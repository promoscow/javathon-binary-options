package ru.xpendence.javathonbinaryoptions.service;

import org.junit.Before;
import org.junit.Test;
import ru.xpendence.javathonbinaryoptions.entity.Currency;
import ru.xpendence.javathonbinaryoptions.repository.CurrencyRepository;
import yahoofinance.Stock;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class CurrencyServiceImplTest {


    private CurrencyRepository repository;
    private CurrencyServiceImpl currencyService;
    private Map<String, Stock> stringStockMap;

    @Before
    public void setUp() throws Exception {
        currencyService = new CurrencyServiceImpl(repository);
    }

    @Test
    public void preStart() {
        LocalTime start = LocalTime.now();
        List<Currency> list = currencyService.preStartList();
        list.forEach(System.out::println);

        assertNotNull(list);
        System.out.println((LocalTime.now().toNanoOfDay() - start.toNanoOfDay()) / 1000000);
    }
}