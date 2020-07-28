package com.github.fengyuchenglun.apidoc.core.common.description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字符串类型
 * @author duanledexianxianxian
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StringTypeDescription extends TypeDescription {

    /**
     * Instantiates a new String type description.
     *
     * @param type         the type
     * @param charSequence the char sequence
     */
    public StringTypeDescription(String type, CharSequence charSequence) {
        this.type = type;
        value = charSequence.toString();
    }

    @Override
    public String getValue() {
        return (String) value;
    }
}
