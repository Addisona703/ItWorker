# ItWorker
##### [...]

---

## 项目技术栈

### 前端:
- ![Vue](https://img.shields.io/badge/Vue-4FC08D?style=flat&logo=Vue.js&logoColor=white)
- ![Axios](https://img.shields.io/badge/Axios-5A29E4?style=flat&logo=Axios&logoColor=white)
- ![Element](https://img.shields.io/badge/Element-4A7BF7?style=flat&logo=Element&logoColor=white)

### 后端:
- ![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=Spring%20Boot&logoColor=white)
- ![MyBatis-Plus](https://img.shields.io/badge/MyBatis--Plus-000000?style=flat&logo=MyBatis&logoColor=white)
- ![JWT](https://img.shields.io/badge/JWT-000000?style=flat&logo=JSON%20Web%20Tokens&logoColor=white)
- ![Bcrypt](https://img.shields.io/badge/Bcrypt-05122A?style=flat&logo=Keycloak&logoColor=white)
- ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=Swagger&logoColor=white)
- ![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat&logo=Redis&logoColor=white)

---

## 前端开发流程

1. 项目初始化：配置开发环境
2. 引入依赖：包括 Vue、Axios 和 Element 等
3. 页面开发：完成页面设计及组件功能开发
4. 接口对接：调用后端接口并处理响应
5. 部署与优化：项目打包、优化静态资源

---

## 后端开发流程

### 已实现功能：
- **Token 验证**：基于 JWT 实现用户认证机制
- **密码加密**：采用 Bcrypt 增强密码安全性
- **文件上传**：支持文件的上传与存储
- **接口文档**：集成 Swagger（安全策略与认证功能尚未实现）
- **Redis 集成**：目前用于存储 Token

### 待实现功能：
- **日志管理**：记录系统运行与错误日志
- **其他功能**：待进一步补充和规划

### 开发流程：
1. **项目初始化**：新建项目并创建数据库及数据表
2. **依赖导入**：配置项目依赖与基础环境
3. **自定义配置**：创建并配置异常类与统一返回类
4. **分层开发**：
    - `Mapper` 层
    - `Service` 层
    - `Controller` 层
5. **功能扩展**：
    - 集成 JWT 完成用户认证与授权
    - 使用 Bcrypt 实现密码加密
    - 添加文件上传与异步任务处理功能
6. **跨域与文档支持**：
    - 配置跨域访问 (CORS)
    - 集成 SpringDoc (Swagger) 生成接口文档
7. **Redis 集成**：
    - 登录时存储 Token
    - 登出时将 Token 拉入黑名单

---

API文档地址：http://localhost:8080/docs/swagger-ui/index.html
