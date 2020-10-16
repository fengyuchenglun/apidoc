package com.github.fengyuchenglun.apidoc.core.common.helper;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 解决循环依赖问题
 *
 * @author duanledexianxianxian
 */
public class ReferenceContext {

    private static ThreadLocal<ReferenceContext> threadLocal = new ThreadLocal<>();

    /**
     * Get instance reference context.
     *
     * @return the reference context
     */
    public static ReferenceContext getInstance(){
        ReferenceContext context = threadLocal.get();
        if (context == null) {
            context = new ReferenceContext();
            threadLocal.set(context);
        }
        return context;
    }

    private final Set<Object> set = Sets.newHashSet();

    /**
     * Push boolean.
     *
     * @param object the object
     * @return the boolean
     */
    public boolean push(Object object){
        return set.add(object);
    }

    /**
     * Remove boolean.
     *
     * @param object the object
     * @return the boolean
     */
    public boolean remove(Object object){
        return set.remove(object);
    }

    /**
     * Get values set.
     *
     * @return the set
     */
    public Set<Object> getValues(){
        return set;
    }

}
