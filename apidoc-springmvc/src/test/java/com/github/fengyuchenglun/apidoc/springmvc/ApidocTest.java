package com.github.fengyuchenglun.apidoc.springmvc;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.Context;
import com.github.fengyuchenglun.apidoc.core.render.MarkdownRender;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ApiDoc单元测试
 *
 * @author duanledexianxianxian
 */
public class ApidocTest {
    /**
     * Get class path.
     */
    @Test
    public void getClassPath() {
        System.out.println(this.getClass().getResource(""));
        System.out.println(this.getClass().getResourceAsStream(""));
        System.out.println(Paths.get("").toAbsolutePath());
    }


    @SneakyThrows
    @Test
    public void appTest() {
        // 测试枚举
        Context context = new Context();
//        context.setRenders(Lists.newArrayList(new MarkdownRender()));
        context.addSource(Paths.get("").resolve("src/test/java").toAbsolutePath());
        ApiDoc apiDoc = new ApiDoc(context);
        apiDoc.parse();
        apiDoc.render();
    }
}
