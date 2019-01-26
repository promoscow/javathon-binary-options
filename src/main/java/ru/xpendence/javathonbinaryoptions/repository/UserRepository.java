package ru.xpendence.javathonbinaryoptions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xpendence.javathonbinaryoptions.entity.User;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:48
 * e-mail: 2262288@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
