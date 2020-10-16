package com.github.fengyuchenglun.apidoc.core.resolver;

import com.github.fengyuchenglun.apidoc.core.common.description.TypeDescription;
import com.github.fengyuchenglun.apidoc.core.common.helper.TypeNameHelper;
import com.github.fengyuchenglun.apidoc.core.common.description.UnAvailableTypeDescription;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.types.ResolvedType;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 类型解析器.
 *
 * @author duanledexianxian
 */
@Slf4j
public class TypeResolvers {

    /**
     * The Object type resolver.
     */
    private final ObjectTypeResolver objectTypeResolver = new ObjectTypeResolver();

    /**
     * 类型解析器
     */
    private final List<TypeResolver> resolvers = Lists.newArrayList(
            // 基本类型+包装类型
            new PrimitiveTypeResolver(),
            // 数组
            new ArrayTypeResolver(),
            // 字符串
            new StringTypeResolver(),
            // 集合
            new CollectionTypeResolver(),
            // 时间日期
            new DateTypeResolver(),
            // Map
            new MapTypeResolver(),
            // 枚举
            new EnumTypeResolver(),
            // Object
            new SystemObjectTypeResolver()
    );

    /**
     * 名称解析器.
     */
    private final List<TypeNameResolver> nameResolvers = Lists.newArrayList();

    /**
     * 获取类型信息
     * 1. 先进行类型解析
     * 2. 再进行名称解析
     *
     * @param type the type
     * @return type description
     */
    public TypeDescription resolve(Type type) {
        try {
            ResolvedType resolvedType = type.resolve();
            return resolve(resolvedType);
        } catch (UnsolvedSymbolException e) {
            //解析失败时，尝试降级，使用名称解析
            return nameResolve(type);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new UnAvailableTypeDescription();
    }

    /**
     * 解析类型信息
     *
     * @param type the type
     * @return type description
     */
    public TypeDescription resolve(ResolvedType type) {
        for (TypeResolver typeResolver : resolvers) {
            if (typeResolver.accept(type)) {
                return typeResolver.resolve(type);
            }
        }
        if (objectTypeResolver.accept(type)) {
            return objectTypeResolver.resolve(type);
        }
        return new UnAvailableTypeDescription();
    }

    /**
     * Name resolve type description.
     *
     * @param type the type
     * @return the type description
     */
    public TypeDescription nameResolve(Type type) {
        String id = TypeNameHelper.getName(type);
        for (TypeNameResolver nameResolver : nameResolvers) {
            if (nameResolver.accept(id)) {
                return nameResolver.resolve(type);
            }
        }
        log.warn("type({}) resolve failed", id);
        return new UnAvailableTypeDescription();
    }

    /**
     * Add resolver.
     *
     * @param typeResolver the type resolver
     */
    public void addResolver(TypeResolver typeResolver) {
        resolvers.add(typeResolver);
    }

    /**
     * Add name resolver.
     *
     * @param nameResolver the name resolver
     */
    public void addNameResolver(TypeNameResolver nameResolver) {
        nameResolvers.add(nameResolver);
    }


}
