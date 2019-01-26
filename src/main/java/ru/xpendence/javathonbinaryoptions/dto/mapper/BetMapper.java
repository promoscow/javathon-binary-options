package ru.xpendence.javathonbinaryoptions.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.xpendence.javathonbinaryoptions.dto.BetDto;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.repository.BetRepository;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 21:12
 * e-mail: 2262288@gmail.com
 */
@Component
public class BetMapper implements AbstractMapper<Bet, BetDto> {

    @Autowired
    private BetRepository betRepository;

    @Override
    public Bet toEntity(BetDto dto) {

        return null;
    }

    @Override
    public BetDto toDto(Bet entity) {
        return null;
    }
}
