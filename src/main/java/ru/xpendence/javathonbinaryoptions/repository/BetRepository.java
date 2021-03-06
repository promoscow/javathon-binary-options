package ru.xpendence.javathonbinaryoptions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xpendence.javathonbinaryoptions.entity.Bet;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:42
 * e-mail: 2262288@gmail.com
 */
@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllByIdIn(List<Long> list);

    List<Bet> findAllByExpiresInBefore(LocalDateTime dateTime);
}
