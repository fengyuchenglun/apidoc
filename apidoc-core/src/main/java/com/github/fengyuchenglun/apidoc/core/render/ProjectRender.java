package com.github.fengyuchenglun.apidoc.core.render;

import com.github.fengyuchenglun.apidoc.core.schema.Project;

/**
 * The interface Project render.
 *
 * @author fengyuchenglun
 */
public interface ProjectRender {

    /**
     * 模版文件名.
     *
     * @return the string
     */
    String template();

    /**
     * Render.
     *
     * @param project the project
     */
    void render(Project project);

}
