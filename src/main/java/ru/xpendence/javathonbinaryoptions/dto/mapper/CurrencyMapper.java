package ru.xpendence.javathonbinaryoptions.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.xpendence.javathonbinaryoptions.dto.CurrencyDto;
import ru.xpendence.javathonbinaryoptions.entity.Currency;
import ru.xpendence.javathonbinaryoptions.repository.CurrencyRepository;

import java.util.Objects;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 21:12
 * e-mail: 2262288@gmail.com
 */
@Component
public class CurrencyMapper implements AbstractMapper<Currency, CurrencyDto> {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Currency toEntity(CurrencyDto dto) {
        if (Objects.isNull(dto) || Objects.isNull(dto.getId())) {
            return null;
        }
        Currency currency = currencyRepository.getOne(dto.getId());
        if (Objects.isNull(currency)) {
            return null;
        }
        currency.setCode(dto.getCode());
        currency.setRate(dto.getRate());
        currency.setCreated(dto.getCreated());
        return currency;
    }

    @Override
    public CurrencyDto toDto(Currency entity) {
        return Objects.nonNull(entity) ? new CurrencyDto(
                entity.getId(),
                entity.getCreated(),
                entity.getCode(),
                entity.getRate()
        ) : null;
    }
}
