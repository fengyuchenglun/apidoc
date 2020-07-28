package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.fengyuchenglun.apidoc.core.schema.Cell;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.resolution.declarations.ResolvedEnumConstantDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedEnumDeclaration;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Enum helper.
 *
 * @author duanledexianxianxian
 */
public class EnumHelper {

    /**
     * Get names string.
     *
     * @param enumDeclaration the enum declaration
     * @return the string
     */
    public static String getNames(ResolvedEnumDeclaration enumDeclaration) {
        StringBuilder sb = new StringBuilder();
        for (ResolvedEnumConstantDeclaration resolvedEnumConstantDeclaration : enumDeclaration.getEnumConstants()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(resolvedEnumConstantDeclaration.getName());
        }
        return sb.toString();
    }

    /**
     * To details list.
     *
     * @param declaration the declaration
     * @return the list
     */
    public static List<Cell<String>> toDetails(EnumDeclaration declaration) {
        List<Cell<String>> cells = Lists.newArrayList();
        for (EnumConstantDeclaration constant : declaration.getEntries()) {
            Cell<String> cell = new Cell<>();
            cell.add(constant.getNameAsString());
            for (Expression expression : constant.getArguments()) {
                Object value = ExpressionHelper.getValue(expression);
                cell.add(String.valueOf(value));
            }
            if (cell.size() == 2) {
                cell.add(1, cell.get(0));
            }
            cells.add(cell);
        }
        return cells;
    }

}
