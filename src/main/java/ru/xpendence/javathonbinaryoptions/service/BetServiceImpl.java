package ru.xpendence.javathonbinaryoptions.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xpendence.javathonbinaryoptions.attributes.BetVector;
import ru.xpendence.javathonbinaryoptions.dto.BetDto;
import ru.xpendence.javathonbinaryoptions.dto.mapper.BetMapper;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.entity.Currency;
import ru.xpendence.javathonbinaryoptions.entity.User;
import ru.xpendence.javathonbinaryoptions.exception.BetException;
import ru.xpendence.javathonbinaryoptions.exception.CurrencyException;
import ru.xpendence.javathonbinaryoptions.repository.BetRepository;
import ru.xpendence.javathonbinaryoptions.repository.CurrencyRepository;
import ru.xpendence.javathonbinaryoptions.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:43
 * e-mail: 2262288@gmail.com
 */
@Service
@AllArgsConstructor
@Slf4j
public class BetServiceImpl implements BetService {

    private final BetRepository repository;
    private final BetMapper mapper;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final CurrencyService currencyService;
    private final UserService userService;

    @Override
    @Transactional
    public BetDto create(BetDto bet) {
        log.info("Creating bet {}", bet);
        doBet(bet);
        return mapper.toDto(repository.save(createFixRate(bet)));
    }

    @Override
    public List<Bet> getAllActiveBetsExpired() {
        List<Bet> bets = repository.findAllByExpiresInBefore(LocalDateTime.now());
        if (!bets.isEmpty()) {
            log.info("Expired bets found: {}", bets.size());
        }
        return bets;
    }

    private void doBet(BetDto bet) {
        User user = userRepository.getOne(bet.getId());
        Long balance = user.getBalance();
        if (balance < bet.getAmount()) {
            throw new BetException("Not much money to perform bet.");
        }
        user.setBalance(user.getBalance() - bet.getAmount());
        userRepository.save(user);
    }

    private Bet createFixRate(BetDto bet) {
        Bet entity = mapper.toEntity(bet);
        entity.setFixRate(bet.getCurrency().getRate());
        return entity;
    }

    @Override
    @Transactional
    @Scheduled(initialDelay = 30000, fixedDelay = 15000)
    public List<BetDto> generate() {
        List<Currency> allCurr = currencyService.preStartList();
        currencyRepository.saveAll(allCurr);
        List<User> bots = userRepository.findAllByGeneratedAndLimit(true, 0L);
        List<Bet> generatedBets = bots.stream()
                .map(user -> generateBet(allCurr, user))
                .collect(Collectors.toList());
        List<Bet> bets = repository.saveAll(generatedBets);
        return mapper.toDto(bets);
    }

    @Scheduled(initialDelayString = "${bet.result.initial.delay}", fixedDelayString = "${bet.result.fixed.delay}")
    @Override
    @Transactional
    public void betResult() {
        log.info("Processing bet results.");
        List<Bet> expired = getAllActiveBetsExpired();
        if (Objects.nonNull(expired) && !expired.isEmpty()) {
            List<Currency> actualCurrencies = currencyService.preStartList();
            userService.saveAll(expired
                    .stream()
                    .map(e -> {
                        User user = e.getUser();
                        user.setBalance(editBalance(e, user, actualCurrencies));
                        return user;
                    })
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public BetDto get(Long id) {
        return mapper.toDto(repository.getOne(id));
    }

    private Long editBalance(Bet bet, User user, List<Currency> actualCurrencies) {
        BetVector vector = bet.getBetVector();
        Currency currency = actualCurrencies
                .stream()
                .filter(c -> c.getCode().equals(bet.getCurrency().getCode()))
                .findFirst()
                .orElseThrow(() -> new CurrencyException("No such currency"));
        BetVector actualVector = checkActualVector(currency.getRate(), bet.getFixRate());
        if (vector.equals(actualVector)) {
            return calculatePriceForWin(bet, user);
        }
        return user.getBalance();
    }

    private long calculatePriceForWin(Bet bet, User user) {
        return user.getBalance() + bet.getAmount() * 180 / 100;
    }

    private BetVector checkActualVector(Long rate, Long fixRate) {
        return rate.equals(fixRate) ? BetVector.DRAW : rate > fixRate ? BetVector.DOWN : BetVector.UP;
    }

    private Bet generateBet(List<Currency> allCurr, User user) {
        Currency currency = allCurr.get(current().nextInt(allCurr.size()));
        return new Bet(user, current().nextLong(1, user.getBalance()), currency, BetVector.randomVector(), currency.getRate(), LocalDateTime.now().plusMinutes(new Random().nextInt(3) + 1));
    }
}
