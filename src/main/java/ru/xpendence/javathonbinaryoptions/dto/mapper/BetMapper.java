package ru.xpendence.javathonbinaryoptions.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.xpendence.javathonbinaryoptions.attributes.BetVector;
import ru.xpendence.javathonbinaryoptions.dto.BetDto;
import ru.xpendence.javathonbinaryoptions.dto.CurrencyDto;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.entity.Currency;
import ru.xpendence.javathonbinaryoptions.entity.User;
import ru.xpendence.javathonbinaryoptions.repository.BetRepository;
import ru.xpendence.javathonbinaryoptions.repository.CurrencyRepository;
import ru.xpendence.javathonbinaryoptions.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 21:12
 * e-mail: 2262288@gmail.com
 */
@Component
public class BetMapper implements AbstractMapper<Bet, BetDto> {

    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final AbstractMapper<Currency, CurrencyDto> currencyMapper;

    @Autowired
    public BetMapper(BetRepository betRepository,
                     UserRepository userRepository,
                     CurrencyRepository currencyRepository,
                     AbstractMapper<Currency, CurrencyDto> currencyMapper) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
    }

    @Override
    @Transactional
    public Bet toEntity(BetDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        Bet bet = null;
        if (dto.getId() != null) {
            bet = betRepository.getOne(dto.getId());
        } else {
            bet = new Bet();
        }
        bet.setId(dto.getId());
        bet.setAmount(dto.getAmount());
        bet.setCreated(dto.getCreated());
        bet.setFixRate(dto.getFixRate());
        bet.setBetVector(createBetVector(dto.getBetVector()));
        bet.setCurrency(currencyMapper.toEntity(dto.getCurrency()));
        bet.setUser(createUser(dto.getUserId()));
        bet.setExpiresIn(dto.getExpiresIn());
        return bet;
    }

    private User createUser(Long user) {
        return Objects.nonNull(user) ? userRepository.getOne(user) : null;
    }

    private Currency createCurrency(Long currency) {
        return Objects.nonNull(currency) ? currencyRepository.getOne(currency) : null;
    }

    private BetVector createBetVector(Integer betVector) {
        return Arrays.stream(BetVector.values()).filter(v -> v.getId().equals(betVector)).findFirst().orElse(null);
    }

    @Override
    public BetDto toDto(Bet entity) {
        return Objects.nonNull(entity) ? new BetDto(
                entity.getId(),
                entity.getCreated(),
                Objects.nonNull(entity.getUser()) ? entity.getUser().getId() : null,
                entity.getAmount(),
                Objects.nonNull(entity.getCurrency()) ? currencyMapper.toDto(entity.getCurrency()) : null,
                entity.getBetVector().getId(),
                entity.getFixRate(),
                entity.getExpiresIn()
        ) : null;
    }

    public List<BetDto> toDto(List<Bet> bets) {
        return bets.stream().map(this::toDto).collect(Collectors.toList());
    }
}
