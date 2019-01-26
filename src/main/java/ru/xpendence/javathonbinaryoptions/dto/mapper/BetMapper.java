package ru.xpendence.javathonbinaryoptions.dto.mapper;

import ru.xpendence.javathonbinaryoptions.dto.BetDto;
import ru.xpendence.javathonbinaryoptions.entity.Bet;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 21:12
 * e-mail: 2262288@gmail.com
 */
public class BetMapper implements AbstractMapper<Bet, BetDto> {

    @Override
    public Bet toEntity(BetDto dto) {
        return null;
    }

    @Override
    public BetDto toDto(Bet entity) {
        return null;
    }
}
