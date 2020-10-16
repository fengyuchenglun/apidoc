package com.github.fengyuchenglun.apidoc.core.schema;

import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.javadoc.Javadoc;
import com.github.fengyuchenglun.apidoc.core.common.helper.CommentHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.StringHelper;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.github.fengyuchenglun.apidoc.core.common.Constants.DEFAULT_NODE_INDEX;
import static com.github.fengyuchenglun.apidoc.core.common.Constants.TAG_CUSTOM_JAVA_DOC_IGNORE;

/**
 * The type Node.
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Data
public class Node implements Comparable<Node> {
    /**
     * 节点类型
     */
    String type;
    /**
     * 节点编号
     */
    String id;
    /**
     * 节点名称
     */
    String name;
    /**
     * 节点描述
     */
    String description;
    /**
     * 节点索引
     */
    int index = DEFAULT_NODE_INDEX;
    /**
     * 扩展属性
     * 如：Spring在Controller的RequestMapping，可以存在扩展属性中
     */
    Map<String, Object> ext = new HashMap<>();

    /**
     * javadoc 中的tag
     */
    Map<String, Tag> tags = Maps.newHashMap();

    @Override
    public int compareTo(@Nonnull Node other) {
        // 类型排序
        if (this.type != null && other.type != null) {
            return this.type.compareTo(other.type);
        }
        // 索引排序
        if (this.index != other.index) {
            return this.index - other.index;
        }
        // id排序
        if (this.id != null && other.id != null) {
            return this.id.compareTo(other.id);
        }
        // 名称排序
        if (this.name != null && other.name != null) {
            return this.name.compareTo(other.name);
        }
        return 0;
    }

    /**
     * Accept.
     *
     * @param comment the comment
     */
    public void accept(Comment comment) {
        // 不是javadoc注释
        if (!comment.isJavadocComment()) {
            setNameAndDescription(comment.getContent());
            return;
        }
        Javadoc javadoc = comment.asJavadocComment().parse();
        setNameAndDescription(CommentHelper.getDescription(javadoc.getDescription()));

        // 设置tag
        javadoc.getBlockTags().forEach(blockTag -> {
            Tag tag = new Tag();
            tag.id = blockTag.getTagName();
            tag.key = blockTag.getName().isPresent() ? blockTag.getName().get() : "";
            tag.content = CommentHelper.getDescription(blockTag.getContent());
            putTag(tag);
        });

        getTag("index").ifPresent(tag -> setIndex(tag.getIntContent(DEFAULT_NODE_INDEX)));
    }

    /**
     * 设置名称与描述.
     *
     * @param content the content
     */
    public void setNameAndDescription(String content) {
        String[] arr = content.split("(\\r\\n)|(\\r)|(\\n)+", 2);
        if (arr.length >= 1 && StringUtils.isNotBlank(arr[0])) {
            name = arr[0];
        }
        if (arr.length >= 2 && StringUtils.isNotBlank(arr[1])) {
            description = arr[1];
        }
    }

    /**
     * Gets tag.
     *
     * @param id the id
     * @return the tag
     */
    public Optional<Tag> getTag(String id) {
        return Optional.ofNullable(tags.get(id));
    }

    /**
     * Gets param tag.
     *
     * @param id the id
     * @return the param tag
     */
    public Optional<Tag> getParamTag(String id) {
        return Optional.ofNullable(tags.get("param:" + id));
    }

    /**
     * Put tag.
     *
     * @param tag the tag
     */
    public void putTag(Tag tag) {
        String id = tag.id;
        if (StringHelper.nonBlank(tag.getKey())) {
            id += ":" + tag.getKey();
        }
        tags.put(id, tag);
    }

    /**
     * Is ignore boolean.
     *
     * @return the boolean
     */
    public boolean isIgnore() {
        return getTag(TAG_CUSTOM_JAVA_DOC_IGNORE).isPresent();
    }
}
