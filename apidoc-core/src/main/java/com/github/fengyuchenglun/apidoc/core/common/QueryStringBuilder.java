package com.github.fengyuchenglun.apidoc.core.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fengyuchenglun.apidoc.core.common.helper.ObjectHelper;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * The type Query string builder.
 *
 * @author duanledexianxianxian
 */
public class QueryStringBuilder {

    /**
     * The Builder.
     */
    private StringBuilder builder = new StringBuilder();

    /**
     * Append query string builder.
     *
     * @param key   the key
     * @param value the value
     * @return the query string builder
     */
    public QueryStringBuilder append(Object key, Object value) {
        if (builder.length() > 0) {
            builder.append("&");
        }
        builder.append(key);
        builder.append("=");
        builder.append(ObjectHelper.isEmpty(value) ? "xxx" : value);
        return this;
    }


    /**
     * Append query string builder.
     *
     * @param objectNode the object node
     * @return the query string builder
     */
    public QueryStringBuilder append(ObjectNode objectNode) {
        Iterator<String> iterator = objectNode.fieldNames();
        while (iterator.hasNext()) {
            String key = iterator.next();
            JsonNode valueNode = objectNode.get(key);
            String value = valueNode.isTextual() ? valueNode.asText() : valueNode.toString();
            append(key, value);
        }
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }


}
