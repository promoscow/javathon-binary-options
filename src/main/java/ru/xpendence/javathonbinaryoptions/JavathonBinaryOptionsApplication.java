package ru.xpendence.javathonbinaryoptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "ru.xpendence.javathonbinaryoptions.service",
        "ru.xpendence.javathonbinaryoptions.repository",
        "ru.xpendence.javathonbinaryoptions.controller",
        "ru.xpendence.javathonbinaryoptions.dto.mapper"
})
@EnableJpaRepositories
@EntityScan(basePackages = "ru.xpendence.javathonbinaryoptions.entity")
public class JavathonBinaryOptionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavathonBinaryOptionsApplication.class, args);
    }

}

