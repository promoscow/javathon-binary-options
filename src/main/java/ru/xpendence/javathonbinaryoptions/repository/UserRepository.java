package ru.xpendence.javathonbinaryoptions.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.xpendence.javathonbinaryoptions.entity.User;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:48
 * e-mail: 2262288@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.generated = :generated and u.balance > :limit")
    List<User> findAllByGeneratedAndLimit(@Param("generated") boolean generated, @Param("limit") Long limit);

    @Query(value = "select u from User u where u.balance > :limit")
    List<User> findAllByLimit(@Param("limit") Long limit);

    User findOneByName(String name);

    @Query("select u from User u order by u.balance desc")
    Page<User> findAllByBalanceDesc(Pageable pageable);

    User findByName(String name);
}
