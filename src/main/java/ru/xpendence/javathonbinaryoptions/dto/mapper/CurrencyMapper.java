package ru.xpendence.javathonbinaryoptions.dto.mapper;

import org.springframework.stereotype.Component;
import ru.xpendence.javathonbinaryoptions.dto.CurrencyDto;
import ru.xpendence.javathonbinaryoptions.entity.Currency;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 21:12
 * e-mail: 2262288@gmail.com
 */
@Component
public class CurrencyMapper implements AbstractMapper<Currency, CurrencyDto> {

    @Override
    public Currency toEntity(CurrencyDto dto) {
        return null;
    }

    @Override
    public CurrencyDto toDto(Currency entity) {
        return null;
    }
}
