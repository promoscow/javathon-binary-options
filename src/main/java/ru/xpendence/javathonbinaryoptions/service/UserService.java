package ru.xpendence.javathonbinaryoptions.service;

import ru.xpendence.javathonbinaryoptions.dto.UserDto;
import ru.xpendence.javathonbinaryoptions.entity.User;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:52
 * e-mail: 2262288@gmail.com
 */
public interface UserService {
    UserDto createOrUpdate(UserDto user);

    UserDto generateUser();

    List<UserDto> getAllActive(boolean generated);

    List<User> saveAll(List<User> users);
}
