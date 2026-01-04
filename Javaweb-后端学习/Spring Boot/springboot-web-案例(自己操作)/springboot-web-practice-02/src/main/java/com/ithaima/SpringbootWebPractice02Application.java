package com.ithaima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootWebPractice02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebPractice02Application.class, args);
    }

}
