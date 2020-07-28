package com.github.fengyuchenglun.apidoc.springmvc;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.Context;
import com.github.fengyuchenglun.apidoc.core.common.diff.FileMatcher;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpringTest {

    @Test
    public void test() throws IOException {

        Context context = new Context();
        context.setId("test");
        context.setName("测试项目");
        context.addSource(Paths.get("F:\\@project@\\@dianli@\\tool\\apidoc\\apidoc-springmvc\\src\\test\\java"));
//        context.setCss("https://darshandsoni.com/asciidoctor-skins/css/monospace.css");

        ApiDoc apigcc = new ApiDoc(context);
        apigcc.parse();
        apigcc.render();
    }


    @Test
    public void testTestToolls() throws IOException {

        Context context = new Context();
        context.setId("test-tools");
        context.setName("测试工具");
        context.addSource(Paths.get("D:/workspaces/ubisor-test-tools/backend/"));
//        context.setCss("https://darshandsoni.com/asciidoctor-skins/css/monospace.css");

        ApiDoc apigcc = new ApiDoc(context);
        apigcc.parse();
        apigcc.render();

        Path buildAdoc = Paths.get("build/test-tools/index.adoc");
        Path template = Paths.get("src/test/resources/test-tools.adoc");
        Path templateHtml = Paths.get("src/test/resources/template.html");
        Path resultHtml = Paths.get("build/test-tools/diff.html");

        FileMatcher fileMatcher = new FileMatcher();
        int changed = fileMatcher.compare(template, buildAdoc);
        if(changed>0){
            fileMatcher.renderHtml(templateHtml, resultHtml);
        }

        System.out.println("BUILD SUCCESS");
    }


    @Test
    public void testUbcloud() throws IOException {

        Context context = new Context();
        context.setId("ubcloud");
        context.setName("优碧云1");
        context.addSource(Paths.get("D:/workspaces/ubisor-backend/ubcloud-front-web/"));
        context.addDependency(Paths.get("D:/workspaces/ubisor-backend/"));
        context.setCss("https://darshandsoni.com/asciidoctor-skins/css/monospace.css");

        ApiDoc apigcc = new ApiDoc(context);
        apigcc.parse();
        apigcc.render();

        Path buildAdoc = Paths.get("build/ubcloud/index.adoc");
        Path template = Paths.get("src/test/resources/ubcloud-front-web.adoc");
        Path templateHtml = Paths.get("src/test/resources/template.html");
        Path resultHtml = Paths.get("build/ubcloud/diff.html");

        FileMatcher fileMatcher = new FileMatcher();
        int changed = fileMatcher.compare(template, buildAdoc);
        if(changed>0){
            fileMatcher.renderHtml(templateHtml, resultHtml);
        }

        System.out.println("BUILD SUCCESS");
    }

}