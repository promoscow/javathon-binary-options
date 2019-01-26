package ru.xpendence.javathonbinaryoptions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.xpendence.javathonbinaryoptions.dto.UserDto;
import ru.xpendence.javathonbinaryoptions.dto.mapper.UserMapper;
import ru.xpendence.javathonbinaryoptions.entity.User;
import ru.xpendence.javathonbinaryoptions.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:52
 * e-mail: 2262288@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final Long DEFAULT_CREATION_BALANCE = 1000L;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public UserDto createOrUpdate(UserDto user) {
        User entity = userMapper.toEntity(user);
        User updatedUser = repository.save(entity);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public UserDto generateUser() {
        UserDto generatedUser = UserDto.builder().balance(DEFAULT_CREATION_BALANCE).generated(true).build();
        return createOrUpdate(generatedUser);
    }

    @Override
    public List<UserDto> getAllActive(boolean generated) {
        List<User> users = repository.findAllByGeneratedAndBalanceGreaterThan(generated, 0);
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return repository.saveAll(users);
    }
}
