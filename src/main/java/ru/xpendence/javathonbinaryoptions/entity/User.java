package ru.xpendence.javathonbinaryoptions.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:44
 * e-mail: 2262288@gmail.com
 */
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE users SET active = 0 WHERE id = ?")
@Where(clause = "active = 1")
public class User extends AbstractEntity {

    private String name;
    private Long balance;
    private List<Bet> bets;
    private Boolean generated;

    public User(String name, Long balance, Boolean generated) {
        this.name = name;
        this.balance = balance;
        this.generated = generated;
    }

    /**
     * Имя пользователя.
     */
    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

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
