package com.github.fengyuchenglun.apidoc.springmvc;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * springMVC 参数上下文
 *
 * @author duanledexianxianxian
 * @since 1.0.0
 */
@Data
public class SpringMvcContext {

    /**
     * 常量-Controller
     */
    public static final String ANNOTATION_CONTROLLER = "Controller";
    /**
     * 常量-RestController
     */
    public static final String ANNOTATION_REST_CONTROLLER = "RestController";

    /**
     * 常量-KimController
     */
    public static final String ANNOTATION_KIM_CONTROLLER = "KimController";
    /**
     * 常量-NixController
     */
    public static final String ANNOTATION_NIX_CONTROLLER = "NixController";
    /**
     * The Controllers.
     */
    public Set<String> controllers = Sets.newHashSet(ANNOTATION_CONTROLLER, ANNOTATION_REST_CONTROLLER, ANNOTATION_KIM_CONTROLLER, ANNOTATION_NIX_CONTROLLER);

    /**
     * The Rest controllers.
     */
    public Set<String> restControllers = Sets.newHashSet(ANNOTATION_REST_CONTROLLER, ANNOTATION_KIM_CONTROLLER, ANNOTATION_NIX_CONTROLLER);


    /**
     * 获取springMVC 参数上下文实例
     *
     * @return the instance
     */
    public static SpringMvcContext getInstance() {
        Map<String, Object> ext = ApiDoc.getInstance().getContext().getExt();
        if (null == ext) {
            throw new IllegalArgumentException("Context ext is null");
        }
        SpringMvcContext context = (SpringMvcContext) ext.get(SpringParser.FRAMEWORK);
        if (context == null) {
            context = new SpringMvcContext();
        }
        return context;
    }

    /**
     * Add controller.
     *
     * @param controller controller
     */
    public void addController(String controller) {
        this.controllers.add(controller);
    }

    /**
     * Add controllers.
     *
     * @param controllers the controllers
     */
    public void addControllers(Set<String> controllers) {
        this.controllers.addAll(controllers);
    }

    /**
     * Add restControllers.
     *
     * @param restController restController
     */
    public void addRestController(String restController) {
        this.restControllers.add(restController);
    }

    /**
     * Add rest controllers.
     *
     * @param restControllers the rest controllers
     */
    public void addRestControllers(Set<String> restControllers) {
        this.restControllers.addAll(restControllers);
    }
}
