package com.neocortex.recognitioncortex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="com.neocortex.recognitioncortex")
@SpringBootApplication
public class RecognitionCortexApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecognitionCortexApplication.class, args);
    }

}
