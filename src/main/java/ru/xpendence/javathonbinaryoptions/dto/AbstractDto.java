package ru.xpendence.javathonbinaryoptions.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 19:00
 * e-mail: 2262288@gmail.com
 */
@Data
public abstract class AbstractDto {

    private Long id;
    private LocalDateTime created;
}
