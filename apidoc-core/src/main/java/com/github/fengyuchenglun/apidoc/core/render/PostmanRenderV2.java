package com.github.fengyuchenglun.apidoc.core.render;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.ObjectMappers;
import com.github.fengyuchenglun.apidoc.core.common.helper.FileHelper;
import com.github.fengyuchenglun.apidoc.core.common.postman.*;
import com.github.fengyuchenglun.apidoc.core.schema.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

/**
 * Postman v2.1 json文件构建
 *
 * @author duanledexianxianxian
 */
@Slf4j
public class PostmanRenderV2 implements ProjectRender {
    @Override
    public String template() {
        return "postman_v2.ftl";
    }
    @Override
    public void render(Project project) {
        Postman postman = build(project);
        String id = ApiDoc.getInstance().getContext().getId();
        Path buildPath = ApiDoc.getInstance().getContext().getBuildPath();
        Path file = buildPath.resolve(id).resolve("postman_v2_1.json");
        FileHelper.write(file, ObjectMappers.pretty(postman));
        log.info("Build Postman {}", file);
    }

    private Postman build(Project project) {
        Postman postman = new Postman();
        Info info = new Info();
        info.set_postman_id(project.getId());
        info.setName(project.getName());
        info.setDescription(project.getDescription());
        postman.setInfo(info);

        for (Book book : project.getBooks().values()) {
            Folder folder = new Folder();
            folder.setName(book.getId());
            for (Chapter chapter : book.getChapters()) {
                if (chapter.isIgnore() || chapter.getSections().isEmpty()) {
                    continue;
                }
                Folder chapterFolder = new Folder();
                chapterFolder.setName(chapter.getName());
                chapterFolder.setDescription(chapter.getDescription());
                for (Section section : chapter.getSections()) {
                    if (section.isIgnore()) {
                        continue;
                    }
                    chapterFolder.getItem().add(build(section));
                }
                folder.getItem().add(chapterFolder);
            }
            postman.getItem().add(folder);
        }

        if (postman.getItem().size() == 1) {
            Folder folder = postman.getItem().get(0);
            postman.setItem(folder.getItem());
        }

        return postman;
    }

    private Item build(Section section) {
        Item item = new Item();
        item.setName(section.getName());
        item.setDescription(section.getDescription());

        Request request = new Request();
        request.setDescription(section.getDescription());
        request.getUrl().setPath(section.getUri());
        request.setMethod(section.getMethod());
        request.getHeaders().addAll(section.getInHeaders().values());

        ObjectNode objectNode = (ObjectNode) section.getQueryParameters();
        for (String key : section.getRequestRows().keySet()) {
            if (objectNode.has(key)) {
                Row row = section.getRequestRows().get(key);
                request.getUrl().getQuery().add(Parameter.of(row));
            }
        }
        if (section.isQueryParameter()) {
            // get请求
            if (!Method.GET.equals(request.getMethod())) {
                request.getBody().setMode(BodyMode.urlencoded);
                objectNode = (ObjectNode) section.getRequestBodyParameters();
                for (String key : section.getRequestRows().keySet()) {
                    if (objectNode.has(key)) {
                        Row row = section.getRequestRows().get(key);
                        request.getBody().getUrlencoded().add(Parameter.of(row));
                    }
                }
            }
        } else {
            request.getBody().setMode(BodyMode.raw);
            request.getBody().setRaw(section.getRequestBodyParameterString());
        }
        item.setRequest(request);

        Response response = new Response();
        response.setName("success");
        response.setOriginalRequest(request);
        response.getHeaders().addAll(section.getOutHeaders().values());
        response.setBody(section.getResponseString());
        response.setCode(200);
        response.setStatus("OK");
        item.getResponse().add(response);

        return item;
    }


}
