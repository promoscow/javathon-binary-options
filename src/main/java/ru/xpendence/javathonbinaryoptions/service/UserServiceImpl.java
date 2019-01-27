package ru.xpendence.javathonbinaryoptions.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xpendence.javathonbinaryoptions.dto.UserDto;
import ru.xpendence.javathonbinaryoptions.dto.mapper.UserMapper;
import ru.xpendence.javathonbinaryoptions.entity.User;
import ru.xpendence.javathonbinaryoptions.repository.UserRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:52
 * e-mail: 2262288@gmail.com
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final Long DEFAULT_CREATION_BALANCE = 1000L;
    private final Random random = new Random();

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

    @Transactional
    public UserDto create(UserDto user) {
        User userByName = repository.findOneByName(user.getName());
        if (userByName != null) {
            throw new RuntimeException("User already exists");
        }
        if (user.getBalance() == null || user.getBalance() == 0) {
            user.setBalance(DEFAULT_CREATION_BALANCE);
        }
        User entity = userMapper.toNewEntity(user);
        User updatedUser = repository.save(entity);
        return userMapper.toDto(updatedUser);
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 30000)
    @Override
    public UserDto generateUser() {
        UserDto generatedUser = UserDto.builder().name(generateName()).balance(DEFAULT_CREATION_BALANCE).generated(true).build();
        return create(generatedUser);
    }

    public String generateName() {
        return RandomStringUtils.random(10, true, false);
    }

    @Override
    public List<UserDto> getAllActiveByGenerated(boolean generated) {
        List<User> users = repository.findAllByGeneratedAndLimit(generated, 0L);
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllActive() {
        List<User> users = repository.findAllByLimit(0L);
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getTop() {
        return repository.findAllByBalanceDesc(PageRequest.of(0, 10))
                .get().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getByName(String name) {
        return userMapper.toDto(repository.findByName(name));
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return repository.saveAll(users);
    }
}
