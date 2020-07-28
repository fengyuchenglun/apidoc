package com.github.fengyuchenglun.apidoc.core;

import com.github.fengyuchenglun.apidoc.core.common.Constants;
import com.github.fengyuchenglun.apidoc.core.common.enums.FieldShowWay;
import com.github.fengyuchenglun.apidoc.core.render.AsciiDocRender;
import com.github.fengyuchenglun.apidoc.core.render.PostmanRender;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.github.fengyuchenglun.apidoc.core.common.helper.FileHelper;
import com.github.fengyuchenglun.apidoc.core.render.MarkdownRender;
import com.github.fengyuchenglun.apidoc.core.render.ProjectRender;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * 上下文.
 * 用户可自定义配置参数
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Getter
public class Context {

    /**
     * 默认-节点索引
     */
    public static final Integer DEFAULT_NODE_INDEX = 99;
    /**
     * 默认-项目编号
     */
    public static final String DEFAULT_PROJECT_ID = "api";
    /**
     * 默认-编译路径
     */
    public static final String DEFAULT_BUILD_PATH = "build";
    /**
     * 默认-代码结构
     */
    public static final String DEFAULT_CODE_STRUCTURE = "src/main/java";


    /**
     * 设置当前解析框架
     */
    @Setter
    private String framework;

    /**
     * The Renders.
     */
    @Setter
    private List<ProjectRender> renders = Lists.newArrayList(
            new AsciiDocRender(),
            new PostmanRender(),
            new MarkdownRender());

    /**
     * 编译目录
     */
    @Setter
    private Path buildPath = Paths.get(DEFAULT_BUILD_PATH);

    /**
     * 源码目录
     */
    private List<Path> sources = Lists.newArrayList();

    /**
     * 依赖源码
     */
    private List<Path> dependencies = Lists.newArrayList();

    /**
     * 依赖jar包
     */
    private List<Path> jars = Lists.newArrayList();

    /**
     * 项目编号
     */
    @Setter
    private String id = DEFAULT_PROJECT_ID;
    /**
     * 名称
     */
    @Setter
    private String name;
    /**
     * 描述
     */
    @Setter
    private String description;
    /**
     * 版本
     */
    @Setter
    private String version;

    /**
     * 渲染html时的css
     */
    @Setter
    private String css;


    /**
     * markdown模版文件路径
     */
    @Setter
    private String markdownTemplate = Constants.MARKDOWN_TEMPLATE;

    /**
     * 字段显示方式.
     * 1. 平级（默认）
     * 2. tree
     */
    @Setter
    private FieldShowWay fileShowWay= FieldShowWay.FLAT;

    /**
     * 自定义扩展参数
     */
    private Map<String, Object> ext = Maps.newHashMap();

    /**
     * 添加源码.
     *
     * @param path the path
     */
    public void addSource(Path path) {
        sources.add(path);
        sources.addAll(FileHelper.find(path, DEFAULT_CODE_STRUCTURE));
        addDependency(path);
    }

    /**
     * 添加依赖.
     * 文件路径：例如
     * /url/api-doc/
     *
     * @param path the path
     */
    public void addDependency(Path path) {
        dependencies.add(path);
        dependencies.addAll(FileHelper.find(path, DEFAULT_CODE_STRUCTURE));
    }

    /**
     * 添加jar.
     *
     * @param path the path
     */
    public void addJar(Path path) {
        jars.add(path);
    }


}
