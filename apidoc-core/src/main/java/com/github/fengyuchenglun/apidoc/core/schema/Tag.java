package com.github.fengyuchenglun.apidoc.core.schema;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * //@param username 用户名
 * id    key      content
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Slf4j
@Data
public class Tag {

    /**
     * The Id.
     */
    String id;
    /**
     * The Key.
     */
    String key;
    /**
     * The Content.
     */
    String content;

    /**
     * Get int content int.
     *
     * @param def the def
     * @return the int
     */
    public int getIntContent(int def) {
        if (Objects.nonNull(content)) {
            try {
                return Integer.parseInt(content);
            } catch (Exception e) {
                log.warn(content + " parse error");
            }
        }
        return def;
    }

}
