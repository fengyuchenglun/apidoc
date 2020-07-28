package com.github.fengyuchenglun.apidoc.core.common.description;

import com.github.fengyuchenglun.apidoc.core.common.ObjectMappers;
import com.github.fengyuchenglun.apidoc.core.common.helper.StringHelper;
import com.github.fengyuchenglun.apidoc.core.schema.Row;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

/**
 * 对象类型描述
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ObjectTypeDescription extends TypeDescription {

    /**
     * The Members.
     */
    protected List<TypeDescription> members = Lists.newArrayList();
    /**
     * The Value.
     */
    private ObjectNode value = ObjectMappers.instance.createObjectNode();

    @Override
    public void setRequired(Boolean required) {
        this.required = required;
        this.members.get(0).setRequired(required);
    }

    /**
     * Merge.
     *
     * @param other the other
     */
    public void merge(ObjectTypeDescription other) {
        value.setAll(other.getValue());
        members.addAll(other.members);
    }

    /**
     * Add.
     *
     * @param component the component
     */
    public void add(TypeDescription component) {
        members.add(component);
        if (component.isPrimitive()) {
            primitive(component.asPrimitive());
        } else if (component.isString()) {
            value.put(component.getKey(), component.asString().getValue());
        } else if (component.isArray()) {
            value.set(component.getKey(), component.asArray().getValue());
        } else if (component.isObject()) {
            value.set(component.getKey(), component.asObject().getValue());
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
                value.put(typeDescription.getKey(), (byte) typeDescription.getValue());
                break;
            case "short":
                value.put(typeDescription.getKey(), (short) typeDescription.getValue());
                break;
            case "char":
                value.put(typeDescription.getKey(), (char) typeDescription.getValue());
                break;
            case "int":
                value.put(typeDescription.getKey(), (int) typeDescription.getValue());
                break;
            case "long":
                value.put(typeDescription.getKey(), (long) typeDescription.getValue());
                break;
            case "boolean":
                value.put(typeDescription.getKey(), (boolean) typeDescription.getValue());
                break;
            case "float":
                value.put(typeDescription.getKey(), (float) typeDescription.getValue());
                break;
            case "double":
                value.put(typeDescription.getKey(), (double) typeDescription.getValue());
                break;
        }
    }

    @Override
    public void setKey(String key) {
        super.setKey(key);
        String memberPrefix = fullKey();
        for (TypeDescription member : members) {
            if (member.isAvailable()) {
                member.setPrefix(memberPrefix);
            }
        }
    }

    @Override
    public void setPrefix(String prefix) {
        super.setPrefix(prefix);
        String memberPrefix = fullKey();
        for (TypeDescription member : members) {
            if (member.isAvailable()) {
                member.setPrefix(memberPrefix);
            }
        }
    }

    @Override
    public String fullKey() {
        return StringHelper.join(".", prefix, key);
    }

    @Override
    public ObjectNode getValue() {
        return value;
    }

    @Override
    public Collection<Row> rows() {
        Collection<Row> rows = super.rows();
        for (TypeDescription member : members) {
            if (member.isAvailable()) {
                rows.addAll(member.rows());
            }
        }
        return rows;
    }

    @Override
    public Collection<Row> rows(String parameterType) {
        Collection<Row> rows = super.rows(null);
        for (TypeDescription member : members) {
            if (member.isAvailable()) {
                rows.addAll(member.rows(parameterType));
            }
        }
        return rows;
    }
}
