package ru.xpendence.javathonbinaryoptions.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:48
 * e-mail: 2262288@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends AbstractDto {

    private Long balance;
    private List<Long> bets;
}
