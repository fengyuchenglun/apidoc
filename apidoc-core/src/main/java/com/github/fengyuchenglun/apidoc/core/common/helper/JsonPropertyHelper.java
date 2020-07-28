package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;

import java.util.Optional;

/**
 * The type Json property helper.
 *
 * @author duanledexianxianxian
 */
public class JsonPropertyHelper {

    /**
     * The constant ANNOTAION_JSON_PROPERTY.
     */
    public static final String ANNOTAION_JSON_PROPERTY = "JsonProperty";
    /**
     * The constant ANNOTAION_JSON_FIELD.
     */
    public static final String ANNOTAION_JSON_FIELD = "JSONField";
    /**
     * The constant ANNOTAION_SERIALIZED_NAME.
     */
    public static final String ANNOTAION_SERIALIZED_NAME = "SerializedName";

    /**
     * Get json name optional.
     *
     * @param declaredField the declared field
     * @return the optional
     */
    public static Optional<String> getJsonName(ResolvedFieldDeclaration declaredField){
        if(declaredField instanceof JavaParserFieldDeclaration){
            FieldDeclaration fieldDeclaration = ((JavaParserFieldDeclaration) declaredField).getWrappedNode();
            return OptionalHelper.any(
                    getStringValue(fieldDeclaration, ANNOTAION_JSON_PROPERTY, "value"),
                    getStringValue(fieldDeclaration, ANNOTAION_JSON_FIELD, "name"),
                    getStringValue(fieldDeclaration, ANNOTAION_SERIALIZED_NAME, "value")
            );
        }
        return Optional.empty();
    }

    /**
     * Get string value optional.
     *
     * @param fieldDeclaration the field declaration
     * @param anno             the anno
     * @param attr             the attr
     * @return the optional
     */
    public static Optional<String> getStringValue(FieldDeclaration fieldDeclaration, String anno, String attr){
        Optional<AnnotationExpr> optional = fieldDeclaration.getAnnotationByName(anno);
        if (optional.isPresent()) {
            Optional<Expression> expr = AnnotationHelper.getAttribute(optional.get(),attr);
            if (expr.isPresent()) {
                return Optional.of(ExpressionHelper.getStringValue(expr.get()));
            }
        }
        return Optional.empty();
    }

}
