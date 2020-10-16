[TOC]
# 欢迎使用ApiDoc

#  文档说明


# 接口 1.0.0

## 1.  认证模块.
### 1.1 获取认证信息.

**请求**

```HTTP
HTTP/1.1
token: {value}

Url:
POST /restdoc/auth

```
**响应**
```
{
  "code" : 0,
  "msg" : "",
  "data" : {
    "name" : "",
    "age" : 0,
    "createAt" : "",
    "Sex" : ""
  }
}
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| code  | Integer | 0 | 0 | 返回码 |
| msg  | String |  |  | 返回信息. |
| data  | User |  |  | result data |
| data.name  | String |  |  | 用户名称. |
| data.age  | Integer | 0 | 20 | 用户年龄. |
| data.createAt  | Date |  | 2020-07-28 | 创建时间. |
| data.Sex  | String |  |  | 性别. |

## 2.  home模块.
### 2.1 hello接口.

**请求**

```HTTP
HTTP/1.1

Url:
GET /home

Query param:
?name=xxx
```

| 字段    | 类型  | 参数类型  | 必填 | 验证 | 默认 | 描述 |
| :------- | :----- | :----- |:-------- |:-------- | :------ | :---------- |
| name  | String | **查询参数** |false |  |  | 名称 |
**响应**
```
{
  "id" : 0,
  "content" : ""
}
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| id  | Long | 0 | 0 | 编号 |
| content  | String |  |  | 内容 |

## 3.  其他.
### 3.1 Hello with ResponseBody

 *********
由于带有@ResponseBody，restdoc将解析该Endpoint
<p>
hhh
\*********
 *********
hhhh
*********
<p>
class ************** {
<p>
}

**请求**

```HTTP
HTTP/1.1

Url:
GET /other/hello

```
**响应**
```
{
  "userId" : 0,
  "userName" : "",
  "avatar" : "",
  "effDate" : "",
  "expDate" : "",
  "mobile" : "",
  "fixedPhone" : "",
  "email" : "",
  "userState" : 0,
  "lockState" : 0,
  "userRoleRelationList" : [ 0 ]
}
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| userId  | Integer | 0 | 0 | 用户编号. |
| userName  | String |  | duanledexianxianxian | 用户名. |
| avatar  | String |  |  | 用户头像. |
| effDate  | DateTime |  |  | 账号生效时间<br>保留 暂时不用 |
| expDate  | DateTime |  |  | 账号失效时间<br>保留 暂时不用 |
| mobile  | String |  |  | 手机号码. |
| fixedPhone  | String |  |  | 固定号码. |
| email  | String |  |  | 邮箱. |
| userState  | Integer | 0 | 0 | 用户状态<br>0 无效 1有效 |
| lockState  | Integer | 0 | 0 | 锁定状态<br>0-未锁  1-锁定 |
| userRoleRelationList  | Integer[] |  |  | 用户角色编号列表. |
### 3.2 未知的多泛型的tuple 演示

**请求**

```HTTP
HTTP/1.1

Url:
GET /other/tuple

```
**响应**
```
{
  "a" : {
    "userId" : 0,
    "userName" : "",
    "avatar" : "",
    "effDate" : "",
    "expDate" : "",
    "mobile" : "",
    "fixedPhone" : "",
    "email" : "",
    "userState" : 0,
    "lockState" : 0,
    "userRoleRelationList" : [ 0 ]
  },
  "b" : {
    "id" : 0,
    "name" : "",
    "age" : 0,
    "birthday" : "",
    "tags" : [ "" ],
    "data" : [ ],
    "icons" : [ "" ],
    "attrs" : {
      "" : ""
    }
  }
}
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| a  | UserForm |  |  | 用户表单对象. |
| a.userId  | Integer | 0 | 0 | 用户编号. |
| a.userName  | String |  |  | 用户名. |
| a.avatar  | String |  |  | 用户头像. |
| a.effDate  | DateTime |  |  | 账号生效时间<br>保留 暂时不用 |
| a.expDate  | DateTime |  |  | 账号失效时间<br>保留 暂时不用 |
| a.mobile  | String |  |  | 手机号码. |
| a.fixedPhone  | String |  |  | 固定号码. |
| a.email  | String |  |  | 邮箱. |
| a.userState  | Integer | 0 | 0 | 用户状态<br>0 无效 1有效 |
| a.lockState  | Integer | 0 | 0 | 锁定状态<br>0-未锁  1-锁定 |
| a.userRoleRelationList  | Integer[] |  |  | 用户角色编号列表. |
| b  | UserDTO |  |  |  |
| b.id  | Integer | 0 | 0 | 编号 |
| b.name  | String |  |  | 姓名 |
| b.age  | Integer | 0 | 0 | 年龄 |
| b.birthday  | Date |  |  | 生日，还是推荐使用javadoc |
| b.tags  | String[] |  |  | 用户标签 |
| b.data  | [] |  |  |  |
| b.icons  | String[] |  |  | 用户图标 |
| b.attrs  |  |  |  |  |
### 3.3 多个RequestMethod

