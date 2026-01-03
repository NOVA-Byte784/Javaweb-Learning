# Javaweb学习

[全网首发AI+JavaWeb开发入门，Tlias教学管理系统项目实战全套视频教程，从需求分析、设计、前后端开发、测试、程序优化到项目部署一套搞定_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1yGydYEE3H/?spm_id_from=333.337.search-card.all.click&vd_source=9916ba039b625fccde258af0ad4edb7b)



# 1. web前端开发





# 2. web后端开发

## 2.1 Maven

### maven坐标

#### 1. 什么是坐标？

坐标是 Maven 在仓库中**唯一标识**一个 Jar 包的三要素，统称 **GAV**。

#### 2. GAV 三要素详解

- **G (GroupId)**：**组织/公司 ID**。规则：域名倒写。例子：org.springframework (Spring组织), com.alibaba (阿里)。
- **A (ArtifactId)**：**项目/产品名称**。规则：具体的模块名。例子：spring-context (Spring的上下文模块), druid (数据库连接池)。
- **V (Version)**：**版本号**。例子：分为Release（正式版） vs SNAPSHOT（开发版）。6.1.4, 1.0-SNAPSHOT (快照开发版)。

#### 3. 坐标与硬盘路径的关系

坐标直接对应本地仓库的**文件夹层级**。

- **坐标**：org.springframework : spring-context : 6.1.4
- **路径**：repo/org/springframework/spring-context/6.1.4/xxx.jar

#### 4. 坐标的两种写法

- **定义自己**：在 pom.xml 开头，给自己的项目起名字。
- **引用别人**：在 <dependencies> 标签里，告诉 Maven 我需要用谁的 Jar 包。

------



### Maven的5 个关键阶段

| 阶段           | 归属周期 | 核心作用     | 结果/产物                                                    |
| -------------- | -------- | ------------ | ------------------------------------------------------------ |
| **1. clean**   | Clean    | **清理环境** | 删除 target 目录（消除旧文件残留）。                         |
| **2. compile** | Default  | **编译代码** | 将 .java 编译成 .class，放入 target/classes。                |
| **3. test**    | Default  | **运行测试** | 自动运行 src/test 下的单元测试代码。                         |
| **4. package** | Default  | **打包程序** | 将编译好的代码打成 .jar 或 .war 包，放在 target 下。         |
| **5. install** | Default  | **安装入库** | 将打好的包复制到你的**本地仓库** (repo 文件夹)，供其他项目使用。 |

运行后面的阶段 = 自动执行前面所有阶段

注意：clean 属于独立的生命周期。所以，单跑 mvn install 是**不会**执行清理操作的。



## 2.2 测试

单元测试，集成测试，系统测试，验收测试



测试方法：白盒测试，黑盒测试，灰盒测试



### 2.2.1 单元测试（基于JUnit 5）

- **JUnit 5**: 模块化设计，支持参数化测试，与所有主流IDE（IntelliJ, Eclipse）和构建工具（Maven, Gradle）无缝集成。

​	

断言：



Junit5常见注解：



使用maven设置依赖jar包的作用范围：

![image-20251231171842107](Javaweb学习.assets/image-20251231171842107.png)



## 2.3 web基础

![image-20251231173334565](Javaweb学习.assets/image-20251231173334565.png)

---

### 2.3.1 Spring Boot



#### 2.3.1.1 三层架构与分层解耦

在 Java Web 开发中，我们遵循**“三层架构”**的设计模式。
这就好比经营一家大餐厅，不能让老板一个人既当服务员、又当厨师、还去买菜。必须**分工明确**，才能高效运转。

> **Controller 只管接待，Service 只管做饭，Dao 只管仓库，Pojo 就是流转的菜。**

---

##### 各层级详细解析

