package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * The type Annotation helper.
 *
 * @author duanledexianxianxian
 */
public class AnnotationHelper {
    private static final String VALUE = "value";

    /**
     * 节点是否存在当前注解
     *
     * @param node            the node
     * @param annotationNames the annotation names
     * @return boolean
     */
    public static boolean isAnnotationPresent(NodeWithAnnotations node, Set<String> annotationNames) {
        for (String annotationName : annotationNames) {
            if (node.isAnnotationPresent(annotationName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets attribute.
     *
     * @param annotationExpr the annotation expr
     * @param key            the key
     * @return the attribute
     */
    public static Optional<Expression> getAttribute(AnnotationExpr annotationExpr, String key) {
        if (Objects.equals(VALUE, key) && annotationExpr.isSingleMemberAnnotationExpr()) {
            return Optional.of(annotationExpr.asSingleMemberAnnotationExpr().getMemberValue());
        }
        if (annotationExpr.isNormalAnnotationExpr()) {
            for (MemberValuePair pair : annotationExpr.asNormalAnnotationExpr().getPairs()) {
                if (Objects.equals(key, pair.getNameAsString())) {
                    return Optional.of(pair.getValue());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Gets any attribute.
     *
     * @param annotationExpr the annotation expr
     * @param keys           the keys
     * @return the any attribute
     */
    public static Optional<Expression> getAnyAttribute(AnnotationExpr annotationExpr, String... keys) {
        for (String key : keys) {
            Optional<Expression> optional = getAttribute(annotationExpr, key);
            if (optional.isPresent()) {
                return optional;
            }
        }
        return Optional.empty();
    }

}
