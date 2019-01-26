package ru.xpendence.javathonbinaryoptions.service;

import ru.xpendence.javathonbinaryoptions.dto.BetDto;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:43
 * e-mail: 2262288@gmail.com
 */
public interface BetService {

    BetDto create(BetDto bet);

    List<BetDto> generate();
}
