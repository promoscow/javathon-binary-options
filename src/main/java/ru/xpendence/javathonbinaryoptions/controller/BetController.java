package ru.xpendence.javathonbinaryoptions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.xpendence.javathonbinaryoptions.dto.BetDto;
import ru.xpendence.javathonbinaryoptions.service.BetService;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:46
 * e-mail: 2262288@gmail.com
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bet")
public class BetController {

    private final BetService service;

    @Autowired
    public BetController(BetService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<BetDto> get(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.get(id));
    }
}
