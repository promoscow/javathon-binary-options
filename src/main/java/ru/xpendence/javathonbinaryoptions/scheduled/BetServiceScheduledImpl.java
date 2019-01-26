package ru.xpendence.javathonbinaryoptions.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.service.BetService;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 23:36
 * e-mail: 2262288@gmail.com
 */
@Service
public class BetServiceScheduledImpl implements BetServiceScheduled {

    @Autowired
    private BetService betService;

    @Scheduled(initialDelay = 30000, fixedDelay = 10000)
    @Override
    public void betResult() {
        //берём все активные ставки
        //рассчитываем, каким подошёл срок
        //берём валюты
        //начисляем
        //сохраняем в аккаунты
        List<Bet> expired = betService.getAllActiveBetsExpired();
//        List<Currency> actualCurrencies =
    }
}
