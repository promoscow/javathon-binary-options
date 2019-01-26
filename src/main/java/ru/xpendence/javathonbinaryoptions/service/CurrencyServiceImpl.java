package ru.xpendence.javathonbinaryoptions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.xpendence.javathonbinaryoptions.repository.CurrencyRepository;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:44
 * e-mail: 2262288@gmail.com
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository repository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository repository) {
        this.repository = repository;
    }
}
