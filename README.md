# blogSpringBoot
## 项目介绍
#### 该项目是个人博客项目，采用Vue + SpringBoot开发。
#### 后台管理页面使用Vue编写，其他页面使用Thymeleaf模板。
#### 项目演示地址：http://lurenpeng.cn/
#### 项目后台地址：http://lurenpeng.cn:8088/

------------
#### 0.2 对项目进行了重构
- 使用MybatisPlus简化数据库操作
- 对用户端大部分目录做了缓存
- 支持管理员在线分配角色权限
- 修复Bug若干

------------

## 技术栈
#### 后端技术栈
- Spring Boot
- Shiro+Jwt
- MyBatisPlus
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
##### 请参考 [https://github.com/lurenha/MyBlogVue](https://github.com/lurenha/MyBlogVue "https://github.com/lurenha/MyBlogVue")

------------

### 鸣谢

#### 该项目参考了曾中杰的 https://github.com/Mretron/MyBlogWebSite
#### SpringBoot整合Shiro参考 https://github.com/Heeexy/SpringBoot-Shiro-Vue
#### 该项目参考了vue-element-admin https://github.com/PanJiaChen/vue-element-admin
#### 该项目参考了若依后台管理 https://gitee.com/y_project/RuoYi-Vue


------------
## License
欢迎小伙伴们star、fork。

## 项目截图

![1](http://sjpeng.top/indexlist.png)

![2](http://sjpeng.top/blog01.png)

![3](http://sjpeng.top/role.png)

![4](http://sjpeng.top/blogcontent.png)

![5](http://sjpeng.top/bloglist.png)






