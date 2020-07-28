package com.github.fengyuchenglun.apidoc.core.schema;

import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.fengyuchenglun.apidoc.core.common.helper.EnumHelper;
import com.github.fengyuchenglun.apidoc.core.common.helper.FieldHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * 附录
 *
 * @author duanledexianxianxian
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class Appendix extends Node {

    /**
     * The Cells.
     */
    List<Cell<String>> cells = new ArrayList<>();

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return cells.isEmpty();
    }


    /**
     * Parse appendix.
     *
     * @param n the n
     * @return the appendix
     */
    @Nullable
    public static Appendix parse(JavadocComment n) {
        if (!n.getCommentedNode().isPresent()) {
            return null;
        }
        final com.github.javaparser.ast.Node node = n.getCommentedNode().get();
        if (!(node instanceof BodyDeclaration)) {
            return null;
        }
        final BodyDeclaration bodyDeclaration = (BodyDeclaration) node;
        // 不是枚举且不是类
        if (!bodyDeclaration.isEnumDeclaration() && !bodyDeclaration.isClassOrInterfaceDeclaration()) {
            return null;
        }
        Appendix appendix = new Appendix();
        // 是枚举
        if (bodyDeclaration.isEnumDeclaration()) {
            appendix.getCells().addAll(EnumHelper.toDetails(bodyDeclaration.asEnumDeclaration()));
        } else if (bodyDeclaration.isClassOrInterfaceDeclaration()) {
            // 常量类
            appendix.getCells().addAll(FieldHelper.getConstants(bodyDeclaration.asClassOrInterfaceDeclaration()));
        }
        if (node instanceof NodeWithSimpleName) {
            appendix.setName(((NodeWithSimpleName) node).getNameAsString());
        }
        if (node.getComment().isPresent()) {
            appendix.accept(node.getComment().get());
        }
        return appendix;
    }

}
