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
public class Currency extends AbstractEntity {

    private List<Bet> bets;
    private String code;
    private Long rate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "currency")
    public List<Bet> getBets() {
        return bets;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    @Column(name = "rate")
    public Long getRate() {
        return rate;
    }
}
