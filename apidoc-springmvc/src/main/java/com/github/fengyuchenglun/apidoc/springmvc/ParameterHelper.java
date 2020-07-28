package com.github.fengyuchenglun.apidoc.springmvc;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.Parameter;

/**
 * The type Parameter helper.
 * @author duanledexianxianxian
 */
public class ParameterHelper {

    /**
     * The constant ANNOTATION_REQUEST_PARAM.
     */
    public static final String ANNOTATION_REQUEST_PARAM = "RequestParam";
    /**
     * The constant ANNOTATION_REQUEST_HEADER.
     */
    public static final String ANNOTATION_REQUEST_HEADER = "RequestHeader";
    /**
     * The constant ANNOTATION_REQUEST_ATTRIBUTE.
     */
    public static final String ANNOTATION_REQUEST_ATTRIBUTE = "RequestAttribute";
    /**
     * The constant ANNOTATION_REQUEST_PART.
     */
    public static final String ANNOTATION_REQUEST_PART = "RequestPart";
    /**
     * The constant ANNOTATION_COOKIE_VALUE.
     */
    public static final String ANNOTATION_COOKIE_VALUE = "CookieValue";
    /**
     * The constant ANNOTATION_PATH_VARIABLE.
     */
    public static final String ANNOTATION_PATH_VARIABLE = "PathVariable";
    /**
     * The constant ANNOTATION_REQUEST_BODY.
     */
    public static final String ANNOTATION_REQUEST_BODY = "RequestBody";
    /**
     * The constant ANNOTATION_MULTIPART_FILE.
     */
    public static final String ANNOTATION_MULTIPART_FILE = "MultipartFile";

    /**
     * Has request body boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     */
    public static boolean hasRequestBody(NodeList<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            if (isRequestBody(parameter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是请求参数
     *
     * @param parameter the parameter
     * @return boolean boolean
     */
    public static boolean isRequestParam(Parameter parameter) {
        if (!parameter.isAnnotationPresent(ANNOTATION_PATH_VARIABLE) &&
                !parameter.isAnnotationPresent(ANNOTATION_REQUEST_BODY) &&
                !parameter.isAnnotationPresent(ANNOTATION_REQUEST_HEADER) &&
                !parameter.isAnnotationPresent(ANNOTATION_COOKIE_VALUE) &&
                !parameter.isAnnotationPresent(ANNOTATION_REQUEST_PART) &&
                !parameter.isAnnotationPresent(ANNOTATION_MULTIPART_FILE) &&
                !parameter.isAnnotationPresent(ANNOTATION_REQUEST_ATTRIBUTE)) {
            return true;
        }
        return false;
    }

    /**
     * Is path variable boolean.
     *
     * @param parameter the parameter
     * @return the boolean
     */
    public static boolean isPathVariable(Parameter parameter) {
        if (parameter.isAnnotationPresent(ANNOTATION_PATH_VARIABLE)) {
            return true;
        }
        return false;
    }

    /**
     * Is request body boolean.
     *
     * @param parameter the parameter
     * @return the boolean
     */
    public static boolean isRequestBody(Parameter parameter) {
        if (parameter.isAnnotationPresent(ANNOTATION_REQUEST_BODY)) {
            return true;
        }
        return false;
    }

    /**
     * Is request header boolean.
     *
     * @param parameter the parameter
     * @return the boolean
     */
    public static boolean isRequestHeader(Parameter parameter) {
        if (parameter.isAnnotationPresent(ANNOTATION_REQUEST_HEADER)) {
            return true;
        }
        return false;
    }

}
