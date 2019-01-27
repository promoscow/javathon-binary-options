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

    UserDto create(UserDto user);

    UserDto generateUser();

    List<User> saveAll(List<User> users);

    List<UserDto> getAllActiveByGenerated(boolean generated);

    List<UserDto> getAllActive();

    List<UserDto> getTop();

    UserDto getByName(String name);
}
