package ru.xpendence.javathonbinaryoptions.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.entity.Currency;
import ru.xpendence.javathonbinaryoptions.entity.User;
import ru.xpendence.javathonbinaryoptions.service.BetService;
import ru.xpendence.javathonbinaryoptions.service.CurrencyService;
import ru.xpendence.javathonbinaryoptions.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 23:36
 * e-mail: 2262288@gmail.com
 */
@Service
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

    @Scheduled(initialDelay = 30000, fixedDelay = 10000)
    @Override
    @Transactional
    public void betResult() {
        //берём все активные ставки
        //рассчитываем, каким подошёл срок
        //берём валюты
        //начисляем
        //сохраняем в аккаунты
        List<Bet> expired = betService.getAllActiveBetsExpired();
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

    private Long editBalance(Bet bet, User user, List<Currency> actualCurrencies) {
        Long balance = user.getBalance();
        Long amount = bet.getAmount();

        return null;
    }
}