| 模块名称                       | 餐厅比喻 🏪           | 核心职责 (干什么) 🛠️                                          |
| :----------------------------- | :------------------- | :----------------------------------------------------------- |
| **Pojo** <br> (Entity/Model)   | **食材 / 菜品** 🥬    | **数据载体**。<br>不含逻辑，只负责在各层之间传递数据（属性 + get/set）。 |
| **Controller** <br> (Web 层)   | **服务员 / 前台** 💁‍♂️ | **接待与响应**。<br>1. 接收浏览器请求。<br>2. 指挥 Service 干活。<br>3. 把结果返回给用户。 |
| **Service** <br> (业务层)      | **厨师 / 大厨** 👨‍🍳   | **业务逻辑处理**。<br>系统的“大脑”，负责判断、计算、加工数据。<br>*(最复杂的代码都在这)* |
| **Dao / Mapper** <br> (持久层) | **仓库管理员** 📦     | **数据存取**。<br>只负责与数据库打交道（增删改查），不管业务逻辑。 |

---
**🔄 调用链：**
浏览器请求 ➡️ **服务员 (Controller)** ➡️ **厨师 (Service)** ➡️ **仓库 (Dao)** ➡️ 数据库

---

#### 2.3.1.2 SpringBoot案例

##### Lombok 与构造器

在实体类（Pojo/Entity）中，我们通常**“无脑”**加上这三个注解，统称为“Lombok 三件套”：

| 注解                      | 作用                                                         | 备注                               |
| :------------------------ | :----------------------------------------------------------- | :--------------------------------- |
| **`@Data`**               | 自动生成 `get`、`set`、`toString`、`equals`、`hashCode` 方法 | 省去几十行样板代码，让类保持清爽。 |
| **`@NoArgsConstructor`**  | 自动生成 **无参构造器**                                      | `public User() {}`                 |
| **`@AllArgsConstructor`** | 自动生成 **全参构造器**                                      | `public User(all args...) {}`      |

---

###### (1) 无参构造器 (`NoArgsConstructor`)

*   **含义**：创建一个“空对象”，不带任何初始数据。
*   **代码体现**：
    ```java
    // 就像拿了一个空碗
    User u = new User(); 
    // 后期再往碗里夹菜（赋值）
    u.setName("张三");
    ```
*   **核心用途**：
    *   **灵活性**：先创建，后赋值。
    *   **框架强制要求**：Spring 和 MyBatis 等框架在底层读取数据库数据时，第一步都是先调用无参构造器实例化对象，如果没这个构造器，程序会**直接报错**。

###### (2) 全参构造器 (`AllArgsConstructor`)
*   **含义**：创建对象的同时，把所有属性都赋好值。
*   **代码体现**：
    ```java
    // 就像端出一碗盛好的饭
    User u = new User(1, "张三", 18);
    ```
*   **核心用途**：
    *   **开发便捷**：一行代码搞定对象创建和赋值，常用于测试数据的编写。

---

###### (3) 重点：如果不使用 Lombok，代码该怎么写？

如果你删掉了 `@NoArgsConstructor` 和 `@AllArgsConstructor`，你需要手写以下代码才能达到同样的效果。

```java
public class User {
    private String name;
    private Integer age;

    // ==========================================
    // 1. 手写无参构造器 (对应 @NoArgsConstructor)
    // ==========================================
    public User() {
        // 里面是空的，什么都不用写，但必须要有这就话
    }

    // ==========================================
    // 2. 手写全参构造器 (对应 @AllArgsConstructor)
    // ==========================================
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // ... 下面还得手写 Getter/Setter/ToString ...
}
```

---

###### (4) 必须要知道的“Java 潜规则”

1.  如果你在类里**什么构造器都不写**，Java 编译器会默认送你一个“无参构造器”。
2.  一旦你写了（或用 Lombok 生成了）一个**全参构造器**，Java 就会**收回**那个默认送的无参构造器。
3.  **后果**：
    *   如果你只加了 `@AllArgsConstructor` 而没加 `@NoArgsConstructor`。
    *   此时你想 `new User()`，或者让 MyBatis 查数据库，程序会报错提示：“找不到无参构造方法”。

**最佳实践结论**：写实体类时，**`@Data` + `@NoArgsConstructor` + `@AllArgsConstructor`** 这三个一起加，永远不会出错！



