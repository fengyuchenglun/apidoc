## 简介

### 前言

现在主流的系统，都采用了前后端分离的开发模式。而API文档是前后端沟通的桥梁。

对于API文档输出，现实中我们可能会有以下的感悟：

- 后端代码还未开发完成，文档不能输出，前端等等吧。

- 你还在手动维护接口文档嘛，花一个下午不停的复制粘贴代码里面的注释。
- 接口字段变动，还得去更新文档，更新不及时导致文档不同步。
- 或者你使用了swagger之类的基于注解，依靠运行时的文档工具，看着swagger繁多的注解定义，满屏幕的文档注解，你的强迫症有没有发作。

### 代码即文档

一直以来，程序员都有一个烦恼，只想写代码，不想写文档。`代码就表达了我的思想和灵魂`。

Python提出了一个方案，叫**docstring**，来试图解决这个问题。即编写代码，同时也能写出文档，保持代码和文档的一致。docstring说白了就是一堆代码中的注释。Python的docstring可以通过help函数直接输出一份有格式的文档，本工具的思想与此类似。

`apidoc是一款非侵入，基于注释以及代码一键生成Rest API文档的工具。`

使用apidoc配合IDEA javadoc插件，自动快速生成代码注释以及API接口文档，这是一种飞一般的感觉。

假如有如下Controller代码：

```
package com.github.fengyuchenglun.apidoc.springmvc.example.spring;

import com.github.fengyuchenglun.apidoc.springmvc.example.common.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * home模块.
 *
 * @group home
 */
@RestController
@RequestMapping("/home")
public class HelloController {
    /**
     * hello接口.
     *
     * @param name 名称
     * @return the greeting
     */
    @GetMapping
    public Greeting hello(String name){
        return null;
    }
}

```

Greeting代码如下：

```java
package com.github.fengyuchenglun.apidoc.springmvc.example.common;

/**
 * Greeting返回对象
 */
public class Greeting {

    /**
     * 编号
     */
    private final long id;
    /**
     * 内容
     */
    private final String content;

    /**
     * Instantiates a new Greeting.
     *
     * @param id      the id
     * @param content the content
     */
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }
}
```

通过apidoc，可以生成如下的API文档(截图为markdown文档示例，通过配置选项，可以生成html、adhoc、markdown、postman格式文档)。

