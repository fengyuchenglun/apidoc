package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.javadoc.description.JavadocDescriptionElement;
import com.github.javaparser.javadoc.description.JavadocInlineTag;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Comment helper.
 * @author duanledexianxianxian
 */
public class CommentHelper {

    /**
     * 获取完整注释字符串
     *
     * @param description the description
     * @return string
     */
    public static String getDescription(JavadocDescription description) {
        return description.getElements()
                .stream()
                // 过滤非单行注释
                .filter(e -> !(e instanceof JavadocInlineTag))
                .map(JavadocDescriptionElement::toText).collect(Collectors.joining());
    }

    /**
     * Get content string.
     *
     * @param comment the comment
     * @return the string
     */
    public static String getContent(Comment comment) {
        if (!comment.isJavadocComment()) {
            return comment.getContent();
        }
        return getDescription(comment.asJavadocComment().parse().getDescription());
    }


    /**
     * Get comment string.
     *
     * @param it the it
     * @return the string
     */
    public static String getComment(MethodUsage it) {
        if (it.getDeclaration() instanceof JavaParserMethodDeclaration) {
            MethodDeclaration wrappedNode = ((JavaParserMethodDeclaration) it.getDeclaration()).getWrappedNode();
            Optional<Comment> optional = wrappedNode.getComment();
            if (optional.isPresent()) {
                return CommentHelper.getContent(optional.get());
            }
        }
        return null;
    }

    /**
     * 获取字段注释.
     *
     * @param it the it
     * @return the string
     */
    public static String getComment(ResolvedFieldDeclaration it) {
        if (it instanceof JavaParserFieldDeclaration) {
            FieldDeclaration wrappedNode = ((JavaParserFieldDeclaration) it).getWrappedNode();
            Optional<Comment> optional = wrappedNode.getComment();
            if (optional.isPresent()) {
                return CommentHelper.getContent(optional.get());
            }
        } else if (it instanceof JavaParserClassDeclaration) {
            JavaParserClassDeclaration classDeclaration = (JavaParserClassDeclaration) it;
        }
        return null;
    }


    public static Optional<Comment> getOptionalComment(ResolvedFieldDeclaration it) {
        if (it instanceof JavaParserFieldDeclaration) {
            FieldDeclaration wrappedNode = ((JavaParserFieldDeclaration) it).getWrappedNode();
            Optional<Comment> optional = wrappedNode.getComment();
            return optional;
        }
        return Optional.empty();
    }


}
