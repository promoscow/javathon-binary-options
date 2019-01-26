package ru.xpendence.javathonbinaryoptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "ru.xpendence.javathonbinaryoptions.service",
        "ru.xpendence.javathonbinaryoptions.repository",
        "ru.xpendence.javathonbinaryoptions.controller"
})
public class JavathonBinaryOptionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavathonBinaryOptionsApplication.class, args);
    }

}

