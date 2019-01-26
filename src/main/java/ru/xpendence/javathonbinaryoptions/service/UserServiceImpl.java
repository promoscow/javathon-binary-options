package ru.xpendence.javathonbinaryoptions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.xpendence.javathonbinaryoptions.repository.UserRepository;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:52
 * e-mail: 2262288@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
}
