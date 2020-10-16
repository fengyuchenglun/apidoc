package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import java.util.Optional;

/**
 * The type Compilation unit helper.
 *
 * @author duanledexianxianxian
 */
public class CompilationUnitHelper {

    /**
     * Gets compilation unit.
     *
     * @param node the node
     * @return the compilation unit
     */
    public static Optional<CompilationUnit> getCompilationUnit(Node node) {
        if (node instanceof CompilationUnit) {
            return Optional.of((CompilationUnit) node);
        }
        if (node.getParentNode().isPresent()) {
            return getCompilationUnit(node.getParentNode().get());
        }
        return Optional.empty();
    }

}
