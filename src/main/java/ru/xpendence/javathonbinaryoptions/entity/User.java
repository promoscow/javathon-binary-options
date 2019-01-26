package ru.xpendence.javathonbinaryoptions.entity;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:44
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "currencies")
@EqualsAndHashCode(callSuper = true)
@ToString
@Setter
public class User extends AbstractEntity {

    private Long balance;
    private List<Bet> bets;
    private Boolean generated;

    /**
     * Текущий баланс пользователя.
     */
    @Column(name = "balance")
    public Long getBalance() {
        return balance;
    }

    /**
     * Ставки, сделанные пользователем.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    public List<Bet> getBets() {
        return bets;
    }

    @Column(name = "generated")
    public Boolean getGenerated() {
        return generated;
    }
}
