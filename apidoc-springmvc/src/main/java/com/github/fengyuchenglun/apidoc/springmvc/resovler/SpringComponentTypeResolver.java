package com.github.fengyuchenglun.apidoc.springmvc.resovler;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.description.UnAvailableTypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.helper.TypeParameterHelper;
import com.github.fengyuchenglun.apidoc.core.resolver.TypeNameResolver;
import com.github.fengyuchenglun.apidoc.core.resolver.TypeResolver;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.types.ResolvedType;

import java.util.Optional;

/**
 * spring组件解析.
 *
 * @author duanledexianxianxian
 */
public class SpringComponentTypeResolver implements TypeResolver, TypeNameResolver {
    @Override
    public boolean accept(ResolvedType type) {
        return isSpringComponent(type);
    }

    @Override
    public TypeDescription resolve(ResolvedType type) {
        Optional<ResolvedType> optional = TypeParameterHelper.getTypeParameter(type.asReferenceType(), 0);
        return optional
                .map(ApiDoc.getInstance().getTypeResolvers()::resolve)
                .orElseGet(UnAvailableTypeDescription::new);
    }

    @Override
    public boolean accept(String id) {
        return isSpringComponent(id);
    }

    @Override
    public TypeDescription resolve(Type type) {
        if (type.isClassOrInterfaceType()) {
            Optional<NodeList<Type>> optional = type.asClassOrInterfaceType().getTypeArguments();
            if (optional.isPresent() && optional.get().size()>0) {
                Type typeArgument = optional.get().get(0);
                return ApiDoc.getInstance().getTypeResolvers().resolve(typeArgument);
            }

        }
        return new UnAvailableTypeDescription();
    }

    /**
     * Is spring component boolean.
     *
     * @param type the type
     * @return the boolean
     */
    private static boolean isSpringComponent(ResolvedType type){
        if(!type.isReferenceType()){
            return false;
        }
        return isSpringComponent(type.asReferenceType().getId());
    }

    /**
     * Is spring component boolean.
     *
     * @param id the id
     * @return the boolean
     */
    private static boolean isSpringComponent(String id){
        return id!=null && (id.startsWith("org.springframework"));
    }
}
