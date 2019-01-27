package ru.xpendence.javathonbinaryoptions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xpendence.javathonbinaryoptions.dto.CurrencyDto;
import ru.xpendence.javathonbinaryoptions.service.CurrencyService;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:46
 * e-mail: 2262288@gmail.com
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService service;

    @Autowired
    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CurrencyDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
