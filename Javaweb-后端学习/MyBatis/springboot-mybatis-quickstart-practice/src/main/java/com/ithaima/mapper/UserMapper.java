package com.ithaima.mapper;

import com.ithaima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 应用程序在运行时，会动态生成该接口的实现类对象，并会自动将实现类存入IoC容器中（Bean）
public interface UserMapper {

    // @Select("select * from user")    // 使用注解方式编写SQL
    public List<User> findAll();

    // 使用#{}占位符传入参数，MyBatis会自动将参数进行类型转换并防止SQL注入
    @Delete("delete from user where id = #{id}")
    public Integer deleteById(Integer id);

    //
    @Insert("insert into user(username,password,name,age) " +
            "values(#{username},#{password},#{name},#{age})")
    public Integer insert(User user);

    @Update("update user set " +
            "username=#{username},password=#{password},name=#{name},age=#{age} where id=#{id}")
    public Integer update(User user);


    // MyBatis 在处理多个参数时，默认无法识别代码中的变量名（username 和 password）
    // 它在内部会将参数命名为 param1, param2 或者 arg0, arg1
    // 如果你在 SQL 里写 #{username}，MyBatis 会找不到这个名字，从而抛出异常
    // 需要使用 @Param 注解明确指定 SQL 中使用的参数名
    // @Select("select * from user where username = #{username} and password = #{password}")
    // public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    // 基于官方最新版本的 MyBatis（3.4.1 及以上），可以直接启用参数名称的支持
    // 只需确保在编译时启用了 -parameters 选项（大多数现代 IDE 默认启用）
    @Select("select * from user where username = #{username} and password = #{password}")
    public User findByUsernameAndPassword(String username, String password);

}
