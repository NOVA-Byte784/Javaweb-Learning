package com.ithaima.controller;


import cn.hutool.core.io.IoUtil;
import com.ithaima.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// 用户信息控制器

@RestController // 标识这是一个控制器类，处理HTTP请求
public class UserController {

    @RequestMapping("/list")
    public List<User> list(){
        // 加载并读取用户信息

        // 不推荐使用这种方式，路径硬编码，不灵活
        // InputStream in = new FileInputStream(new File("D:\\idea_workspace\\springboot-web-practice-01\\src\\main\\resources\\users.txt"));

        // 推荐使用类加载器读取资源文件，路径相对于resources目录
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("users.txt");

        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8,new ArrayList<>());


        // 解析用户信息,封装成对象User ---> list集合

        List<User> UserList= lines.stream().map(
                line->{
                    String[] parts = line.split(",");
                    Integer id = Integer.parseInt(parts[0]);
                    String username = parts[1];
                    String password = parts[2];
                    String name = parts[3];
                    Integer age = Integer.parseInt(parts[4]);
                    LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    return new User(id, username, password, name, age, updateTime);
                }
        ).toList();


        // 返回用户信息（json）
        return UserList;
    }

}
