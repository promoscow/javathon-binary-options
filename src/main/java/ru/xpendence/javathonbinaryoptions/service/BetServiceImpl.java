package ru.xpendence.javathonbinaryoptions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.xpendence.javathonbinaryoptions.repository.BetRepository;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:43
 * e-mail: 2262288@gmail.com
 */
@Service
public class BetServiceImpl implements BetService {

    private final BetRepository repository;

    @Autowired
    public BetServiceImpl(BetRepository repository) {
        this.repository = repository;
    }
}
