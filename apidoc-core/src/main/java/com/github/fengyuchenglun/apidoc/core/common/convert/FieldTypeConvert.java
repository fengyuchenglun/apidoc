package com.github.fengyuchenglun.apidoc.core.common.convert;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 字段转换.
 *
 * @author duanledexianxianxian
 */
public enum FieldTypeConvert implements IFieldTypeConvert {
    /**
     * String.
     */
    STRING("String", "String"),
    /**
     * Byte.
     */
    BYTE("Byte", "Byte"),
    /**
     * Short.
     */
    SHORT("Integer", "Short"),
    /**
     * Char.
     */
    CHAR("String", "Char"),
    /**
     * Int.
     */
    INT("Integer", "int"),
    /**
     * Long.
     */
    LONG("Long", "Long"),
    /**
     * Boolean.
     */
    BOOLEAN("Boolean", "Boolean"),
    /**
     * Float.
     */
    FLOAT("Float", "Float"),
    /**
     * Double.
     */
    DOUBLE("Double", "Double"),
    /**
     * Local date.
     */
    LOCAL_DATE("Date", "LocalDate"),
    /**
     * Local date time.
     */
    LOCAL_DATE_TIME("DateTime", "LocalDateTime"),
    /**
     * Local time.
     */
    LOCAL_TIME("Time", "LocalTime"),

    /**
     * Other.
     */
    OTHER("object", "Object"),
    ;

    /**
     * The Type.
     */
    private final String type;

    /**
     * The Java type.
     */
    private final String javaType;

    /**
     * Instantiates a new Field type convert.
     *
     * @param type     the type
     * @param javaType the java type
     */
    FieldTypeConvert(String type, String javaType) {
        this.type = type;
        this.javaType = javaType;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getJavaType() {
        return javaType;
    }

    /**
     * The Field type cache map.
     */
    private static Map<String, IFieldTypeConvert> FIELD_TYPE_CACHE_MAP = Maps.newConcurrentMap();

    static {
        for (IFieldTypeConvert fieldTypeConvert : FieldTypeConvert.values()) {
            FIELD_TYPE_CACHE_MAP.put(fieldTypeConvert.getJavaType().toLowerCase(), fieldTypeConvert);
        }
    }

    /**
     * Java type of field type convert.
     *
     * @param javaType the java type
     * @return the field type convert
     */
    public static IFieldTypeConvert javaTypeOf(String javaType) {
        return FIELD_TYPE_CACHE_MAP.get(javaType.toLowerCase());
    }
}
