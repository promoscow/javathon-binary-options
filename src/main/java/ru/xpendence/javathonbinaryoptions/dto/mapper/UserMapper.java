package ru.xpendence.javathonbinaryoptions.dto.mapper;

import org.springframework.stereotype.Component;
import ru.xpendence.javathonbinaryoptions.dto.UserDto;
import ru.xpendence.javathonbinaryoptions.entity.User;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 21:11
 * e-mail: 2262288@gmail.com
 */
@Component
public class UserMapper implements AbstractMapper<User, UserDto> {

    @Override
    public User toEntity(UserDto dto) {
        return null;
    }

    @Override
    public UserDto toDto(User entity) {
        return null;
    }
}
