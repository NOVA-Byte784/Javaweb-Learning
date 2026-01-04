package com.ithaima.service.impl;

import com.ithaima.dao.UserDao;
import com.ithaima.dao.impl.UserDaoImpl;
import com.ithaima.service.UserService;
import com.ithaima.pojo.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class UserServiceImpl implements UserService {

    //
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {

        // 调用dao层,获取原始数据
        List<String> lines = userDao.findAll();

        List<User> usersList = lines.stream().map(
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

        return usersList;
    }

}
