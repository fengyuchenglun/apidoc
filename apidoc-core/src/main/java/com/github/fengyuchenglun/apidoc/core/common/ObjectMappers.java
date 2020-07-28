package com.github.fengyuchenglun.apidoc.core.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

/**
 * The type Object mappers.
 *
 * @author duanledexianxianxian
 */
public class ObjectMappers {

    /**
     * The constant instance.
     */
    public static final ObjectMapper instance;

    static {
        instance = new ObjectMapper();
        instance.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Pretty string.
     *
     * @param node the node
     * @return the string
     */
    public static String pretty(Object node) {
        try {
            return instance.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Merge object node.
     *
     * @param result the result
     * @param nodes  the nodes
     * @return the object node
     */
    public static ObjectNode merge(ObjectNode result, ObjectNode... nodes) {
        if (result != null) {
            for (ObjectNode node : nodes) {
                Iterator<String> iterator = node.fieldNames();
                while (iterator.hasNext()){
                    String key = iterator.next();
                    result.set(key,node.get(key));
                }
            }
        }
        return result;
    }
}
