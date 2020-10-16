package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ClassLoaderTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;

import java.io.File;
import java.nio.file.Path;

/**
 * @author 44902
 */
public class ModuleParserTypeSolver extends JavaParserTypeSolver {
    private ClassLoaderTypeSolver classLoaderTypeSolver =

            new ClassLoaderTypeSolver(ModuleParserTypeSolver.class.getClassLoader());

    public ModuleParserTypeSolver(File srcDir) {
        super(srcDir);
    }

    public ModuleParserTypeSolver(String srcDir) {
        super(srcDir);
    }

    public ModuleParserTypeSolver(File srcDir, ParserConfiguration parserConfiguration) {
        super(srcDir, parserConfiguration);
    }

    public ModuleParserTypeSolver(String srcDir, ParserConfiguration parserConfiguration) {
        super(srcDir, parserConfiguration);
    }

    public ModuleParserTypeSolver(Path srcDir, ParserConfiguration parserConfiguration) {
        super(srcDir, parserConfiguration);
    }

    public ModuleParserTypeSolver(Path srcDir) {
        super(srcDir);
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        try {
            SymbolReference<ResolvedReferenceTypeDeclaration> reference = super.tryToSolveType(name);
            if (!reference.isSolved()) {
                reference = classLoaderTypeSolver.tryToSolveType(name);
            }
            return reference;
        } catch (Exception e) {
            return classLoaderTypeSolver.tryToSolveType(name);
        }
    }
}
