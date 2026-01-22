package com.itheima;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.itheima.pojo.User;

public class JDBCTest {

    // JDBC入门代码

    @Test
    public void testUpdate() throws Exception {

        // 注册驱动

        Class.forName("com.mysql.cj.jdbc.Driver");

        // 获取数据库连接

        String url = "jdbc:mysql://localhost:3306/web01";
        String user = "root";
        String password = "123456#";

        Connection connection = DriverManager.getConnection(url, user, password);

        // 获取SQL执行对象
        Statement statement = connection.createStatement();

        // 执行SQL
        int i = statement.executeUpdate("update user set age = 25 where id = 1");// DML语句
        System.out.println("影响的行数：" + i);

        // 释放资源
        statement.close();  // 关闭SQL执行对象
        connection.close(); // 关闭数据库连接

    }

    @Test
    public void testSelect() throws Exception {

        String url = "jdbc:mysql://localhost:3306/web01";
        String dbUser = "root";
        String dbPassword = "123456#"; // 请替换为你的实际密码

        // 定义题目要求的 SQL 语句 (硬编码，存在 SQL 注入风险)
        // String sql = "select * from user where username = 'daqiao' and password = '123456'";

        // 预编译 SQL 语句，防止 SQL 注入 (性能也有所提升)
        String sql = "select * from user where username = ? and password = ?";


        // 存放封装后的 User 对象列表
        List<User> userList = new ArrayList<>();

        PreparedStatement pstmt = null;
        Connection conn = null;
        pstmt = null;
        ResultSet rs = null;


        try {
            // 2. 加载驱动 (JDBC 4.0+ 之后通常可以省略，但写上更稳妥)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 3. 获取连接
            conn = DriverManager.getConnection(url, dbUser, dbPassword);

            // 4. 获取执行 SQL 的对象
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "daqiao");

            pstmt.setString(2, "123456");

            // 5. 执行 SQL，获取结果集
            rs = pstmt.executeQuery();

            // 6. 遍历结果集，封装对象
            while (rs.next()) {
                // 创建 User 对象
                User user = new User();

                // 根据列名获取数据并设置到对象中
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));

                // 将封装好的对象添加到集合中
                userList.add(user);
            }

            // 7. 输出结果到控制台
            System.out.println("查询结果如下：");
            if (userList.isEmpty()) {
                System.out.println("未找到符合条件的用户。");
            } else {
                for (User user : userList) {
                    System.out.println(user);
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("找不到数据库驱动类");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("数据库操作异常");
            e.printStackTrace();
        } finally {
            // 8. 释放资源 (遵循先开后关原则)
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
