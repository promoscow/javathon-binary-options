package ru.xpendence.javathonbinaryoptions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Long userId;
    private Long amount;
    private CurrencyDto currency;
    private Integer betVector;
    private Long fixRate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime expiresIn;

    public BetDto(Long id,
                  LocalDateTime created,
                  Long user,
                  Long amount,
                  CurrencyDto currency,
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

    public BetDto(Long userId, Long amount, CurrencyDto currency, Integer betVector, Long fixRate, LocalDateTime expiresIn) {
        this(null, null, userId, amount, currency, betVector, fixRate, expiresIn);
    }
}
