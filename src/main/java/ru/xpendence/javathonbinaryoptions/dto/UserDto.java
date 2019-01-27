package ru.xpendence.javathonbinaryoptions.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:48
 * e-mail: 2262288@gmail.com
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AbstractDto {

    private String name;
    private Long balance;
    private List<Long> bets;
    private Boolean generated;

    public UserDto(Long id,
                   LocalDateTime created,
                   String name,
                   Long balance,
                   List<Long> bets,
                   Boolean generated) {
        super(id, created);
        this.name = name;
        this.balance = balance;
        this.bets = bets;
        this.generated = generated;
    }

    @Builder
    public UserDto(Long balance, Boolean generated) {
        this.balance = balance;
        this.generated = generated;
    }
}
