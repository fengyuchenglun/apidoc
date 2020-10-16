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
import com.google.common.collect.Sets;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.github.fengyuchenglun.apidoc.core.common.Constants.*;

/**
 * 配置上下文.
 *
 * @author duanledexianxianxian
 * @version 1.0.0
 */
public class Context {

    /**
     * 框架名称.
     */
    private String framework;
    /**
     * 编译目录.
     */
    private Path buildPath = Paths.get(DEFAULT_BUILD_PATH);
    /**
     * 源码目录.
     */
    private final List<Path> sources = Lists.newArrayList();
    /**
     * 依赖源码路径.
     */
    private final List<Path> dependencies = Lists.newArrayList();
    /**
     * 依赖jar包.
     * 绝对路径.最好使用xxxx-sources.jar
     */
    private final List<Path> jars = Lists.newArrayList();
    /**
     * 项目编号.
     */
    private String id = DEFAULT_PROJECT_ID;
    /**
     * 名称.
     */
    private String name = DEFAULT_PROJECT_ID;
    /**
     * 描述.
     */
    private String description;
    /**
     * 版本.
     */
    private String version = DEFAULT_VERSION;
    /**
     * 渲染html时的css.
     */
    private String css;
    /**
     * 解析jar包中的java文件需要扫描的包名.
     */
    private final Set<String> scanPackages = new TreeSet<>();
    /**
     * markdown模版文件路径.
     * 默认/templates
     */
    private String template = Constants.TEMPLATE_PATH;
    /**
     * 字段列表显示方式.
     * 1. 平级（默认）
     * 2. tree
     */
    private FieldShowWay fileShowWay = FieldShowWay.TREE;

    /**
     * 生成附录是否需要使用@code标记
     * 默认false
     */
    private Boolean scanCode = false;

    /**
     * Controller注解名称标识
     */
    private final Set<String> controllers = Sets.newHashSet();
    /**
     * RestController注解名称标识
     */
    private final Set<String> restControllers = Sets.newHashSet();

    /**
     * 统一结果-分页类名称
     * 默认^IPage\w*,Page,^PageInfo.
     */
    private final Set<String> pageClassNames = Sets.newHashSet(Arrays.asList("^IPage\\S*", "Page", "^PageInfo"));
    /**
     * 自定义扩展参数.
     */
    private final Map<String, Object> ext = Maps.newHashMap();

    /**
     * 只输出指定的Controller类接口文档
     */
    private final Set<String> includes = Sets.newHashSet();

    /**
     * 忽略指定的Controller类接口文档
     */
    private final Set<String> excludes = Sets.newHashSet();

    /**
     * 渲染器.
     * 默认渲染adhoc、postman、markdown文件
     */
    private List<ProjectRender> renders = Lists.newArrayList(
            new AsciiDocRender(),
            new PostmanRender(),
            new MarkdownRender()
    );

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Add source.
     *
     * @param path the path
     */
    public void addSource(Path path) {
        sources.add(path);
        sources.addAll(FileHelper.find(path, DEFAULT_CODE_STRUCTURE));
        addDependency(path);
    }

    /**
     * Add dependency.
     *
     * @param path the path
     */
    public void addDependency(Path path) {
        dependencies.add(path);
        dependencies.addAll(FileHelper.find(path, DEFAULT_CODE_STRUCTURE));
    }

    /**
     * Add jar.
     *
     * @param path the path
     */
    public void addJar(Path path) {
        jars.add(path);
    }

    /**
     * Add scan package.
     *
     * @param pack the pack
     */
    public void addScanPackage(String pack) {
        scanPackages.add(pack);
    }

    /**
     * Add scan packages.
     *
     * @param packs the packs
     */
    public void addScanPackages(List<String> packs) {
        scanPackages.addAll(packs);
    }


    /**
     * Add include.
     *
     * @param include the include
     */
    public void addInclude(String include) {
        includes.add(include);
    }

    /**
     * Add includes.
     *
     * @param includes the includes
     */
    public void addIncludes(List<String> includes) {
        this.includes.addAll(includes);
    }


    /**
     * Add exclude.
     *
     * @param exclude the exclude
     */
    public void addExclude(String exclude) {
        excludes.add(exclude);
    }

    /**
     * Add excludes.
     *
     * @param excludes the excludes
     */
    public void addExcludes(List<String> excludes) {
        this.excludes.addAll(excludes);
    }

