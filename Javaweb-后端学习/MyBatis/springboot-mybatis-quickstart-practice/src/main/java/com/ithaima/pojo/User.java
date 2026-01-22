package com.ithaima.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public Integer id;
    public String username;
    public  String password;
    public String name;
    public Integer age;

}
