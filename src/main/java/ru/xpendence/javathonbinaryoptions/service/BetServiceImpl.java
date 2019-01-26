package ru.xpendence.javathonbinaryoptions.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xpendence.javathonbinaryoptions.attributes.BetVector;
import ru.xpendence.javathonbinaryoptions.dto.BetDto;
import ru.xpendence.javathonbinaryoptions.dto.mapper.BetMapper;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.entity.Currency;
import ru.xpendence.javathonbinaryoptions.entity.User;
import ru.xpendence.javathonbinaryoptions.repository.BetRepository;
import ru.xpendence.javathonbinaryoptions.repository.CurrencyRepository;
import ru.xpendence.javathonbinaryoptions.repository.UserRepository;

import java.util.List;
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
public class BetServiceImpl implements BetService {

    private final BetRepository repository;
    private final BetMapper mapper;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    @Transactional
    public BetDto create(BetDto bet) {
        return mapper.toDto(repository.save(createFixRate(bet)));
    }

    private Bet createFixRate(BetDto bet) {
        Bet entity = mapper.toEntity(bet);
        entity.setFixRate(bet.getCurrency().getRate());
        return entity;
    }

    @Override
    public List<BetDto> generate() {
        List<Currency> allCurr = currencyRepository.findAll();
        List<User> bots = userRepository.findAllByGeneratedAndBalanceGreaterThan(true, 0);
        List<Bet> generatedBets = bots.stream()
                .map(user -> generateBet(allCurr, user))
                .collect(Collectors.toList());
        List<Bet> bets = repository.saveAll(generatedBets);
        return mapper.toDto(bets);
    }

    private Bet generateBet(List<Currency> allCurr, User user) {
        Currency currency = allCurr.get(current().nextInt(allCurr.size()));
        return new Bet(user, current().nextLong(1, user.getBalance()), currency, BetVector.randomVector(), currency.getRate());
    }
}
