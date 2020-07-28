package com.github.fengyuchenglun.apidoc.core.common.description;

import com.github.fengyuchenglun.apidoc.core.common.ObjectMappers;
import com.github.fengyuchenglun.apidoc.core.common.helper.StringHelper;
import com.github.fengyuchenglun.apidoc.core.schema.Row;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 数组类型描述
 *
 * @author duanledexianxianxian
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArrayTypeDescription extends TypeDescription {

    /**
     * The Value.
     */
    protected ArrayNode value;
    /**
     * The Component.
     */
    protected TypeDescription component;

    /**
     * Instantiates a new Array type description.
     *
     * @param component the component
     */
    public ArrayTypeDescription(TypeDescription component) {
        this.component = component;
        this.value = ObjectMappers.instance.createArrayNode();
        if (component.isAvailable()) {
            this.type = component.getType() + "[]";
            if (component.isPrimitive()) {
                primitive(component.asPrimitive());
            } else if (component.isString()) {
                value.add(component.asString().getValue());
            } else if (component.isArray()) {
                value.add(component.asArray().getValue());
            } else if (component.isObject()) {
                value.add(component.asObject().getValue());
            }
        } else {
            this.type = "[]";
        }
    }

    /**
     * Primitive.
     *
     * @param typeDescription the type description
     */
    public void primitive(PrimitiveTypeDescription typeDescription) {
        switch (typeDescription.getType()) {
            case "byte":
                value.add((byte) typeDescription.getValue());
                break;
            case "short":
                value.add((short) typeDescription.getValue());
                break;
            case "char":
                value.add((char) typeDescription.getValue());
                break;
            case "int":
                value.add((int) typeDescription.getValue());
                break;
            case "long":
                value.add((long) typeDescription.getValue());
                break;
            case "boolean":
                value.add((boolean) typeDescription.getValue());
                break;
            case "float":
                value.add((float) typeDescription.getValue());
                break;
            case "double":
                value.add((double) typeDescription.getValue());
                break;
        }
    }

    @Override
    public void setKey(String key) {
        super.setKey(key);
        if (component.isAvailable()) {
            component.setPrefix(fullKey());
        }
    }

    @Override
    public void setPrefix(String prefix) {
        super.setPrefix(prefix);
        if (component.isAvailable()) {
            component.setPrefix(fullKey());
        }
    }


    @Override
    public String fullKey() {
        return StringHelper.join(".", prefix, key);
    }

    @Override
    public ArrayNode getValue() {
        return value;
    }

    @Override
    public Collection<Row> rows(String parameterType) {
        ArrayList<Row> rows = new ArrayList<>();
        if (key != null) {
            rows.addAll(super.rows(parameterType));
        }
        if (component.isAvailable()) {
            rows.addAll(component.rows(parameterType));
        }
        return rows;
    }

    @Override
    public Collection<Row> rows() {
        return rows(null);
    }
}
