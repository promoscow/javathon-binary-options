package ru.xpendence.javathonbinaryoptions.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.xpendence.javathonbinaryoptions.dto.UserDto;
import ru.xpendence.javathonbinaryoptions.entity.AbstractEntity;
import ru.xpendence.javathonbinaryoptions.entity.Bet;
import ru.xpendence.javathonbinaryoptions.entity.User;
import ru.xpendence.javathonbinaryoptions.repository.BetRepository;
import ru.xpendence.javathonbinaryoptions.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 21:11
 * e-mail: 2262288@gmail.com
 */
@Component
public class UserMapper implements AbstractMapper<User, UserDto> {

    private final UserRepository userRepository;
    private final BetRepository betRepository;

    @Autowired
    public UserMapper(UserRepository userRepository, BetRepository betRepository) {
        this.userRepository = userRepository;
        this.betRepository = betRepository;
    }

    @Override
    public User toEntity(UserDto dto) {
        if (Objects.isNull(dto) || Objects.isNull(dto.getId())) {
            return null;
        }
        User user = userRepository.getOne(dto.getId());
        if (Objects.isNull(user)) {
            return null;
        }
        user.setCreated(dto.getCreated());
        user.setBalance(dto.getBalance());
        user.setName(dto.getName());
        user.setGenerated(dto.getGenerated());
        user.setBets(getBets(dto.getBets()));
        return user;
    }

    private List<Bet> getBets(List<Long> bets) {
        return betRepository.findAllByIdIn(bets);
    }

    @Override
    public UserDto toDto(User entity) {
        return Objects.isNull(entity) ? null : new UserDto(
                entity.getId(),
                entity.getCreated(),
                entity.getName(),
                entity.getBalance(),
                entity.getBets().stream().map(AbstractEntity::getId).collect(Collectors.toList()),
                entity.getGenerated()
        );
    }
}
