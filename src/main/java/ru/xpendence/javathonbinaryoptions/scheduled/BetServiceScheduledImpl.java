package ru.xpendence.javathonbinaryoptions.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 23:36
 * e-mail: 2262288@gmail.com
 */
@Service
public class BetServiceScheduledImpl implements BetServiceScheduled {

    @Scheduled(initialDelay = 30000, fixedDelay = 10000)
    @Override
    public void betResult() {
        System.out.println("Processing bet result: " + LocalTime.now());
    }
}
