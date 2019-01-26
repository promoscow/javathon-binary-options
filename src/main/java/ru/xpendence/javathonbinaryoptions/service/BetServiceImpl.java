package ru.xpendence.javathonbinaryoptions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.xpendence.javathonbinaryoptions.dto.BetDto;
import ru.xpendence.javathonbinaryoptions.dto.mapper.AbstractMapper;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.repository.BetRepository;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:43
 * e-mail: 2262288@gmail.com
 */
@Service
public class BetServiceImpl implements BetService {

    private BetRepository repository;
    private final AbstractMapper<Bet, BetDto> mapper;

    @Autowired
    public BetServiceImpl(BetRepository repository,
                          AbstractMapper<Bet, BetDto> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BetDto create(BetDto bet) {
        return mapper.toDto(repository.save(mapper.toEntity(bet)));
    }
}
