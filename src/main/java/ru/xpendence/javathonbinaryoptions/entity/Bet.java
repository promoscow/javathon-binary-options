package ru.xpendence.javathonbinaryoptions.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ru.xpendence.javathonbinaryoptions.attributes.ActiveType;
import ru.xpendence.javathonbinaryoptions.attributes.BetVector;

import javax.persistence.*;
import java.time.LocalDateTime;

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
@NoArgsConstructor
@Setter
@SQLDelete(sql = "UPDATE bets SET active = 0 WHERE id = ?")
@Where(clause = "active = 1")
public class Bet extends AbstractEntity {

    private User user;
    private Long amount;
    private Currency currency;
    private BetVector betVector;
    private Long fixRate;
    private LocalDateTime expiresIn;

    public Bet(User user, Long amount, Currency currency, BetVector betVector, Long fixRate, LocalDateTime expiresIn) {
        this(null, null, null, ActiveType.ENABLED, user, amount, currency, betVector, fixRate, expiresIn);
    }

    public Bet(Long id,
               LocalDateTime created,
               LocalDateTime updated,
               ActiveType active,
               User user,
               Long amount,
               Currency currency,
               BetVector betVector,
               Long fixRate,
               LocalDateTime expiresIn) {
        super(id, created, updated, active);
        this.user = user;
        this.amount = amount;
        this.currency = currency;
        this.betVector = betVector;
        this.fixRate = fixRate;
        this.expiresIn = expiresIn;
    }

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

    @Column(name = "expires_in")
    public LocalDateTime getExpiresIn() {
        return expiresIn;
    }
}
