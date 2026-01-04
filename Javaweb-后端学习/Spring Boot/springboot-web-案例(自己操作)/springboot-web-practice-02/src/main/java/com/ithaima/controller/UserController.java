package com.ithaima.controller;


import com.ithaima.service.UserService;
import com.ithaima.service.impl.UserServiceImpl;
import com.ithaima.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class UserController {

    private UserService userService=new UserServiceImpl();

    @RequestMapping("/list")
    public List<User> list() throws Exception {

        // 调用service层，获取用户信息
        List<User> userList = userService.findAll();

        // 返回用户信息（json）
        return userList;

    }
}
