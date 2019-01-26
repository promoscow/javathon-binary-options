package ru.xpendence.javathonbinaryoptions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xpendence.javathonbinaryoptions.entity.Currency;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:42
 * e-mail: 2262288@gmail.com
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
