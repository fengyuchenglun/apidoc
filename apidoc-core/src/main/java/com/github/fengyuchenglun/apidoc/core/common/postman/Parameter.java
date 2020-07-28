package com.github.fengyuchenglun.apidoc.core.common.postman;


import com.github.fengyuchenglun.apidoc.core.schema.Row;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Parameter{

    String key;
    String type;
    Object value;
    String description;
    boolean disabled = false;

    public static Parameter of(Row row) {
        Parameter parameter = new Parameter();
        parameter.setKey(row.getKey());
        parameter.setType(row.getType());
        parameter.setValue(row.getDef());
        parameter.setDescription(row.getRemark());
        return parameter;
    }
}