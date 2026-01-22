package com.ithaima;

import com.ithaima.mapper.UserMapper;
import com.ithaima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
// springBoot单元测试注解，当前测试类中的测试方法运行时，会启动springBoot应用程序
// IOC容器也会启动，测试方法就可以直接从IOC容器中获取Bean进行测试
class SpringbootMybatisQuickstartPracticeApplicationTests {

    @Autowired
    public UserMapper userMapper;
    
    @Test
    public  void testFindAll() {
        List<User> userlist = userMapper.findAll();
        // userlist.forEach(user -> System.out.println(user));  // 方法1：Lambda表达式
        userlist.forEach(System.out::println);  // 方法2：方法引用
    }

    @Test
    public  void testFindById() {
        Integer n = userMapper.deleteById(4);
        System.out.println(n);
    }

    @Test
    public void testInsert() {
        User user = new User(null,"xiaoming","123456","小明",18);
        Integer n = userMapper.insert(user);
        System.out.println(n);
    }

    @Test
    public  void testUpdate() {
        User user = new User(1,"xiaohong","654321","小红",20);
        Integer n = userMapper.update(user);
        System.out.println(n);
    }

    @Test
    public  void testFindByUsernameAndPassword() {
        User user = userMapper.findByUsernameAndPassword("xiaohong", "654321");
        System.out.println(user);
    }

}
