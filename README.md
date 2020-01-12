# blogSpringBoot
## 项目介绍
#### 该项目是个人博客项目，采用Vue + SpringBoot开发。
#### 后台管理页面使用Vue编写，其他页面使用Thymeleaf模板。

------------

项目演示地址：http://sjpeng.top/
项目后台地址：http://sjpeng.top:8088/

------------

## 技术栈
#### 后端技术栈
- Spring Boot
- Shiro+Jwt
- MyBatis
- MySQL
- Redis

#### 前端技术栈
- Vue
- ElementUI
- axios
- vue-router
- Vuex
- WebSocket
- vue-cli4
------------


## 上手指南
### 服务端启动
1. 克隆项目到本地 `git clone https://github.com/lurenha/blogSpringBoot.git`
1. 找到项目中resources目录下的myblog.sql文件，在MySQL数据库中执行(需要Mysql版本5.7及以上).
1. 启动Redis服务(Redis和Mysql配置在项目的application.properties中)
1. 在IntelliJ IDEA中运行本项目
1. 至此，服务端就启动成功了，此时我们直接在地址栏输入 http://localhost:8080/peng 即可访问我们的项目
------------
### Vue后台管理服务启动
##### 请参考 [https://github.com/lurenha/blogAdminVue](https://github.com/lurenha/blogAdminVue "https://github.com/lurenha/blogAdminVue")

------------
## License
欢迎小伙伴们star、fork。
