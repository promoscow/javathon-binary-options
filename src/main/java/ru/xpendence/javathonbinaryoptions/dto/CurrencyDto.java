package ru.xpendence.javathonbinaryoptions.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:52
 * e-mail: 2262288@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CurrencyDto extends AbstractDto {

    private String code;
    private Long rate;

    public CurrencyDto(Long id,
                       LocalDateTime created,
                       String code,
                       Long rate) {
        super(id, created);
        this.code = code;
        this.rate = rate;
    }
}
