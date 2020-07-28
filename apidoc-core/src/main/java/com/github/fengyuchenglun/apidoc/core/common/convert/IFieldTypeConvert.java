package com.github.fengyuchenglun.apidoc.core.common.convert;

/**
 * 字段类型转换接口.
 *
 * @author duanledexianxianxian
 */
public interface IFieldTypeConvert {
    /**
     * 获取字段类型
     * 展示类型
     *
     * @return 字段类型 type
     */
    String getType();

    /**
     * 获取字段java类型
     * 除java.lang包下的包类型
     *
     * @return 获取字段java类型 java type
     */
    String getJavaType();
}
