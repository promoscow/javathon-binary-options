package ru.xpendence.javathonbinaryoptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "ru.xpendence.javathonbinaryoptions.service",
        "ru.xpendence.javathonbinaryoptions.repository",
        "ru.xpendence.javathonbinaryoptions.controller",
        "ru.xpendence.javathonbinaryoptions.dto.mapper",
        "ru.xpendence.javathonbinaryoptions.scheduled"
})
@EnableJpaRepositories
@EnableScheduling
@EntityScan(basePackages = "ru.xpendence.javathonbinaryoptions.entity")
@PropertySource("classpath:/common.properties")
public class JavathonBinaryOptionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavathonBinaryOptionsApplication.class, args);
    }

}

