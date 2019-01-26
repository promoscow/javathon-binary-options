package ru.xpendence.javathonbinaryoptions.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:52
 * e-mail: 2262288@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CurrencyDto extends AbstractDto {

    private List<Long> bets;
    private String code;
    private Long rate;
}
