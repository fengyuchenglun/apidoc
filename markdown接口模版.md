[TOC]





# 系统管理

## 1. 用户接口

与用户相关接口。

### 1.1 添加用户

添加用户

 **请求**

```HTTP
POST /api/v1/users HTTP/1.1

{
    "loginId":"admin",
    "userName":"123456",
    "passwords":"123456"
}
```

| 字段    | 类型   | 参数类型 | 是否必填 | 默认值 | 描述 |
| :------- | :----- | ---------- | :-------- | :------ | :---------- |
| loginId  | String | Body       | 必填      |         | 用户编号    |
| userName | String | Body       | 必填      |         | 用户名      |
| password | String | Body       | 必填      |         | 密码        |

 **响应**

```
{
    "loginId":"admin",
    "userName":"123456",
    "passwords":"123456"
}
```

| 字段     | 类型   | 描述 |
| :------- | :----- | :---------- |
| loginId  | String | 用户编号    |
| userName | String | 用户名      |
| password | String | 密码        |

### 1.2 查询用户列表

查询用户列表

 **请求**

```HTTP
GET /api/v1/users?userName=XXX&sex=XXX HTTP/1.1E
```

| 字段    | 类型   | 参数类型 | 是否必填 | 默认值 | 描述 |
| :------- | :----- | :-------- | :------ | :---------- | ----------- |
| userName | String | QUERY |  |     |用户名|
| sex | String | QUERY |         |       |性别|

 **响应**

```
{
    "loginId":"admin",
    "userName":"123456",
    "passwords":"123456"
}
```

| 字段     | 类型   | 描述 |
| :------- | :----- | :---------- |
| loginId  | String | 用户编号    |
| userName | String | 用户名      |
| password | String | 密码        |


### 1.3  删除用户

删除用户

 **请求**

```HTTP
DELETE /api/v1/users/:userId HTTP/1.1E
```

| 字段    | 类型   | 参数类型 | 是否必填 | 默认值 | 描述 |
| :------- | :----- | :-------- | :------ | :---------- | ----------- |
| userId | String | PATH |  |     |用户编号|

 **响应**

```
{
    "data":"true"
}
```

| 字段     | 类型   | 描述 |
| :------- | :----- | :---------- |
| data  | String | 是否成功    |