![](https://i.loli.net/2020/10/12/4BFfAiXW1MryVYz.png)

### 特征

- 注释一键生成代码。
- 生成多种类型API文档，支持自定义API接口文档。
- 支持统一结果返回。
- 支持部分Map、泛型类型接口文档生成。
- 提升开发效率。

## 快速开始

### 代码方式

引入apidoc maven坐标：

> 2020-10-20,最新版本1.0.12

```xml
<dependency>
		<groupId>com.github.fengyuchenglun</groupId>
		<artifactId>apidoc-springmvc</artifactId>
		<version>最新版本</version>
</dependency>
```

执行以下API文档代码，即可生成api文档：

```java
import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.Context;
import com.github.fengyuchenglun.apidoc.core.common.enums.FieldShowWay;
import com.github.fengyuchenglun.apidoc.core.render.MarkdownRender;
import com.google.common.collect.Lists;

import java.nio.file.Paths;

public class ApiTest {
    public static void main(String[] args) {
        Context context = new Context();
        context.setName("测试项目");
        context.addSource(Paths.get("工程根目录"));
        ApiDoc apiDoc = new ApiDoc(context);
        apiDoc.parse();
        apiDoc.render();
    }
}
```



### Maven插件方式

引入apidoc maven plugin坐标：

> 2020-10-20,最新版本1.0.12

```xml
<plugin>
	<groupId>com.github.fengyuchenglun</groupId>
	<artifactId>apidoc-maven-plugin</artifactId>
	<version>最新版本</version>
</plugin>
```

运行maven执行插件命令

```
mvn com.github.fengyuchenglun:apidoc-maven-plugin:1.0.12:apidoc
```

或者在IDEA中可以使用如下图形化操作：

![](https://i.loli.net/2020/10/20/dZTzXAHbO45RLlm.png)

Maven插件支持的配置参数，基本与**`Context`**可配置参数一致，配置详情请参考[配置项](#config)章节。

### Gradle插件方式

暂未实现，敬请期待。

## 最佳实践

1. IDEA 安装javadoc插件，一键生成符合javadoc规范的注释。

   - Alt+Shift+G：为选择的元素一键生成javadoc注释。
   - Ctrl+Alt+Shift+G：为当前文件内容生成javadoc注释。
   - Alt+Shift+Z：移除当前选择元素的javadoc注释。
   - Ctrl+Alt+Shift+Z:移除当前文件的所有javadoc注释。

   

   ![](https://i.loli.net/2020/10/12/ASowUQFV6M3EvGq.png)

   这样我们可以通过代码自动生成注释，然后通过注释生成API文档，一气呵成，全部自动化。

2. 统一结果返回

   一般统一结果返回有两种方式：

   - Controller中方法返回ResultData<T>

     ```java
      /**
          * 获取认证信息.
          *
          * @param token 上报的身份验证token，jwt
          * @return result data
          */
         @PostMapping
         public ResultData<User> getAuthInfo(@RequestHeader String token) {
             return ResultData.ok();
         }
     ```

     ```java
     package com.github.fengyuchenglun.apidoc.springmvc.example.common;
     
     import lombok.Getter;
     import lombok.Setter;
     
     /**
      * 统一返回结果.
      * 有的项目会封装ResultData类,封装请求返回。
      * 但是在Controller中
      * 1. 调用者需显示声明ResultData返回类型。
      * 2. 调用者需显示创建ResultData对象。
      *
      * @param <T> the type parameter
      */
     @Setter
     @Getter
     public class ResultData<T> {
     
         /**
          * 返回码
          */
         int code;
         /**
          * 返回信息.
          */
         String msg;
         /**
          * The Data.
          * @data
          */
         T data;
     
         /**
          * Ok result data.
          *
          * @param <T> the type parameter
          * @return the result data
          */
         public static <T> ResultData<T> ok() {
             return ok(null);
         }
     
         /**
          * Ok result data.
          *
          * @param <T>  the type parameter
          * @param data the data
          * @return the result data
          */
         public static <T> ResultData<T> ok(T data) {
             ResultData<T> resultData = new ResultData<>();
             resultData.code = 0;
             resultData.msg = "ok";
             resultData.data = data;
             return resultData;
         }
     
     }
     ```

     上述代码，apidoc能够完美的输出api文档。

     ![](https://i.loli.net/2020/10/12/fLwVeGDCtduhbQ7.png)

     > 这里特别注意，因为apidoc静态解析代码以及注释，ResultData后面的泛型<User>不能省略。

   - 通过全局拦截，重写responseBody,Controller中方法返回值为包装内容。

     ```
      /**
          * 获取认证信息.
          *
          * @param token 上报的身份验证token，jwt
          * @return result data
          */
         @PostMapping
         public User getAuthInfo(@RequestHeader String token) {
             return null;
         }
     ```

     ```java
     package com.github.fengyuchenglun.apidoc.springmvc.example.common;
     
     import lombok.Getter;
     import lombok.Setter;
     
     /**
      * 统一返回结果.
      * @resultData
      * @param <T> the type parameter
      */
     @Setter
     @Getter
     public class ResultData<T> {
     
         /**
          * 返回码
          */
         int code;
         /**
          * 返回信息.
          */
         String msg;
         /**
          * The Data.
          * @data
          */
         T data;
     
         /**
          * Ok result data.
          *
          * @param <T> the type parameter
          * @return the result data
          */
         public static <T> ResultData<T> ok() {
             return ok(null);
         }
     
         /**
          * Ok result data.
          *
          * @param <T>  the type parameter
          * @param data the data
          * @return the result data
          */
         public static <T> ResultData<T> ok(T data) {
             ResultData<T> resultData = new ResultData<>();
             resultData.code = 0;
             resultData.msg = "ok";
             resultData.data = data;
             return resultData;
         }
     
     }
     ```

     `@resultData`标识当前类为统一结果类。接口返回的数据将塞入`T data`中。生成文档与上面一样。

   - 接口返回结果包含Map-不推荐。

     虽然apidoc能够解析出部分map结构，但用map作为返回结果会导致代码可读性差，降低代码的可维护性。

##  <span id="config"> 配置项</span>

### 配置总览

| 字段编码        | 默认值                                                   | 描述                                                         |
| --------------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| framework       | springmvc                                                | 框架名称                                                     |
| id              | api                                                      | 项目编号                                                     |
| name            | api                                                      | 名称                                                         |
| description     |                                                          | 描述                                                         |
| version         | 1.0.0                                                    | 版本                                                         |
| buildPath       | build                                                    | 编译目录                                                     |
| sources         |                                                          | 源码目录                                                     |
| dependencies    |                                                          | 依赖源码路径                                                 |
| jars            |                                                          | 依赖jar包，绝对路径.<br>最好使用xxxx-sources.jar             |
| css             |                                                          | 渲染html时的css                                              |
| scanPackages    |                                                          | 解析jar包中的java文件需要扫描的包名                          |
| template        | 默认/templates                                           | 模版文件目录.                                                |
| fileShowWay     | TREE                                                     | 字段列表显示方式<br>FLAT-平级方式。<br>TREE-树级目录         |
| pageClassNames  | 默认^IPage\S*,Page,^PageInfo                             | 统一结果-分页类名称                                          |
| ext             |                                                          | 自定义扩展参数                                               |
| renders         | 默认渲染adhoc、postman、markdown文件                     | 渲染器                                                       |
| scanCode        | false                                                    | 生成附录是否需要使用@code标记<br>当scanCode=false，枚举类没有@code注释也会生成附件. |
| controllers     | Controller、RestController、KimController、NixController | 标识当前接口是否为接口类注解名称                             |
| restControllers | RestController、KimController、NixController             | 标识当前接口是否为rest接口类注解名称                         |

### 使用方式

```java
        Context context = new Context();
        context.setName("测试项目");
        context.setFileShowWay(FieldShowWay.FLAT);

        context.addJar(Paths.get("src/test/resources/lib/kim3-boot-starter-1.1.4-SNAPSHOT-sources.jar"));
//        context.setScanPackages(Sets.newHashSet(Arrays.asList("com.github.fengyuchenglun")));
        context.addSource(Paths.get("src/test/java"));

        ApiDoc apiDoc = new ApiDoc(context);
        apiDoc.parse();
        apiDoc.render();
```

## 自定义Tag

### @book

作用在类上。

作用在类上,根据标签后描述生成文件名称。比如`@book user`,生成user.md。默认为`@book index`,生成index.md

### @group

作用在类上。

作用同@book，请参考@book说明。

### @mock

作用在字段上。

生成字段的示例值。比如：

```
    /**
     * 用户名.
     * @mock duanledexianxianxian
     */
    private String userName;
```

![](https://i.loli.net/2020/10/10/aPGHwIMVj8n1pms.png)

### @index

作用在类、方法上。

定义生成文档接口或者接口模块顺序。按照@index后的数值排列,数值越大优先级越低。

### @data

作用在字段上。

主要用于标识统一结果返回的数据字段。

如果无统一结果返回，可忽略此Tag。如何定义统一结果返回文档示例，参考统一结果返回章节。

### @resultData

作用在类上。

主要用于标识统一结果返回类。

如果无统一结果返回，可忽略此Tag。如何定义统一结果返回文档示例，参考统一结果返回章节。

### @pageResultData

作用在类上。

主要用于标识统一结果返回分页类。

如果无统一结果返回，可忽略此Tag。如何定义统一结果返回文档示例，参考统一结果返回章节。

> 此tag主要用于kim-boot、bix-boot框架

### @code

作用在类上。

主要用于生成附录章节的全局编码说明。当

### @ignore

ignore注释可以作用在类、方法、字段上。

- 作用在类上，将忽略不输出Controller中的接口。
- 作用在方法上，将忽略不输出该接口。
- 作用在字段中，最终文档忽略盖字段。

### @replace

作用在字段上。

用于将子类的字段值提升到父类上展示。比如：

```
public class Parent{
/*
* 名称
*/
String name;

/*
* 子节点
*@replace
*/
Children child;
}

public class Children{
/*
* 子节点名称
*/
String childName;
}
```

没加@replace,生成的结果如下：

```
{
  "name" : "",
  "child" : {
    "childName" : "",
  },
}
```

加@replace,生成的结果如下：

```
{
  "name" : "",
  "childName" : ""
}
```

## FAQ

### 常见问题

1. IDEA中自定义tag自动代码提示以及去除警告。设置操作如下：

   ![](https://i.loli.net/2020/10/12/xPCFqZjELaySbwm.png)

## License

apidoc is under the MIT license. See the [LICENSE](http://platform.kuopu.net:9999/gitlab/duanledexianxianxian/apidoc/-/blob/master/LICENSE) file for details.

## 参考

1. [apigcc](https://github.com/apigcc/apigcc)
2. [genDoc](https://github.com/easycodingnow/genDoc)
3. [javaparser](javaparser)
4. [javaparservisited](https://leanpub.com/javaparservisited)

## 代码仓库

1. [apidoc](https://github.com/fengyuchenglun/apidoc)
2. http://platform.kuopu.net:9999/gitlab/duanledexianxianxian/apidoc

## 贡献

- duanledexianxianxian
- fengyuchenglun

## 研发计划

- [ ] 补充以及完善单元测试。
- [ ] 代码结构优化以及重构。
- [ ] 一个接口支持多个RequestMethod。
- [ ] 一个接口支持对个RequestUrl。
- [ ] 解析引擎优化:增量解析代码注释引擎。
- [ ] html、adhoc、postman支持模版自定义。
- [ ] 支持YApi文件导出，方便导入YApi。
- [ ] Gradle 插件开发。
- [ ] Idea 插件开发。



