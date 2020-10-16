package com.github.fengyuchenglun.apidoc.springmvc;

import com.github.fengyuchenglun.apidoc.core.common.URI;
import com.github.fengyuchenglun.apidoc.core.common.helper.AnnotationHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.ClassDeclarationHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.ExpressionHelper;
import com.github.fengyuchenglun.apidoc.core.schema.Method;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The type Request mapping helper.
 *
 * @author duanledexianxianxian
 * @version 1.0.0
 */
public class RequestMappingHelper {


    /**
     * 常量-GetMapping
     */
    public static final String ANNOTATION_GET_MAPPING = "GetMapping";
    /**
     * 常量-PostMapping
     */
    public static final String ANNOTATION_POST_MAPPING = "PostMapping";
    /**
     * 常量-PutMapping
     */
    public static final String ANNOTATION_PUT_MAPPING = "PutMapping";
    /**
     * 常量-PatchMapping
     */
    public static final String ANNOTATION_PATCH_MAPPING = "PatchMapping";
    /**
     * 常量-DeleteMapping
     */
    public static final String ANNOTATION_DELETE_MAPPING = "DeleteMapping";
    /**
     * 常量-RequestMapping
     */
    public static final String ANNOTATION_REQUEST_MAPPING = "RequestMapping";

    /**
     * 常量-ResponseBody
     */
    public static final String ANNOTATION_RESPONSE_BODY = "ResponseBody";

    /**
     * The constant ANNOTATION_REQUEST_MAPPINGS.
     */
    public static final Set<String> ANNOTATION_REQUEST_MAPPINGS = Sets.newHashSet(
            ANNOTATION_GET_MAPPING,
            ANNOTATION_POST_MAPPING,
            ANNOTATION_PUT_MAPPING,
            ANNOTATION_PATCH_MAPPING,
            ANNOTATION_DELETE_MAPPING,
            ANNOTATION_REQUEST_MAPPING);

    /**
     * Is rest boolean.
     *
     * @param n the n
     * @return the boolean
     */
    public static boolean isRest(MethodDeclaration n) {
        if (n.isAnnotationPresent(ANNOTATION_RESPONSE_BODY)) {
            return true;
        }
        Optional<Node> parentOptional = n.getParentNode();
        if (parentOptional.isPresent()) {
            Node parentNode = parentOptional.get();
            if (parentNode instanceof ClassOrInterfaceDeclaration) {
                return AnnotationHelper.isAnnotationPresent(((ClassOrInterfaceDeclaration) parentNode), SpringMvcContext.getInstance().getRestControllers());
            }
        }
        return false;
    }


    /**
     * Pick method method.
     *
     * @param methodDeclaration methodDeclaration
     * @return the method
     */
    public static Method pickMethod(MethodDeclaration methodDeclaration) {
        if (methodDeclaration.isAnnotationPresent(ANNOTATION_GET_MAPPING)) {
            return Method.GET;
        }
        if (methodDeclaration.isAnnotationPresent(ANNOTATION_POST_MAPPING)) {
            return Method.POST;
        }
        if (methodDeclaration.isAnnotationPresent(ANNOTATION_PUT_MAPPING)) {
            return Method.PUT;
        }
        if (methodDeclaration.isAnnotationPresent(ANNOTATION_PATCH_MAPPING)) {
            return Method.PATCH;
        }
        if (methodDeclaration.isAnnotationPresent(ANNOTATION_DELETE_MAPPING)) {
            return Method.DELETE;
        }
        if (methodDeclaration.isAnnotationPresent(ANNOTATION_REQUEST_MAPPING)) {
            AnnotationExpr annotationExpr = methodDeclaration.getAnnotationByName(ANNOTATION_REQUEST_MAPPING).get();
            Optional<Expression> expressionOptional = AnnotationHelper.getAttribute(annotationExpr, "method");
            if (expressionOptional.isPresent()) {
                Expression expression = expressionOptional.get();
                if (expression.isArrayInitializerExpr()) {
                    NodeList<Expression> values = expression.asArrayInitializerExpr().getValues();
                    if (values != null && values.size() > 0) {
                        return Method.valueOf(values.get(0).toString().replaceAll("RequestMethod.", ""));
                    }
                }
                return Method.of(expression.toString().replaceAll("RequestMethod.", ""));
            }
        }
        return Method.GET;
    }


    /**
     * 获取uri数据
     *
     * @param n the n
     * @return uri uri
     */
    public static URI pickUriToParent(ClassOrInterfaceDeclaration n) {
        URI parentUri = null;
        Optional<ClassOrInterfaceDeclaration> parentOptional = ClassDeclarationHelper.getParent(n);
        if (parentOptional.isPresent()) {
            parentUri = pickUriToParent(parentOptional.get());
        }
        URI uri = new URI(pickUri(n.getAnnotations()));
        if (parentUri != null) {
            parentUri.add(uri);
            return parentUri;
        }
        return uri;
    }

    /**
     * 获取uri数据，有多个时，暂时只取第一个
     *
     * @param nodeList the node list
     * @return string string
     */
    public static String pickUri(NodeList<AnnotationExpr> nodeList) {
        for (AnnotationExpr annotationExpr : nodeList) {
            if (ANNOTATION_REQUEST_MAPPINGS.contains(annotationExpr.getNameAsString())) {
                Optional<Expression> expressionOptional = AnnotationHelper.getAnyAttribute(annotationExpr, "value", "path");
                if (expressionOptional.isPresent()) {
                    Expression expression = expressionOptional.get();
                    return ExpressionHelper.getStringValue(expression);
                }
            }
        }
        return "";
    }

    /**
     * 获取headers
     *
     * @param nodeList the node list
     * @return list list
     */
    public static List<String> pickHeaders(NodeList<AnnotationExpr> nodeList) {
        for (AnnotationExpr annotationExpr : nodeList) {
            if (ANNOTATION_REQUEST_MAPPINGS.contains(annotationExpr.getNameAsString())) {
                Optional<Expression> expressionOptional = AnnotationHelper.getAttribute(annotationExpr, "headers");
                if (expressionOptional.isPresent()) {
                    Expression expression = expressionOptional.get();
                    return ExpressionHelper.getStringValues(expression);
                }
            }
        }
        return Lists.newArrayList();
    }

    /**
     * 获取headers
     *
     * @param nodeList the node list
     * @return list list
     */
    public static List<String> pickConsumers(NodeList<AnnotationExpr> nodeList) {
        for (AnnotationExpr annotationExpr : nodeList) {
            if (ANNOTATION_REQUEST_MAPPINGS.contains(annotationExpr.getNameAsString())) {
                Optional<Expression> expressionOptional = AnnotationHelper.getAttribute(annotationExpr, "consumes");
                if (expressionOptional.isPresent()) {
                    Expression expression = expressionOptional.get();
                    return ExpressionHelper.getStringValues(expression);
                }
            }
        }
        return Lists.newArrayList();
    }

    /**
     * 获取headers
     *
     * @param nodeList the node list
     * @return list list
     */
    public static List<String> pickProduces(NodeList<AnnotationExpr> nodeList) {
        for (AnnotationExpr annotationExpr : nodeList) {
            if (ANNOTATION_REQUEST_MAPPINGS.contains(annotationExpr.getNameAsString())) {
                Optional<Expression> expressionOptional = AnnotationHelper.getAttribute(annotationExpr, "produces");
                if (expressionOptional.isPresent()) {
                    Expression expression = expressionOptional.get();
                    return ExpressionHelper.getStringValues(expression);
                }
            }
        }
        return Lists.newArrayList();
    }

}
