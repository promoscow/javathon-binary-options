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
@Table(name = "currencies")
@EqualsAndHashCode(callSuper = true)
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE currencies SET active = 0 WHERE id = ?")
@Where(clause = "active = 1")
public class Currency extends AbstractEntity {

    private String code;
    private Long rate;
    private List<Bet> bets;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    @Column(name = "rate")
    public Long getRate() {
        return rate;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "currency")
    public List<Bet> getBets() {
        return bets;
    }
}
