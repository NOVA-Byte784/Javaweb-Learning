package com.ithaima.controller;


import com.ithaima.service.UserService;
import com.ithaima.service.impl.UserServiceImpl;
import com.ithaima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class UserController {

    // 一：属性注入
    @Autowired // 自动注入UserService类型的对象
    private UserService userService;


    // 二：使用构造器注入
    // private final UserService userService;
    // @Autowired   // 只有一个构造器时，可以省略@Autowired
    // public UserController(UserService userService) {
    //     this.userService = userService;
    // }

    // 三：set方法注入
    // private UserService userService;
    // @Autowired
    // public void setUserService(UserService userService) {
    //     this.userService = userService;
    // }

    @RequestMapping("/list")
    public List<User> list() throws Exception {

        // 调用service层，获取用户信息
        List<User> userList = userService.findAll();

        // 返回用户信息（json）
        return userList;

    }
}
