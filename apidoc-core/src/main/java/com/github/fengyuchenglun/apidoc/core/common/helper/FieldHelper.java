package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.fengyuchenglun.apidoc.core.schema.Cell;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Field helper.
 * @author duanledexianxianxian
 */
public class FieldHelper {

    /**
     * 通过access方法，获取属性名
     *
     * @param methodName access方法名
     * @return 属性名 string
     */
    public static String getByAccessMethod(String methodName) {
        if (methodName.startsWith("is") && methodName.length() > 2) {
            String first = methodName.substring(2, 3);
            String less = methodName.substring(3);
            return first.toLowerCase() + less;
        }
        if (methodName.startsWith("get") && methodName.length() > 3) {
            String first = methodName.substring(3, 4);
            String less = methodName.substring(4);
            return first.toLowerCase() + less;
        }
        return null;
    }

    /**
     * Get initializer optional.
     *
     * @param declaredField the declared field
     * @return the optional
     */
    public static Optional<Expression> getInitializer(ResolvedFieldDeclaration declaredField) {
        if (declaredField instanceof JavaParserFieldDeclaration) {
            JavaParserFieldDeclaration field = (JavaParserFieldDeclaration) declaredField;
            return field.getVariableDeclarator().getInitializer();
        }
        return Optional.empty();
    }


    /**
     * 获取常量
     *
     * @param declaration the declaration
     * @return constants
     */
    public static List<Cell<String>> getConstants(ClassOrInterfaceDeclaration declaration) {
        List<Cell<String>> cells = new ArrayList<>();
        for (FieldDeclaration field : declaration.getFields()) {
            if (field.isStatic() && field.isPublic() && field.isFinal()) {
                VariableDeclarator variable = field.getVariable(0);
                String value = null;
                String description = null;
                if (variable.getInitializer().isPresent()) {
                    value = String.valueOf(ExpressionHelper.getValue(variable.getInitializer().get()));
                }
                if (field.getComment().isPresent()) {
                    description = CommentHelper.getContent(field.getComment().get());
                }
                cells.add(new Cell<>(variable.getNameAsString(), value, description));
            }
        }

        return cells;
    }

}
