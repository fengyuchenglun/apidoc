package com.github.fengyuchenglun.apidoc.springmvc.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.FileInputStream;

public class MainTest {
    String mainPath =
            "F:\\@project@\\@dianli@\\tool\\apidoc\\apidoc-springmvc\\src\\test\\java\\com\\github\\fengyuchenglun\\apidoc\\springmvc\\javaparser\\Main.java";

    @SneakyThrows
    @Test
    public void mainTest() {
        FileInputStream in = new FileInputStream(mainPath);
        CompilationUnit cu = StaticJavaParser.parse(in);

        // visit and print the methods names
        new MethodVisitor().visit(cu, null);
    }
}
