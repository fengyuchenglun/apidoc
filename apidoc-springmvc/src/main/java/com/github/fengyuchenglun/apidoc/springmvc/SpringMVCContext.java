package com.github.fengyuchenglun.apidoc.springmvc;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * springMVC 参数上下文
 *
 * @author duanledexianxianxian
 * @version 1.0.0
 * @date 2020 /3/25 0:21
 * @since 1.0.0
 */
@Data
public class SpringMVCContext {

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
     * The Controllers.
     */
    public List<String> controllers = Lists.newArrayList(ANNOTATION_CONTROLLER, ANNOTATION_REST_CONTROLLER, ANNOTATION_KIM_CONTROLLER);

    /**
     * The Rest controllers.
     */
    public List<String> restControllers = Lists.newArrayList(ANNOTATION_REST_CONTROLLER, ANNOTATION_KIM_CONTROLLER);


    /**
     * 获取springMVC 参数上下文实例
     *
     * @return the instance
     */
    public static SpringMVCContext getInstance() {
        Map<String, Object> ext = ApiDoc.getInstance().getContext().getExt();
        if (null == ext) {
            throw new IllegalArgumentException("Context ext is null");
        }
        SpringMVCContext context = (SpringMVCContext) ext.get(SpringParser.FRAMEWORK);
        if (context == null) {
            context = new SpringMVCContext();
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
     * Add restControllers.
     *
     * @param restController restController
     */
    public void addRestController(String restController) {
        this.restControllers.add(restController);
    }
}
