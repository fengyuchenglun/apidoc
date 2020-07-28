package com.github.fengyuchenglun.apidoc.core.common.description;

import com.github.fengyuchenglun.apidoc.core.schema.Row;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/**
 * 未知类型，应该忽略
 * @author duanledexianxianxian
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnAvailableTypeDescription extends TypeDescription {

    public UnAvailableTypeDescription() {
    }

    @Override
    public Object getValue() {
        throw new IllegalArgumentException("unAvailable type not support");
    }

    @Override
    public Collection<Row> rows() {
        throw new IllegalArgumentException("unAvailable type not support");
    }
}
