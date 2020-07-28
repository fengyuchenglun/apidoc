package com.github.fengyuchenglun.apidoc.core;

import com.github.fengyuchenglun.apidoc.core.common.helper.StringHelper;
import com.github.fengyuchenglun.apidoc.core.parser.ParserStrategy;
import com.github.fengyuchenglun.apidoc.core.parser.VisitorParser;
import com.github.fengyuchenglun.apidoc.core.render.ProjectRender;
import com.github.fengyuchenglun.apidoc.core.resolver.TypeResolvers;
import com.github.fengyuchenglun.apidoc.core.schema.Project;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.utils.SourceRoot;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * apidoc配置类.
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Slf4j
public class ApiDoc {

    /**
     * 上下文
     */
    @Getter
    private Context context;
    /**
     * 项目信息
     */
    @Getter
    private Project project = new Project();
    /**
     * The Visitor parser.
     */
    private VisitorParser visitorParser = new VisitorParser();
    /**
     * The Parser configuration.
     */
    private ParserConfiguration parserConfiguration;

    /**
     * 统一结果
     */
    @Setter
    @Getter
    ClassOrInterfaceDeclaration resultDataClassOrInterfaceDeclaration;

    /**
     * The Type resolvers.
     */
    @Getter
    private TypeResolvers typeResolvers = new TypeResolvers();

    /**
     * Instantiates a new ApiDoc.
     */
    private ApiDoc() {
        init(new Context());
    }

    /**
     * Instantiates a new ApiDoc.
     *
     * @param context the context
     */
    public ApiDoc(Context context) {
        init(context);
    }

    /**
     * Get instance api doc.
     *
     * @return the api doc
     */
    public static ApiDoc getInstance() {
        return ApiDocInstance.INSTANCE;
    }


    private static class ApiDocInstance {
        /**
         * The constant INSTANCE.
         */
        public static ApiDoc INSTANCE = new ApiDoc();
    }


    /**
     * 初始化环境配置
     *
     * @param context the context
     */
    private void init(Context context) {
        // 保存自身实例
        ApiDocInstance.INSTANCE = this;
        this.context = context;

        // 初始化项目
        project.setId(context.getId());
        project.setName(context.getName());
        project.setDescription(context.getDescription());
        project.setVersion(context.getVersion());

        // dependencies设置typeSolver
        CombinedTypeSolver typeSolver = new CombinedTypeSolver();
        for (Path dependency : context.getDependencies()) {
            typeSolver.add(new JavaParserTypeSolver(dependency));
        }
        // jars设置typeSolver
        for (Path jar : context.getJars()) {
            try {
                typeSolver.add(new JarTypeSolver(jar));
            } catch (IOException e) {
                log.warn("exception on {} {}", jar, e.getMessage());
            }
        }
        // 反射?
        typeSolver.add(new ReflectionTypeSolver());

        parserConfiguration = new ParserConfiguration();
        parserConfiguration.setSymbolResolver(new JavaSymbolSolver(typeSolver));

        // 加载策略
        ParserStrategy strategy = loadParserStrategy();
        strategy.onLoad();
        visitorParser.setParserStrategy(strategy);

    }

    /**
     * 加载并设置解析框架
     * null时，使用读取到的第一个框架解析器
     * 找不到时，报错
     *
     * @return the parser strategy
     */
    private ParserStrategy loadParserStrategy() {
        ServiceLoader<ParserStrategy> serviceLoader = ServiceLoader.load(ParserStrategy.class);
        List<ParserStrategy> strategies = Lists.newArrayList(serviceLoader);
        if (strategies.isEmpty()) {
            throw new IllegalArgumentException("no ParserStrategy implements found");
        }
        // 用户未配置context的framework
        if (StringUtils.isBlank(context.getFramework())) {
            return strategies.get(0);
        }
        for (ParserStrategy strategy : strategies) {
            if (Objects.equals(context.getFramework(), strategy.name())) {
                return strategy;
            }
        }
        throw new IllegalArgumentException("no ParserStrategy implements found for " + context.getFramework());
    }

    /**
     * 1. 解析源代码
     *
     * @return project project
     */
    public Project parse() {
        for (Path source : this.context.getSources()) {
            SourceRoot root = new SourceRoot(source, parserConfiguration);
            try {
                for (ParseResult<CompilationUnit> result : root.tryToParse()) {
                    if (result.isSuccessful() && result.getResult().isPresent()) {
                        //note: 访问解析器 +当前项目上下文
                        result.getResult().get().accept(visitorParser, project);
                    }
                }
            } catch (IOException e) {
                log.warn("parse root {} error {}", source, e.getMessage());
            }
        }
        return project;
    }

    /**
     * 2. 渲染解析结果
     */
    public void render() {
        for (ProjectRender render : this.context.getRenders()) {
            render.render(project);
        }
    }

}
