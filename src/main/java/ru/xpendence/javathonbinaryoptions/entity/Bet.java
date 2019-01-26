package ru.xpendence.javathonbinaryoptions.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ru.xpendence.javathonbinaryoptions.attributes.BetVector;

import javax.persistence.*;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:37
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "bets")
@EqualsAndHashCode(callSuper = true)
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE bets SET active = 0 WHERE id = ?")
@Where(clause = "active = 1")
public class Bet extends AbstractEntity {

    private User user;
    private Long amount;
    private Currency currency;
    private BetVector betVector;
    private Long fixRate;

    /**
     * Пользователь, который делает ставку.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    public User getUser() {
        return user;
    }

    /**
     * Сумма ставки.
     */
    @Column(name = "amount")
    public Long getAmount() {
        return amount;
    }

    /**
     * Валюта ставки.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency")
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Вектор ставки.
     */
    @Column(name = "vector")
    public BetVector getBetVector() {
        return betVector;
    }

    /**
     * Курс валюты на момент совершения ставки.
     */
    @Column(name = "fix_rate")
    public Long getFixRate() {
        return fixRate;
    }
}
