package com.github.fengyuchenglun.apidoc.core.schema;

import com.github.fengyuchenglun.apidoc.core.ApiDoc;
import com.github.fengyuchenglun.apidoc.core.common.convert.FieldTypeConvert;
import com.github.fengyuchenglun.apidoc.core.common.convert.IFieldTypeConvert;
import com.github.fengyuchenglun.apidoc.core.common.enums.FieldShowWay;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import static com.github.fengyuchenglun.apidoc.core.common.Constants.FIELD_SPACE;

/**
 * The type Row.
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Row {

    /**
     * The Key.
     */
    String key;
    /**
     * The Type.
     */
    String type;
    /**
     * 是否必填.
     */
    Boolean required;
    /**
     * The Condition.
     */
    String condition;
    /**
     * 默认值.
     */
    String def;
    /**
     * 说明.
     */
    String remark;
    /**
     * mock值
     */
    String mock;

    /**
     * 请求参数类型
     */
    String parameterType;

    /**
     * 是否为统一结果
     */
    Boolean isData;


    /**
     * Instantiates a new Row.
     *
     * @param type the type
     */
    public Row(String type) {
        this.type = type;
    }

    /**
     * 获取html文本样式
     *
     * @return html remark
     */
    public String getHtmlRemark() {
        if (StringUtils.isNotBlank(this.remark)) {
            return this.remark.replaceAll("(\\r\\n)|(\\r)|(\\n)+", "<br>");
        }
        return this.remark;
    }

    /**
     * Gets label type.
     *
     * @return the label type
     */
    public String getLabelType() {
        String javaType = this.type;
        Boolean isArray = false;
        if (StringUtils.endsWith(javaType, "[]")) {
            javaType = StringUtils.substringBefore(this.type, "[]");
            isArray = true;
        }
        IFieldTypeConvert fieldTypeConvert = FieldTypeConvert.javaTypeOf(javaType);
        if (null == fieldTypeConvert) {
            return this.type;
        }
        return isArray ? fieldTypeConvert.getType() + "[]" : fieldTypeConvert.getType();
    }

    /**
     * Gets markdown key.
     *
     * @return the markdown key
     */
    public String getMarkdownKey() {
        if (StringUtils.isNotBlank(this.key)) {
            if (FieldShowWay.TREE.equals(ApiDoc.getInstance().getContext().getFileShowWay())) {
                String[] keys = StringUtils.split(this.key, ".");
                if (keys.length > 1) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < keys.length - 2; i++) {
                        sb.append(FIELD_SPACE);
                    }
                    sb.append("└─");
                    sb.append(keys[keys.length - 1]);
                    return sb.toString();

                }
            }
        }
        return this.key;
    }

}
