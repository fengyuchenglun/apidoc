package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JavaBean 验证器帮助类
 *
 * @author duanledexianxianxian
 */
public class ValidationHelper {

    /**
     * The constant NULL.
     */
    public static final String NULL = "Null";
    /**
     * The constant NOT_NULL.
     */
    public static final String NOT_NULL = "NotNull";
    /**
     * The constant ASSERT_TRUE.
     */
    public static final String ASSERT_TRUE = "AssertTrue";
    /**
     * The constant ASSERT_FALSE.
     */
    public static final String ASSERT_FALSE = "AssertFalse";
    /**
     * The constant NOT_EMPTY.
     */
    public static final String NOT_EMPTY = "NotEmpty";
    /**
     * The constant NOT_BLANK.
     */
    public static final String NOT_BLANK = "NotBlank";
    /**
     * The constant EMAIL.
     */
    public static final String EMAIL = "Email";

    /**
     * The constant MIN.
     */
    public static final String MIN = "Min";
    /**
     * The constant MAX.
     */
    public static final String MAX = "Max";
    /**
     * The constant SIZE.
     */
    public static final String SIZE = "Size";

    /**
     * The constant values.
     */
    public static final List<String> values = Lists.newArrayList(NULL,NOT_NULL,NOT_EMPTY,EMAIL,NOT_BLANK);

    /**
     * Get validations list.
     *
     * @param declaredField the declared field
     * @return the list
     */
    public static List<String> getValidations(ResolvedFieldDeclaration declaredField){
        List<String> result = new ArrayList<>();
        if(declaredField instanceof JavaParserFieldDeclaration){
            FieldDeclaration fieldDeclaration = ((JavaParserFieldDeclaration) declaredField).getWrappedNode();
            for (String value : values) {
                Optional<AnnotationExpr> optional = fieldDeclaration.getAnnotationByName(value);
                if(optional.isPresent()){
                    result.add(value);
                }
            }
        }
        return result;
    }

}
