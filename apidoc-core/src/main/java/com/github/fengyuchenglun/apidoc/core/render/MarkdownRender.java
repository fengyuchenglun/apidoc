package com.github.fengyuchenglun.apidoc.core.render;

import com.github.fengyuchenglun.apidoc.core.common.Constants;
import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.helper.FileHelper;
import com.github.fengyuchenglun.apidoc.core.schema.Project;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;


/**
 * 生成markdown api文档
 *
 * @author duanledexianxianxian
 * @version 1.0.0
 * @date 2020 /3/26 19:03
 * @since 1.0.0
 */
@Slf4j
public class MarkdownRender implements ProjectRender {
    private Configuration configuration;

    public MarkdownRender init() {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(Constants.UTF8);
        configuration.setClassForTemplateLoading(MarkdownRender.class, Constants.SLASH);
        return this;
    }


    @Override
    public void render(Project project) {
        this.init();
        try {
            this.build(project);
        } catch (Exception e) {
            log.error("Build Markdown Fail {}", e.getMessage());
        }
    }

    private void build(Project project) {
        String templatePath = ApiDoc.getInstance().getContext().getMarkdownTemplate();
        String id = ApiDoc.getInstance().getContext().getId();
        Path buildPath = ApiDoc.getInstance().getContext().getBuildPath().resolve(id);
        StringWriter writer = new StringWriter();

        project.getBooks().forEach((name, book) -> {
            Path markdownFile = buildPath.resolve(name + Constants.MARKDOWN_EXTENSION);
            try {
                Template template = configuration.getTemplate(templatePath);
                template.process(project, writer);
            } catch (TemplateException | IOException e) {
                log.error("Write markdown fail:{}", e.getMessage());
            } finally {
                writer.flush();
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("Write markdown fail:{}", e.getMessage());
                }
            }
            FileHelper.write(markdownFile, writer.getBuffer().toString());
            log.info("Build Markdown {}", markdownFile);
        });

    }
}
