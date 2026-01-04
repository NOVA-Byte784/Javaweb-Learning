package com.ithaima.dao.impl;

import cn.hutool.core.io.IoUtil;
import com.ithaima.dao.UserDao;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Component //将当前类的对象交给Spring容器进行管理
public class UserDaoImpl implements UserDao {


    @Override
    public List<String> findAll() {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8,new ArrayList<>());
        return lines;
    }
}
