package ru.xpendence.javathonbinaryoptions.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:54
 * e-mail: 2262288@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BetDto extends AbstractDto {

    private Long user;
    private Long amount;
    private Long currency;
    private Integer betVector;
    private Long fixRate;

    public BetDto(Long id,
                  LocalDateTime created,
                  Long user,
                  Long amount,
                  Long currency,
                  Integer betVector,
                  Long fixRate) {
        super(id, created);
        this.user = user;
        this.amount = amount;
        this.currency = currency;
        this.betVector = betVector;
        this.fixRate = fixRate;
    }
}
