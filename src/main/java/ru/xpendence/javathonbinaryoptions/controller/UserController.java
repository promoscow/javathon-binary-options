package ru.xpendence.javathonbinaryoptions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.xpendence.javathonbinaryoptions.dto.UserDto;
import ru.xpendence.javathonbinaryoptions.service.UserService;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:48
 * e-mail: 2262288@gmail.com
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDto createNewUser(@RequestBody UserDto userDto) {
        return service.create(userDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getAllActiveUsers() {
        return service.getAllActiveByGenerated(false);
    }
}