暂时只支持取第一个

**请求**

```HTTP
HTTP/1.1

Url:
GET /other/multiMethod

```
**响应**
```
{
  "code" : 0,
  "msg" : ""
}
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| code  | Integer | 0 | 0 | 返回码 |
| msg  | String |  |  | 返回信息. |
### 3.4 多层返回结果.

**请求**

```HTTP
HTTP/1.1
Content-Type: application/json

Url:
POST /other/multi


Request body:
{
  "code" : 0,
  "msg" : "",
  "data" : {
    "wrapper" : "",
    "data" : [ {
      "id" : 0,
      "name" : "",
      "age" : 0,
      "birthday" : "",
      "tags" : [ "" ],
      "data" : [ ],
      "icons" : [ "" ],
      "attrs" : {
        "" : ""
      }
    } ]
  }
}
```

| 字段    | 类型  | 参数类型  | 必填 | 验证 | 默认 | 描述 |
| :------- | :----- | :----- |:-------- |:-------- | :------ | :---------- |
| code  | Integer | **请求体** |false |  | 0 | 返回码 |
| msg  | String | **请求体** |false |  |  | 返回信息. |
| data  | Wrapper | **** |false |  |  | The type Wrapper. |
| data.wrapper  | String | **请求体** |false |  |  | The Wrapper. |
| data.data  | UserDTO[] | **请求体** |false |  |  | The Data. |
| data.data.id  | Integer | **请求体** |false |  | 0 | 编号 |
| data.data.name  | String | **请求体** |false | NotEmpty  |  | 姓名 |
| data.data.age  | Integer | **请求体** |false |  | 0 | 年龄 |
| data.data.birthday  | Date | **请求体** |false | NotNull  |  | 生日，还是推荐使用javadoc |
| data.data.tags  | String[] | **请求体** |false |  |  | 用户标签 |
| data.data.data  | [] | **请求体** |false |  |  |  |
| data.data.icons  | String[] | **请求体** |false |  |  | 用户图标 |
| data.data.attrs  |  | **** |false |  |  |  |
**响应**
```
{
  "code" : 0,
  "msg" : "",
  "data" : {
    "wrapper" : "",
    "data" : {
      "id" : 0,
      "name" : "",
      "age" : 0,
      "birthday" : "",
      "tags" : [ "" ],
      "data" : [ ],
      "icons" : [ "" ],
      "attrs" : {
        "" : ""
      }
    }
  }
}
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| code  | Integer | 0 | 0 | 返回码 |
| msg  | String |  |  | 返回信息. |
| data  | Wrapper |  |  | the result data |
| data.wrapper  | String |  |  | The Wrapper. |
| data.data  | UserDTO |  |  | The Data. |
| data.data.id  | Integer | 0 | 0 | 编号 |
| data.data.name  | String |  |  | 姓名 |
| data.data.age  | Integer | 0 | 0 | 年龄 |
| data.data.birthday  | Date |  |  | 生日，还是推荐使用javadoc |
| data.data.tags  | String[] |  |  | 用户标签 |
| data.data.data  | [] |  |  |  |
| data.data.icons  | String[] |  |  | 用户图标 |
| data.data.attrs  |  |  |  |  |
### 3.5 引用二方Jar

使用二方Jar的类时，代码解析器无法获取类上的注释，注解
只能获取属性的名称和类型

**请求**

```HTTP
HTTP/1.1
Content-Type: application/json

Url:
POST /other/jar


Request body:
{
  "page" : 0,
  "size" : 0,
  "name" : ""
}
```

| 字段    | 类型  | 参数类型  | 必填 | 验证 | 默认 | 描述 |
| :------- | :----- | :----- |:-------- |:-------- | :------ | :---------- |
| page  | Integer | **请求体** |false |  | 0 |  |
| size  | Integer | **请求体** |false |  | 0 |  |
| name  | String | **请求体** |false |  |  |  |
**响应**
```
{
  "id" : 0,
  "name" : "",
  "man" : ""
}
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| id  | Integer | 0 | 0 |  |
| name  | String |  |  |  |
| man  | String |  |  |  |
### 3.6 一个复杂的类型 List<Map<String,User>>

**请求**

```HTTP
HTTP/1.1

