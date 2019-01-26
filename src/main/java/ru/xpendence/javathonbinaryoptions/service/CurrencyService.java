package ru.xpendence.javathonbinaryoptions.service;

import ru.xpendence.javathonbinaryoptions.dto.CurrencyDto;
import ru.xpendence.javathonbinaryoptions.entity.Currency;

import java.util.List;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 18:43
 * e-mail: 2262288@gmail.com
 */
public interface CurrencyService {

    List<Currency> preStartList();

    List<CurrencyDto> getAll();
}
