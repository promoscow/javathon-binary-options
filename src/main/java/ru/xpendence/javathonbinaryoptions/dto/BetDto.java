package ru.xpendence.javathonbinaryoptions.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.xpendence.javathonbinaryoptions.entity.Currency;

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

    private Long userId;
    private Long amount;
    private Currency currency;
    private Integer betVector;
    private Long fixRate;
    private LocalDateTime expiresIn;

    public BetDto(Long id,
                  LocalDateTime created,
                  Long user,
                  Long amount,
                  Currency currency,
                  Integer betVector,
                  Long fixRate,
                  LocalDateTime expiresIn) {
        super(id, created);
        this.userId = user;
        this.amount = amount;
        this.currency = currency;
        this.betVector = betVector;
        this.fixRate = fixRate;
        this.expiresIn = expiresIn;
    }
}
