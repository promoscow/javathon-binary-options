package ru.xpendence.javathonbinaryoptions.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xpendence.javathonbinaryoptions.attributes.BetVector;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.entity.Currency;
import ru.xpendence.javathonbinaryoptions.entity.User;
import ru.xpendence.javathonbinaryoptions.exception.CurrencyException;
import ru.xpendence.javathonbinaryoptions.service.BetService;
import ru.xpendence.javathonbinaryoptions.service.CurrencyService;
import ru.xpendence.javathonbinaryoptions.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 23:36
 * e-mail: 2262288@gmail.com
 */
@Service
@Slf4j
public class BetServiceScheduledImpl implements BetServiceScheduled {

    private final BetService betService;
    private final CurrencyService currencyService;
    private final UserService userService;

    @Autowired
    public BetServiceScheduledImpl(BetService betService,
                                   CurrencyService currencyService,
                                   UserService userService) {
        this.betService = betService;
        this.currencyService = currencyService;
        this.userService = userService;
    }

    @Scheduled(initialDelayString = "${bet.result.initial.delay}", fixedDelayString = "${bet.result.fixed.delay}")
    @Override
    @Transactional
    public void betResult() {
        log.info("Processing bet results.");
        List<Bet> expired = betService.getAllActiveBetsExpired();
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
}
