package com.github.fengyuchenglun.apidoc.springmvc.example;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.Context;
import com.github.fengyuchenglun.apidoc.core.common.diff.FileMatcher;
import com.github.fengyuchenglun.apidoc.core.common.enums.FieldShowWay;
import com.github.fengyuchenglun.apidoc.core.render.AsciiDocRender;
import com.github.fengyuchenglun.apidoc.core.render.MarkdownRender;
import com.github.fengyuchenglun.apidoc.core.render.PostmanRender;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Spring test.
 */
public class SpringTest {

    /**
     * 测试api接口文档生成
     *
     * @throws IOException the io exception
     */
    @Test
    public void test() throws IOException {
        Context context = new Context();
        context.setName("测试项目");
        context.setFileShowWay(FieldShowWay.FLAT);
        context.addJar(Paths.get("src/test/resources/lib/apidoc-model-1.0-SNAPSHOT.jar"));
        context.addSource(Paths.get("src/test/java"));
        ApiDoc apiDoc = new ApiDoc(context);
        apiDoc.parse();
        apiDoc.render();

        Path buildAdoc = Paths.get("build/api/index.adoc");
        Path templateAdoc = Paths.get("src/test/resources/index.adoc");
        Path templateHtml = Paths.get("src/test/resources/index.html");
        Path resultHtml = Paths.get("build/api/index.html");
        Path buildMd = Paths.get("build/api/index.md");
        Path templateMd = Paths.get("src/test/resources/index.md");
        FileMatcher fileMatcher = new FileMatcher();
        int changedAdoc = fileMatcher.compare(templateAdoc, buildAdoc);
        int changedMd = fileMatcher.compare(templateMd, buildMd);

        if (changedAdoc > 0) {
            fileMatcher.renderHtml(templateHtml, resultHtml);
        }
        Assert.assertEquals(0, changedAdoc);
        Assert.assertEquals(0, changedMd);

        System.out.println("BUILD SUCCESS");
    }

    /**
     * 测试api接口文档生成.
     * 统一结果返回
     *
     * @throws IOException the io exception
     */
    @Test
    public void testResultData() throws IOException {
        Context context = new Context();
        context.setName("测试项目");
        context.setFileShowWay(FieldShowWay.FLAT);
        context.addJar(Paths.get("src/test/resources/lib/kim3-boot-starter-1.1.4-SNAPSHOT-sources.jar"));
        context.addJar(Paths.get("src/test/resources/lib/apidoc-model-1.0-SNAPSHOT.jar"));
        context.addSource(Paths.get("src/test/java"));
        ApiDoc apiDoc = new ApiDoc(context);
        apiDoc.parse();
        apiDoc.render();
    }

}