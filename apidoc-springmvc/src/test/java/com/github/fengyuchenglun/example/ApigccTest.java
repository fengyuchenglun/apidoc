package com.github.fengyuchenglun.example;


import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.Context;
import org.junit.Test;

import java.nio.file.Paths;

/**
 * @title Apigcc示例文档
 * @description 通过javadoc设置文档描述信息
 * 优先级大于通过Environment.description()设置的值
 * @readme 所有接口均使用Https调用
 * /app路径下的接口为app专用
 * /mini路径下的接口为小程序专用
 */
public class ApigccTest {

    @Test
    public void testApigcc() {
        Context context = new Context();
        context.setId("test");
        context.setName("测试项目");
        context.addSource(Paths.get("D:/apigcc/apigcc-demo-spring"));
        //context.setCss("https://darshandsoni.com/asciidoctor-skins/css/monospace.css");

        ApiDoc apigcc = new ApiDoc(context);
        apigcc.parse();
        apigcc.render();
    }

}