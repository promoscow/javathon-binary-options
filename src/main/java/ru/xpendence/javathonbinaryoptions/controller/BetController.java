package ru.xpendence.javathonbinaryoptions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xpendence.javathonbinaryoptions.service.BetService;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:46
 * e-mail: 2262288@gmail.com
 */
@RestController
@RequestMapping("/bet")
public class BetController {

    private final BetService service;

    @Autowired
    public BetController(BetService service) {
        this.service = service;
    }
}
