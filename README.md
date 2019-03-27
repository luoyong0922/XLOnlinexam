# XLOnlinexam  课堂测评系统

## 项目简介

本系统是在SpringBoot基础上搭建的一个Java Web项目，
以Spring MVC为模型视图控制器，MyBatis为数据访问层， 
Spring-Security为权限授权层，
MySQL进行存储，使用Maven做项目管理，提高项目的易开发性、扩展性。

## 内置功能

* 角色管理：
   * 角色的基础功能以及角色分配权限（本系统共有三个角色：管理员、老师、学生）。
* 用户管理：
   * 处理用户的登录、注册，密码加密存储，修改个人信息，忘记密码等。
   * 学生和老师可以自行注册，但必须通过管理员审核后才能登录系统。
   * 如果用户忘记了自己的登录密码，可以根据正确的密保问题回答找回密码。
   * 用户登录成功后，可以查看并修改个人信息。
* 课程管理：
    * 课程管理模块不仅仅包含管理员对课程信息的增、删、改、查，
       还有管理员对授课老师的工作分配，
    * 老师根据所授课程发布课后作业和通知，新增测评要求，
    * 学生选择课程以及查看课后作业通知，进入测评等。
    * 除此以外，老师和学生还可以参与课程在线实时讨论。
* 题库管理：
    * 老师根据课程信息添加、查看、修改、删除试题信息，
    * 每位老师根据课程的不同都拥有属于自己的题库。
    * 管理员查看试题信息，
    * 学生参加测试时从题库中随机抽取老师预设的题量。
* 成绩管理：
    * 系统自动批改客观题，
    * 老师批阅主观题后，系统统计学生测试成绩，
    * 对同一测评的测试成绩进行分析，形成测评成绩排名和成绩统计图。
    * 登录的用户都可以查看成绩排名和统计图。
* 日志管理：
    * 系统正常操作日志记录；
    * 系统异常信息日志记录。

## 技术选型

### 后端

* 核心框架：SpringBoot 2.1.0.RELEASE
* 集成运行框架：内置Tomcat 2.1.0.RELEASE
* 安全框架：Spring-Security 5.1.1.RELEASE
* 视图框架：Spring MVC 5.1.2.RELEASE
* 网络通信协议框架：WebSocket 2.3.3
* 布局框架：FreeMarker 2.3.28
* 持久层框架：MyBatis 3.4.6
* 数据处理框架：MySQL 5.1.46
* 数据库连接池：Alibaba Druid 1.1.10
* 日志管理：Log4j 2.11.1、slf4j 1.7.25、logback 1.2.3
* MyBatis 分页插件：PageHelper 1.2.7
* 工具类：Apache Commons、fastjson 1.2.3、gson 2.7

### 前端

* JS库：jQuery 1.11.0、echarts 2.0、Bootstrap v3.3.0
* CSS库：Bootstrap v3.3.6
* 工具类框架：X-admin 2.0

### 平台相关

* 服务器中间件：项目采用SpringBoot内置的Tomcat，运行项目是只需启动 SpringbootApplication。
* 数据库支持：目前仅提供MySql数据库的支持，数据库文件位于项目根目录下的 onlinexam.sql。
* 开发环境：Java1.8以上、IDEA、Maven 3.1以上、Git

### 安全考虑

* 开发语言：系统采用Java 语言开发，具有卓越的通用性、高效性、平台移植性和安全性。
* 分层设计：（数据库层，数据访问层，业务逻辑层，展示层）层次清楚，低耦合，各层必须通过接口才能接入，保证数据操作的安全。
* 双重验证：用户表单提交双验证：包括服务器端验证及客户端验证，防止用户跳过客户端验证操作数据库。
* 密码加密：登录用户密码进行SHA1散列加密，此加密方法是不可逆的。保证密文泄露后的安全问题。
* 强制访问：系统对所有管理端链接都进行用户身份权限验证，防止用户直接填写url进行访问。

## 功能预览

* 系统首页
![icon](./src/main/resources/static/screenshot/index.png?raw=true)
* 关于我们
![icon](./src/main/resources/static/screenshot/aboutUs.png?raw=true)
* 注册页面
![icon](./src/main/resources/static/screenshot/regist.png?raw=true)
* 登录页面
![icon](./src/main/resources/static/screenshot/login.png?raw=true)
* 登录初始化页面
![icon](./src/main/resources/static/screenshot/loginInit.png?raw=true)
* 管理员首页
![icon](./src/main/resources/static/screenshot/adminIndex.png?raw=true)
* 管理员页面
![icon](./src/main/resources/static/screenshot/adminPage.png?raw=true)
* 老师首页
![icon](./src/main/resources/static/screenshot/teacherIndex.png?raw=true)
* 成绩统计页面
![icon](./src/main/resources/static/screenshot/grade.png?raw=true)
![icon](./src/main/resources/static/screenshot/grade2.png?raw=true)
* 学生首页
![icon](./src/main/resources/static/screenshot/studentIndex.png?raw=true)
* 学生修改密码
![icon](./src/main/resources/static/screenshot/modifyPWD.png?raw=true)
* 讨论页面
![icon](./src/main/resources/static/screenshot/chatting.png?raw=true)

## 快速体验

1. 具备运行环境：JDK 1.8+、Maven 3.0+、MySql 5+。
2. 修改src\main\resources\application.properties文件中的数据库连接参数。
3. 根据修改参数创建对应MySql数据库用户和参数。
4. onlinexam.sql导入本地数据库即可
5. 运行SpringbootApplication启动类， 即可本地预览
6. 管理员账号，用户名：110 密码：123

## 版权声明

你可以以任何方式获得，你可以修改包名或类名，**但还请注明文件作者信息：roy**。