Url:
GET /other/map

```
**响应**
```
[ {
  "" : {
    "userId" : 0,
    "userName" : "",
    "avatar" : "",
    "effDate" : "",
    "expDate" : "",
    "mobile" : "",
    "fixedPhone" : "",
    "email" : "",
    "userState" : 0,
    "lockState" : 0,
    "userRoleRelationList" : [ 0 ]
  }
} ]
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| userId  | Integer | 0 | 0 | 用户编号. |
| userName  | String |  | duanledexianxianxian | 用户名. |
| avatar  | String |  |  | 用户头像. |
| effDate  | DateTime |  |  | 账号生效时间<br>保留 暂时不用 |
| expDate  | DateTime |  |  | 账号失效时间<br>保留 暂时不用 |
| mobile  | String |  |  | 手机号码. |
| fixedPhone  | String |  |  | 固定号码. |
| email  | String |  |  | 邮箱. |
| userState  | Integer | 0 | 0 | 用户状态<br>0 无效 1有效 |
| lockState  | Integer | 0 | 0 | 锁定状态<br>0-未锁  1-锁定 |
| userRoleRelationList  | Integer[] |  |  | 用户角色编号列表. |
### 3.7 一个更复杂的类型 List<Map<String,ResultData<Map<Integer,User>>>>

**请求**

```HTTP
HTTP/1.1

Url:
GET /other/map

```
**响应**
```
[ {
  "" : {
    "code" : 0,
    "msg" : "",
    "data" : {
      "" : {
        "name" : "",
        "age" : 0,
        "createAt" : "",
        "Sex" : ""
      }
    }
  }
} ]
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| code  | Integer | 0 | 0 | 返回码 |
| msg  | String |  |  | 返回信息. |
| data  |  |  |  |  |
| data.name  | String |  |  | 用户名称. |
| data.age  | Integer | 0 | 20 | 用户年龄. |
| data.createAt  | Date |  | 2020-07-28 | 创建时间. |
| data.Sex  | String |  |  | 性别. |
### 3.8 一个问号类型 List<Map<String,List<?>>>

**请求**

```HTTP
HTTP/1.1

Url:
GET /other/map

```
**响应**
```
[ {
  "" : [ ]
} ]
```

## 4.  UserController
### 4.1 删除用户.

**请求**

```HTTP
HTTP/1.1

Url:
DELETE /api/v1/users/:userId

```

| 字段    | 类型  | 参数类型  | 必填 | 验证 | 默认 | 描述 |
| :------- | :----- | :----- |:-------- |:-------- | :------ | :---------- |
| userId  | Long | **路径参数** |true |  |  | 用户编号 |
**响应**
```
false
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| result  | Boolean | false | false | the user |
### 4.2 修改用户对象.

**请求**

```HTTP
HTTP/1.1
Content-Type: application/json

Url:
PUT /api/v1/users


Request body:
{
  "userId" : 0,
  "userName" : "",
  "avatar" : "",
  "effDate" : "",
  "expDate" : "",
  "mobile" : "",
  "fixedPhone" : "",
  "email" : "",
  "userState" : 0,
  "lockState" : 0,
  "userRoleRelationList" : [ 0 ]
}
```

| 字段    | 类型  | 参数类型  | 必填 | 验证 | 默认 | 描述 |
| :------- | :----- | :----- |:-------- |:-------- | :------ | :---------- |
| userId  | Integer | **请求体** |false | NotNull  | 0 | 用户编号. |
| userName  | String | **请求体** |false | NotBlank  |  | 用户名. |
| avatar  | String | **请求体** |false |  |  | 用户头像. |
| effDate  | DateTime | **请求体** |false |  |  | 账号生效时间<br>保留 暂时不用 |
| expDate  | DateTime | **请求体** |false |  |  | 账号失效时间<br>保留 暂时不用 |
| mobile  | String | **请求体** |false |  |  | 手机号码. |
| fixedPhone  | String | **请求体** |false |  |  | 固定号码. |
| email  | String | **请求体** |false |  |  | 邮箱. |
| userState  | Integer | **请求体** |false | NotNull  | 0 | 用户状态<br>0 无效 1有效 |
| lockState  | Integer | **请求体** |false | NotNull  | 0 | 锁定状态<br>0-未锁  1-锁定 |
| userRoleRelationList  | Integer[] | **请求体** |false | NotEmpty  |  | 用户角色编号列表. |
**响应**
```
false
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| result  | Boolean | false | false | 是否成功 boolean |
### 4.3 添加用户.

**请求**

```HTTP
HTTP/1.1
Content-Type: application/json

