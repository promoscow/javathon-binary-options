package ru.xpendence.javathonbinaryoptions.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 26.01.19
 * Time: 17:48
 * e-mail: 2262288@gmail.com
 */
@Configuration
@EnableJpaRepositories
@EntityScan(basePackages = "ru.xpendence.javathonbinaryoptions.entity")
@ComponentScan(basePackages = {
        "ru.xpendence"
})
public class MainConfig {
}
