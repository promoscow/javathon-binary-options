package ru.xpendence.javathonbinaryoptions.dto.mapper;

import ru.xpendence.javathonbinaryoptions.dto.AbstractDto;
import ru.xpendence.javathonbinaryoptions.dto.UserDto;
import ru.xpendence.javathonbinaryoptions.entity.AbstractEntity;
import ru.xpendence.javathonbinaryoptions.entity.User;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 21:05
 * e-mail: 2262288@gmail.com
 */
public interface AbstractMapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);
}