Url:
POST /api/v1/users


Request body:
{
  "userId" : 0,
  "userName" : "",
  "avatar" : "",
  "effDate" : "",
  "expDate" : "",
  "mobile" : "",
  "fixedPhone" : "",
  "email" : "",
  "userState" : 0,
  "lockState" : 0,
  "userRoleRelationList" : [ 0 ]
}
```

| 字段    | 类型  | 参数类型  | 必填 | 验证 | 默认 | 描述 |
| :------- | :----- | :----- |:-------- |:-------- | :------ | :---------- |
| userId  | Integer | **请求体** |false | NotNull  | 0 | 用户编号. |
| userName  | String | **请求体** |false | NotBlank  |  | 用户名. |
| avatar  | String | **请求体** |false |  |  | 用户头像. |
| effDate  | DateTime | **请求体** |false |  |  | 账号生效时间<br>保留 暂时不用 |
| expDate  | DateTime | **请求体** |false |  |  | 账号失效时间<br>保留 暂时不用 |
| mobile  | String | **请求体** |false |  |  | 手机号码. |
| fixedPhone  | String | **请求体** |false |  |  | 固定号码. |
| email  | String | **请求体** |false |  |  | 邮箱. |
| userState  | Integer | **请求体** |false | NotNull  | 0 | 用户状态<br>0 无效 1有效 |
| lockState  | Integer | **请求体** |false | NotNull  | 0 | 锁定状态<br>0-未锁  1-锁定 |
| userRoleRelationList  | Integer[] | **请求体** |false | NotEmpty  |  | 用户角色编号列表. |
**响应**
```
0
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| result  | Long | 0 | 0 | 用户编号 long |
### 4.4 查看用户详情

**请求**

```HTTP
HTTP/1.1

Url:
GET /api/v1/users/:userId

```

| 字段    | 类型  | 参数类型  | 必填 | 验证 | 默认 | 描述 |
| :------- | :----- | :----- |:-------- |:-------- | :------ | :---------- |
| userId  | Long | **路径参数** |true |  |  | 用户编号 |
**响应**
```
{
  "userId" : 0,
  "userName" : "",
  "avatar" : "",
  "effDate" : "",
  "expDate" : "",
  "mobile" : "",
  "fixedPhone" : "",
  "email" : "",
  "userState" : 0,
  "lockState" : 0,
  "userRoleRelationList" : [ 0 ]
}
```

| 字段    | 类型 | 默认 | 示例  | 描述 |
| :------- | :----- |:----- |:----- | :---------- |
| userId  | Integer | 0 | 0 | 用户编号. |
| userName  | String |  | duanledexianxianxian | 用户名. |
| avatar  | String |  |  | 用户头像. |
| effDate  | DateTime |  |  | 账号生效时间<br>保留 暂时不用 |
| expDate  | DateTime |  |  | 账号失效时间<br>保留 暂时不用 |
| mobile  | String |  |  | 手机号码. |
| fixedPhone  | String |  |  | 固定号码. |
| email  | String |  |  | 邮箱. |
| userState  | Integer | 0 | 0 | 用户状态<br>0 无效 1有效 |
| lockState  | Integer | 0 | 0 | 锁定状态<br>0-未锁  1-锁定 |
| userRoleRelationList  | Integer[] |  |  | 用户角色编号列表. |
### 4.5 获取用户列表-分页.

**请求**

```HTTP
HTTP/1.1

Url:
GET /api/v1/users

Query param:
?pageNum=0&pageSize=0&sortField=xxx&sortOrder=xxx&userName=xxx&userState=0&lockState=0
```

| 字段    | 类型  | 参数类型  | 必填 | 验证 | 默认 | 描述 |
| :------- | :----- | :----- |:-------- |:-------- | :------ | :---------- |
| pageNum  | Long | **查询参数** |false |  | 1L | 分页页码. |
| pageSize  | Long | **查询参数** |false |  | 10L | 分页大小. |
| sortField  | String | **查询参数** |false |  |  | 排序字段 |
| sortOrder  | String | **查询参数** |false |  |  | 排序顺序<br>descend-降序<br>ascend-升序<br>null-不排序 |
| userName  | String | **查询参数** |false |  |  | 用户名. |
| userState  | Integer | **查询参数** |false |  | 0 | 用户状态. |
| lockState  | Integer | **查询参数** |false |  | 0 | 锁定状态. |
**响应**
```
{ }
```




# 附录
