快速搭建spring boot项目脚手架

一、项目结构

1.
版本 open jdk 17.0.1 springboot 2.5.6(2.6.0对swagger不兼容)

主要分为以下几个层次 parant 负责依赖版本管理，插件版本管理

common 存放工具类 通用常量 通用封装对象 项目异常

framework 系统框架，负责项目的配置，中间件配置等

service 服务层，具体的业务逻辑处理 项目启动入口 controller service mapper

mybatis-plus-generator mp的逆向工厂生成器 不参与项目打包

项目实体类定义规范
VO（View Object） 封装前端和服务的之间传输的对象 

DTO（ Data Transfer Object）数据传输对象 用于controller和service之间，业务处理时的数据传输对象

* 服务端之间的api调用 一律返回DTO对象

PO（Persistent Object）持久化对象 用于业务层和数据库交互

