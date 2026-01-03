package com.ithaima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data   // 生成getter/setter/toString等方法
@NoArgsConstructor  // 生成无参构造器
@AllArgsConstructor // 生成全参构造器
public class User {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private LocalDateTime updateTime;

}
