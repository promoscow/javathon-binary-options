package ru.xpendence.javathonbinaryoptions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.xpendence.javathonbinaryoptions.attributes.CurrencyCode;
import ru.xpendence.javathonbinaryoptions.dto.CurrencyDto;
import ru.xpendence.javathonbinaryoptions.dto.mapper.AbstractMapper;
import ru.xpendence.javathonbinaryoptions.entity.Currency;
import ru.xpendence.javathonbinaryoptions.repository.CurrencyRepository;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: Artur Sharafutdinov
 * Date: 26.01.19
 * Time: 18:44
 * e-mail: sharhack@yahoo.com
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository repository;
    private final AbstractMapper<Currency, CurrencyDto> mapper;
    private List<Currency> listCurrency;
    private Map<String, Stock> mapCurrency;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository repository,
                               AbstractMapper<Currency, CurrencyDto> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
//        repository.saveAll(preStartList());
    }

    @Override
    public List<Currency> preStartList() {
        String[] arrayCode = new String[]{"BTC-USD", "ETH-USD", "XRP-USD", "LTC-USD", "WAVES-USD", "USDRUB=X",
                "EURRUB=X", "NZDRUB=X", "AUDUSD=X", "USDJPY=X"};
        listCurrency = new ArrayList<>();
        try {
            mapCurrency = YahooFinance.get(arrayCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapCurrency.entrySet().parallelStream().forEach(e -> {
            EnumSet.allOf(CurrencyCode.class).forEach(code -> {
                if(e.getKey().contains(code.name())){
                Currency currency = new Currency();
                currency.setCode(code.name());
                currency.setRate(e.getValue().getQuote().getPrice().longValue());
                listCurrency.add(currency);
            }
            });

        });
        return listCurrency;
    }

    @Override
    public List<CurrencyDto> getAll() {
        return preStartList().stream().map(mapper::toDto).collect(Collectors.toList());
    }

}
