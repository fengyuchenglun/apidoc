package com.github.fengyuchenglun.apidoc.core.parser;

import com.github.fengyuchenglun.apidoc.core.common.helper.CommentHelper;
import com.github.fengyuchenglun.apidoc.core.schema.*;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.helper.OptionalHelper;

import java.util.Optional;

import static com.github.fengyuchenglun.apidoc.core.common.Constants.*;


/**
 * 访问解析.
 * 整个程序的驱动器
 *
 * @author duanledexianxianxian
 */
public class VisitorParser extends VoidVisitorAdapter<Node> {

    /**
     * The Parser strategy.
     */
    private ParserStrategy parserStrategy;

    /**
     * Sets parser strategy.
     *
     * @param parserStrategy the parser strategy
     */
    public void setParserStrategy(ParserStrategy parserStrategy) {
        this.parserStrategy = parserStrategy;
    }


    /**
     * 类或者接口声明
     *
     * @param classOrInterfaceDeclaration 类或者接口
     * @param arg                         参数
     */
    @Override
    public void visit(final ClassOrInterfaceDeclaration classOrInterfaceDeclaration, final Node arg) {
        if (arg instanceof Project) {
            Project project = (Project) arg;
            // 章节
            Chapter chapter = new Chapter();
            classOrInterfaceDeclaration.getFullyQualifiedName().ifPresent(chapter::setId);
            chapter.setName(classOrInterfaceDeclaration.getNameAsString());
            classOrInterfaceDeclaration.getComment().ifPresent(chapter::accept);


            OptionalHelper.any(chapter.getTag(TAG_CUSTOM_JAVA_DOC_BOOK), chapter.getTag(TAG_CUSTOM_JAVA_DOC_GROUP))
                     .ifPresent(tag -> chapter.setBookName(tag.getContent()));

            if (parserStrategy.accept(classOrInterfaceDeclaration)) {
                parserStrategy.visit(classOrInterfaceDeclaration, chapter);
                project.addChapter(chapter);
                super.visit(classOrInterfaceDeclaration, chapter);
            }
        }
    }

    /**
     * 枚举
     *
     * @param enumDeclaration 枚举
     * @param arg             参数
     */
    @Override
    public void visit(final EnumDeclaration enumDeclaration, final Node arg) {
        // 访问枚举
        if (arg instanceof Project) {
//            Project project = (Project) arg;
//            // 章节
//            Chapter chapter = new Chapter();
//            enumDeclaration.getFullyQualifiedName().ifPresent(chapter::setId);
//            chapter.setName(enumDeclaration.getNameAsString());
//            enumDeclaration.getComment().ifPresent(chapter::accept);
//            OptionalHelper.any(chapter.getTag("book"), chapter.getTag("group"))
//                    .ifPresent(tag -> chapter.setBookName(tag.getContent()));
//            project.addChapter(chapter);
            //放入附录
            if (enumDeclaration.getJavadocComment().isPresent()
                    && scanCode(enumDeclaration.getComment())
            ) {
                Appendix appendix = Appendix.parse(enumDeclaration.getJavadocComment().get());
                ApiDoc.getInstance().getProject().getAppendices().add(appendix);
            }
//            super.visit(enumDeclaration, chapter);
        }
    }

    private Boolean scanCode(Optional<Comment> commentOptional) {
        return ApiDoc.getInstance().getContext().getScanCode() && CommentHelper.isCommentTagPresent(commentOptional, TAG_JAVA_DOC_CODE);
    }


    /**
     * 注释
     *
     * @param javadocComment 注释
     * @param arg            参数
     */
    @Override
    public void visit(JavadocComment javadocComment, Node arg) {
        if (arg instanceof Chapter) {
            Chapter chapter = (Chapter) arg;
            OptionalHelper.any(chapter.getTag(TAG_JAVA_DOC_CODE))
                    .ifPresent(tag -> {
                        if (javadocComment.getCommentedNode().isPresent()) {
                            com.github.javaparser.ast.Node commentedNode = javadocComment.getCommentedNode().get();
                            // 常量类||枚举类
                            if ((commentedNode instanceof ClassOrInterfaceDeclaration
                                    || commentedNode instanceof EnumDeclaration)
                                    && scanCode(javadocComment.getComment())
                            ) {
                                Appendix appendix = Appendix.parse(javadocComment);
                                ApiDoc.getInstance().getProject().getAppendices().add(appendix);
                            }
                        }
                    });

            OptionalHelper.any(chapter.getTag(TAG_JAVA_DOC_RESULT_DATA), chapter.getTag(TAG_JAVA_DOC_PAGE_RESULT_DATA))
                    .ifPresent(tag -> {
                        if (javadocComment.getCommentedNode().isPresent()) {
                            com.github.javaparser.ast.Node commentedNode = javadocComment.getCommentedNode().get();
                            // 常量类||枚举类
                            if (commentedNode instanceof ClassOrInterfaceDeclaration) {
                                ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) commentedNode;
                                if (TAG_JAVA_DOC_RESULT_DATA.equalsIgnoreCase(tag.getId())) {
                                    ApiDoc.getInstance().setResultDataClassOrInterfaceDeclaration(classOrInterfaceDeclaration);
                                }
                                if (TAG_JAVA_DOC_PAGE_RESULT_DATA.equalsIgnoreCase(tag.getId())) {
                                    ApiDoc.getInstance().setPageResultDataClassOrInterfaceDeclaration(classOrInterfaceDeclaration);
                                }
                            }
                        }
                    });
        }
        super.visit(javadocComment, arg);
    }

    /**
     * 方法声明
     *
     * @param methodDeclaration 方法
     * @param arg               参数
     */
    @Override
    public void visit(final MethodDeclaration methodDeclaration, final Node arg) {
        if (arg instanceof Chapter && parserStrategy.accept(methodDeclaration)) {
            Chapter chapter = (Chapter) arg;
            Section section = new Section();
            section.setId(methodDeclaration.getNameAsString());
            section.setName(methodDeclaration.getNameAsString());
            section.setIndex(chapter.getSections().size());
            methodDeclaration.getComment().ifPresent(section::accept);

            parserStrategy.visit(methodDeclaration, chapter, section);
            chapter.getSections().add(section);
            super.visit(methodDeclaration, section);
        }
    }

}
