package com.github.fengyuchenglun.apidoc.core.common.description;

import ch.qos.logback.core.util.OptionHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.CommentHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.OptionalHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.StringHelper;
import com.github.fengyuchenglun.apidoc.core.schema.Row;
import com.github.fengyuchenglun.apidoc.core.schema.Tag;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.javadoc.Javadoc;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.github.fengyuchenglun.apidoc.core.common.Constants.TAG_CUSTOM_JAVA_DOC_DATA;
import static com.github.fengyuchenglun.apidoc.core.common.Constants.TAG_CUSTOM_JAVA_DOC_MOCK;

/**
 * 对象描述.
 *
 * @author duanledexianxianxian
 */
@Data
public abstract class TypeDescription {

    /**
     * 前缀.
     */
    protected String prefix = "";
    /**
     * key.
     */
    protected String key = "";
    /**
     * 类型.
     */
    protected String type;
    /**
     * 条件.
     */
    protected StringBuilder condition = new StringBuilder();

    /**
     * 名称
     */
    String name;
    /**
     * 描述
     */
    String description;
    /**
     * 说明.
     */
    protected String remark;
    /**
     * 值.
     */
    protected Object value;
    /**
     * 默认值.
     */
    protected Object defaultValue;

    /**
     * javadoc 中的tag
     */
    Map<String, Tag> tags = new HashMap<>();

    /**
     * 是否必填.
     */
    protected Boolean required = false;


    /**
     * The Level.
     */
    protected Integer level = 0;

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return !isUnAvailable();
    }

    /**
     * Is un available boolean.
     *
     * @return the boolean
     */
    public boolean isUnAvailable() {
        return this instanceof UnAvailableTypeDescription;
    }

    /**
     * Is primitive boolean.
     *
     * @return the boolean
     */
    public boolean isPrimitive() {
        return this instanceof PrimitiveTypeDescription;
    }

    /**
     * As primitive primitive type description.
     *
     * @return the primitive type description
     */
    public PrimitiveTypeDescription asPrimitive() {
        return (PrimitiveTypeDescription) this;
    }

    /**
     * Is string boolean.
     *
     * @return the boolean
     */
    public boolean isString() {
        return this instanceof StringTypeDescription;
    }

    /**
     * As string string type description.
     *
     * @return the string type description
     */
    public StringTypeDescription asString() {
        return (StringTypeDescription) this;
    }

    /**
     * Is array boolean.
     *
     * @return the boolean
     */
    public boolean isArray() {
        return this instanceof ArrayTypeDescription;
    }

    /**
     * As array array type description.
     *
     * @return the array type description
     */
    public ArrayTypeDescription asArray() {
        return (ArrayTypeDescription) this;
    }

    /**
     * Is object boolean.
     *
     * @return the boolean
     */
    public boolean isObject() {
        return this instanceof ObjectTypeDescription;
    }

    /**
     * As object object type description.
     *
     * @return the object type description
     */
    public ObjectTypeDescription asObject() {
        return (ObjectTypeDescription) this;
    }

    /**
     * Add remark.
     *
     * @param value the value
     */
    public void addRemark(String value) {
        if (value == null) {
            return;
        }
        if (remark == null) {
            remark = value;
        } else {
            remark = StringUtils.isNotBlank(remark) ? remark : value;
        }
    }


    /**
     * Full key string.
     *
     * @return the string
     */
    public String fullKey() {
        return StringHelper.join(".", prefix, key);
    }

    /**
     * Rows collection.
     *
     * @param parameterType the parameter type
     * @return the collection
     */
    public Collection<Row> rows(String parameterType) {
        String fullKey = fullKey();
        if (StringHelper.isBlank(fullKey)) {
            return Lists.newArrayList();
        }
        String def;


        if (defaultValue != null) {
            def = String.valueOf(defaultValue);
        } else if (value != null) {
            def = String.valueOf(value);
        } else {
            def = "";
        }

        String mock = def;
        if (null != tags.get(TAG_CUSTOM_JAVA_DOC_MOCK)) {
            mock = tags.get(TAG_CUSTOM_JAVA_DOC_MOCK).getContent();
        }
        Boolean isData = false;
        if (null != tags.get(TAG_CUSTOM_JAVA_DOC_DATA)) {
            isData = true;
        }

//        if (required != null) {
//            condition.append("required=").append(required);
//        }

        return Lists.newArrayList(new Row(fullKey, type, required, condition.toString(), def, remark, mock, parameterType, isData));
    }


    /**
     * Rows collection.
     *
     * @return the collection
     */
    public Collection<Row> rows() {
        return this.rows(null);
    }

    /**
     * Accept.
     *
     * @param comment the comment
     */
    public void accept(Comment comment) {
        String content;
        if (!comment.isJavadocComment()) {
            content = comment.getContent();
            setRemark(content);
            return;
        }
        Javadoc javadoc = comment.asJavadocComment().parse();
        content = CommentHelper.getDescription(javadoc.getDescription());
        setRemark(content);
    }


    /**
     * Put tag.
     *
     * @param tag the tag
     */
    public void putTag(Tag tag) {
        String id = tag.getId();
        if (StringHelper.nonBlank(tag.getKey())) {
            id += ":" + tag.getKey();
        }
        tags.put(id, tag);
    }


}
