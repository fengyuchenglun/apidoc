package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.reflectionmodel.ReflectionClassDeclaration;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * The type Class declaration helper.
 *
 * @author duanledexianxianxian
 */
@Slf4j
public class ClassDeclarationHelper {

    /**
     * Gets parent.
     *
     * @param n the n
     * @return the parent
     */
    public static Optional<ClassOrInterfaceDeclaration> getParent(ClassOrInterfaceDeclaration n) {
        if (n.getExtendedTypes().isEmpty()) {
            return Optional.empty();
        }
        return tryToResolve(n.getExtendedTypes().get(0));
    }

    /**
     * Try to resolve optional.
     *
     * @param type the type
     * @return the optional
     */
    public static Optional<ClassOrInterfaceDeclaration> tryToResolve(ClassOrInterfaceType type) {
        try {
            ResolvedReferenceType resolvedReferenceType = type.resolve();
            if (resolvedReferenceType.getTypeDeclaration() instanceof JavaParserClassDeclaration) {
                JavaParserClassDeclaration typeDeclaration = (JavaParserClassDeclaration) resolvedReferenceType.getTypeDeclaration();
                return Optional.of(typeDeclaration.getWrappedNode());
            } else if (resolvedReferenceType.getTypeDeclaration() instanceof ReflectionClassDeclaration) {
                ReflectionClassDeclaration typeDeclaration = (ReflectionClassDeclaration) resolvedReferenceType.getTypeDeclaration();
                System.out.println("type Declaration:" + typeDeclaration);
                //TODO
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Gets class or interface package name.
     *
     * @param classOrInterfaceDeclaration the class or interface declaration
     * @return the class or interface package name
     */
    public static String getClassOrInterfacePackageName(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
        final String[] result = {null};
        classOrInterfaceDeclaration.findCompilationUnit().ifPresent(x -> {
            x.getPackageDeclaration().ifPresent(y -> {
                result[0] = y.getNameAsString() + "." + classOrInterfaceDeclaration.getName();
            });
        });
        return result[0];
    }

}

