package com.ithaima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// 默认扫描当前包及其子包,即com.ithaima及其子包

public class SpringbootWebPractice03Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebPractice03Application.class, args);
    }

}
