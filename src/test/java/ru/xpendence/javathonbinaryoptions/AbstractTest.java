package ru.xpendence.javathonbinaryoptions;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.entity.Currency;
import ru.xpendence.javathonbinaryoptions.entity.User;
import ru.xpendence.javathonbinaryoptions.repository.UserRepository;
import ru.xpendence.javathonbinaryoptions.service.CurrencyService;
import ru.xpendence.javathonbinaryoptions.service.UserService;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.01.19
 * Time: 2:38
 * e-mail: 2262288@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@EnableJpaRepositories
public class AbstractTest {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    List<Currency> currencies;
    List<User> users;
    List<Bet> bets;

    @Before
    public void init() {
        currencies = currencyService.preStartList();
        currencies.forEach(System.out::println);
        users = generateUsers();
    }

    private List<User> generateUsers() {
        return Stream
                .generate(() -> new User("user " + LocalTime.now().toNanoOfDay(), 1000L, true))
                .limit(10L)
                .map(u -> userRepository.save(u))
                .collect(Collectors.toList());
    }
}
