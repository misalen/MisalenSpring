>基本工具：JDK、Eclipse、Maven<br>
>前端：Jquery、bootstrap.js、layui.js<br>
>后台核心框架：SpringBoot（Sprnig、SpringMvc）、JPA（Hibernate）<br>
>后台辅助框架：日志框架Logback、安全框架Shiro、任务调度框架Quartz、HTML模板框架Thymeleaf、缓存支持ehcache、单点cas<br>
>分布式框架：暂缺<br>

项目采用maven分模块结构、插件化设计，支持分包和集中部署，各个模块可简单插拔。配置文件可统一使用，也可以单独使用。支持测试环境和正式环境区分打包，`简单的用户角色权限管理`，`在线表单设计器`，`多种方式的代码生成`。
### 已确定结构
[^_^]:![结构图](http://zhaoguochao.com/images/msialenbootstructure.png)
>`misalen-spring-config`:配置中心<br>
>`misalen-spring-common`:常用工具包+基础支撑<br>
>`misalen-spring-core`:与第三方对接的服务（微信支付，短信服务等）<br>
>`misalen-spring-db-jpa`:持久化基础，数据库操作基础<br>
>`misalen-spring-system`:系统设置（管理员、角色、用户管理等）<br>
>`misalen-spring-generator`:自动化（代码生成、表单设计器、流程设计器）<br>
>`misalen-spring-cas`:单点登录<br>
>`misalen-spring-workflow`:集成activiti工作流<br>
>`misalen-spring-poi`:office组件操作工具包<br>

### 将持续增加
>`misalen-spring-logback`:日志服务中心<br>
>`misalen-spring-crawler`:可订阅爬虫服务<br>
>`misalen-spring-test`:自动化测试、测试用例、测试文档、postman脚本<br>
域名备案审核中，暂不可用
详细说明会在博客中持续更新 http://zhaoguochao.com/tags/Misalen/
