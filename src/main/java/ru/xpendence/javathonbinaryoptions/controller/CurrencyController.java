package ru.xpendence.javathonbinaryoptions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xpendence.javathonbinaryoptions.service.CurrencyService;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:46
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService service;

    @Autowired
    public CurrencyController(CurrencyService service) {
        this.service = service;
    }
}
