package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.javadoc.description.JavadocDescriptionElement;
import com.github.javaparser.javadoc.description.JavadocInlineTag;
import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Comment helper.
 *
 * @author duanledexianxianxian
 */
public class CommentHelper {

    /**
     * 获取完整注释字符串
     *
     * @param description the description
     * @return string description
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


    /**
     * Gets optional comment.
     *
     * @param it the it
     * @return the optional comment
     */
    public static Optional<Comment> getOptionalComment(ResolvedFieldDeclaration it) {
        if (it instanceof JavaParserFieldDeclaration) {
            FieldDeclaration wrappedNode = ((JavaParserFieldDeclaration) it).getWrappedNode();
            Optional<Comment> optional = wrappedNode.getComment();
            return optional;
        }
        return Optional.empty();
    }

    /**
     * 获取字段的注释tag列表.
     *
     * @param resolvedFieldDeclaration the resolved field declaration
     * @return the tags
     */
    public static List<JavadocBlockTag> getFieldTags(ResolvedFieldDeclaration resolvedFieldDeclaration) {
        List<JavadocBlockTag> tagList = Lists.newArrayList();
        Optional<Comment> commentOptional = CommentHelper.getOptionalComment(resolvedFieldDeclaration);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            // 不是javadoc注释
            if (!comment.isJavadocComment()) {
                return tagList;
            }
            Javadoc javadoc = comment.asJavadocComment().parse();
            tagList = javadoc.getBlockTags();
        }
        return tagList;
    }

    /**
     * 注释是否包含tagName标签.
     *
     * @param resolvedFieldDeclaration the resolved field declaration
     * @param tagName                  the tag name
     * @return the boolean
     */
    public static Boolean isFieldTagPresent(ResolvedFieldDeclaration resolvedFieldDeclaration, String tagName) {
        if (StringUtils.isBlank(tagName)) {
            return false;
        }
        List<JavadocBlockTag> tagList = getFieldTags(resolvedFieldDeclaration);
        if (CollectionUtils.isNotEmpty(tagList)) {
            JavadocBlockTag tag = tagList.stream().filter(x -> tagName.equalsIgnoreCase(x.getTagName())).findFirst().orElse(null);
            return null != tag;
        }
        return false;
    }


    public static Boolean isCommentTagPresent(Optional<Comment> commentOptional, String tagName) {
        if (StringUtils.isBlank(tagName) || !commentOptional.isPresent()) {
            return false;
        }
        Comment comment = commentOptional.get();
        // 不是javadoc注释
        if (!comment.isJavadocComment()) {
            return false;
        }
        Javadoc javadoc = comment.asJavadocComment().parse();
        List<JavadocBlockTag> tagList = javadoc.getBlockTags();
        if (CollectionUtils.isNotEmpty(tagList)) {
            JavadocBlockTag tag = tagList.stream().filter(x -> tagName.equalsIgnoreCase(x.getTagName())).findFirst().orElse(null);
            return null != tag;
        }
        return false;
    }


}
