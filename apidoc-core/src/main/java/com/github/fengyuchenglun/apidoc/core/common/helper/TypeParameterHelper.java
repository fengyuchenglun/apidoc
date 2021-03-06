package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.utils.Pair;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Type parameter helper.
 *
 * @author duanledexianxianxian
 */
public class TypeParameterHelper {

    /**
     * 获取泛型信息
     *
     * @param referenceType the reference type
     * @param index         位置信息
     * @return optional optional
     */
    public static Optional<ResolvedType> getTypeParameter(ResolvedReferenceType referenceType, int index){
        List<Pair<ResolvedTypeParameterDeclaration, ResolvedType>> typeParameters = referenceType.getTypeParametersMap();
        if(typeParameters.size()>index){
            Pair<ResolvedTypeParameterDeclaration, ResolvedType> pair = typeParameters.get(index);
            return Optional.of(pair.b);
        }
        return Optional.empty();
    }

    /**
     * 获取泛型信息
     *
     * @param referenceType the reference type
     * @param a             the a
     * @return the optional
     */
    public static Optional<ResolvedType> getTypeParameter(ResolvedReferenceType referenceType, String a){
        List<Pair<ResolvedTypeParameterDeclaration, ResolvedType>> typeParameters = referenceType.getTypeParametersMap();
        for (Pair<ResolvedTypeParameterDeclaration, ResolvedType> pair : typeParameters) {
            if(Objects.equals(pair.a.getName(),a)){
                return Optional.of(pair.b);
            }
        }
        return Optional.empty();
    }

    /**
     * 使用父类的泛型
     *
     * @param parent the parent
     * @param field  the field
     * @return the resolved type
     */
    public static ResolvedType useClassTypeParameter(ResolvedReferenceType parent, ResolvedReferenceType field ){
        for (Pair<ResolvedTypeParameterDeclaration, ResolvedType> pair : field.getTypeParametersMap()) {
            if(pair.b.isTypeVariable()){
                Optional<ResolvedType> typeParameter = TypeParameterHelper.getTypeParameter(parent, pair.b.asTypeVariable().describe());
                if (typeParameter.isPresent()) {
                    return field.replaceTypeVariables(pair.b.asTypeVariable().asTypeParameter(), typeParameter.get());
                }
            }
        }
        return field;
    }
}