    /**
     * Add scan package.
     *
     * @param controller the controller
     */
    public void addController(String controller) {
        controllers.add(controller);
    }

    /**
     * Add scan packages.
     *
     * @param controllers the controllers
     */
    public void addControllers(List<String> controllers) {
        this.controllers.addAll(controllers);
    }

    /**
     * Add scan package.
     *
     * @param restController the rest controller
     */
    public void addRestController(String restController) {
        controllers.add(restController);
    }

    /**
     * Add scan packages.
     *
     * @param restControllers the rest controllers
     */
    public void addRestControllers(List<String> restControllers) {
        this.controllers.addAll(restControllers);
    }

    /**
     * Add scan packages.
     *
     * @param packs the packs
     */
    public void addScanPackages(String... packs) {
        Collections.addAll(scanPackages, packs);
    }

    /**
     * Add page class names.
     *
     * @param pageNames the page names
     */
    public void addPageClassNames(String... pageNames) {
        Collections.addAll(pageClassNames, pageNames);
    }

    /**
     * Gets page class names.
     *
     * @return the page class names
     */
    public Set<String> getPageClassNames() {
        return pageClassNames;
    }


    /**
     * Sets framework.
     *
     * @param framework the framework
     */
    public void setFramework(String framework) {
        this.framework = framework;
    }

    /**
     * Sets build path.
     *
     * @param buildPath the build path
     */
    public void setBuildPath(Path buildPath) {
        this.buildPath = buildPath;
    }

    /**
     * Sets renders.
     *
     * @param renders the renders
     */
    public void setRenders(List<ProjectRender> renders) {
        this.renders = renders;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Sets css.
     *
     * @param css the css
     */
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * Add scan packages.
     *
     * @param scanPackages the scan packages
     */
    public void addScanPackages(Set<String> scanPackages) {
        this.scanPackages.addAll(scanPackages);
    }


    /**
     * Sets file show way.
     *
     * @param fileShowWay the file show way
     */
    public void setFileShowWay(FieldShowWay fileShowWay) {
        this.fileShowWay = fileShowWay;
    }

    /**
     * Gets framework.
     *
     * @return the framework
     */
    public String getFramework() {
        return framework;
    }

    /**
     * Gets build path.
     *
     * @return the build path
     */
    public Path getBuildPath() {
        return buildPath;
    }

    /**
     * Gets sources.
     *
     * @return the sources
     */
    public List<Path> getSources() {
        return sources;
    }

    /**
     * Gets dependencies.
     *
     * @return the dependencies
     */
    public List<Path> getDependencies() {
        return dependencies;
    }

    /**
     * Gets jars.
     *
     * @return the jars
     */
    public List<Path> getJars() {
        return jars;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Gets css.
     *
     * @return the css
     */
    public String getCss() {
        return css;
    }

    /**
     * Gets scan packages.
     *
     * @return the scan packages
     */
    public Set<String> getScanPackages() {
        return scanPackages;
    }


    /**
     * Gets file show way.
     *
     * @return the file show way
     */
    public FieldShowWay getFileShowWay() {
        return fileShowWay;
    }

    /**
     * Gets ext.
     *
     * @return the ext
     */
    public Map<String, Object> getExt() {
        return ext;
    }

    /**
     * Gets renders.
     *
     * @return the renders
     */
    public List<ProjectRender> getRenders() {
        return renders;
    }

    /**
     * Gets template.
     *
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Sets template.
     *
     * @param template the template
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * Gets scan code.
     *
     * @return the scan code
     */
    public Boolean getScanCode() {
        return scanCode;
    }

    /**
     * Sets scan code.
     *
     * @param scanCode the scan code
     */
    public void setScanCode(Boolean scanCode) {
        this.scanCode = scanCode;
    }

    /**
     * Gets controllers.
     *
     * @return the controllers
     */
    public Set<String> getControllers() {
        return controllers;
    }

    /**
     * Gets includes.
     *
     * @return the includes
     */
    public Set<String> getIncludes() {
        return includes;
    }

    /**
     * Gets excludes.
     *
     * @return the excludes
     */
    public Set<String> getExcludes() {
        return excludes;
    }

    public Set<String> getRestControllers() {
        return restControllers;
    }
}
