package ru.xpendence.javathonbinaryoptions.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:54
 * e-mail: 2262288@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BetDto extends AbstractDto {

    private Long user;
    private Long amount;
    private Long currency;
    private Long betVector;
    private Long fixRate;
}
