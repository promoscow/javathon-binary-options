package ru.xpendence.javathonbinaryoptions.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xpendence.javathonbinaryoptions.attributes.BetVector;
import ru.xpendence.javathonbinaryoptions.dto.BetDto;
import ru.xpendence.javathonbinaryoptions.dto.mapper.BetMapper;
import ru.xpendence.javathonbinaryoptions.dto.mapper.CurrencyMapper;
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
    private final CurrencyMapper currencyMapper;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final CurrencyService currencyService;
    private final UserService userService;
    private final int MINIMAL_BET_VALUE = 100;

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
        log.info("<- Bet amount is {} for user {}", bet.getAmount(), bet.getUserId());
        User user = userRepository.getOne(bet.getUserId());
        Long balance = user.getBalance();
        if (balance < bet.getAmount()) {
            throw new BetException("Not much money to perform bet.");
        }
        log.info("<- User balance is {} for user {}", user.getBalance(), user.getId());
        user.setBalance(user.getBalance() - bet.getAmount());
        log.info("<- New balance is {} for user {}", user.getBalance(), user.getId());
        userRepository.save(user);
    }

    private Bet createFixRate(BetDto bet) {
        Bet entity = mapper.toEntity(bet);
        if (Objects.isNull(bet.getCurrency())) {
            throw new BetException("Currency is null");
        }
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
        return bots.stream()
                .map(user -> generateBet(allCurr, user))
                .map(this::create)
                .collect(Collectors.toList());
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
            log.info("-> User {} win!", user.getId());
            return calculatePriceForWin(bet, user);
        }
        return user.getBalance();
    }

    private long calculatePriceForWin(Bet bet, User user) {
        long newBalance = user.getBalance() + bet.getAmount() * 2;
        log.info("-> New balance for user {} is {}", user.getId(), newBalance);
        return newBalance;
    }

    private BetVector checkActualVector(Long rate, Long fixRate) {
        return rate.equals(fixRate) ? BetVector.DRAW : rate > fixRate ? BetVector.DOWN : BetVector.UP;
    }

    private BetDto generateBet(List<Currency> allCurr, User user) {
        Currency currency = allCurr.get(current().nextInt(allCurr.size()));
        Long betAmount = user.getBalance();
        if (betAmount > MINIMAL_BET_VALUE) {
            betAmount = getRandomAmount(betAmount);
        }
        LocalDateTime expiresIn = LocalDateTime.now().plusSeconds(current().nextInt(5, 30));
        return new BetDto(user.getId(), betAmount, currencyMapper.toDto(currency), BetVector.randomVector(), currency.getRate(), expiresIn);
    }

    private long getRandomAmount(Long balance) {
        return current().nextLong(1, balance);
    }
}
