package com.github.fengyuchenglun.apidoc.core.schema;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Header.
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
public class Header {

    /**
     * The constant APPLICATION_JSON.
     */
    public static final Header APPLICATION_JSON = new Header("Content-Type", "application/json");
    /**
     * The constant X_FORM_WWW_URLENCODED.
     */
    public static final Header X_FORM_WWW_URLENCODED = new Header("Content-Type", "x-www-form-urlencoded");

    /**
     * The Key.
     */
    String key;
    /**
     * The Value.
     */
    String value;
    /**
     * The Description.
     */
    String description;

    /**
     * Instantiates a new Header.
     *
     * @param key   the key
     * @param value the value
     */
    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Value of header.
     *
     * @param text the text
     * @return the header
     */
    public static Header valueOf(String text) {
        String[] arr = text.split(":");
        Header header = new Header();
        header.setKey(arr[0]);
        if (arr.length > 1) {
            header.setValue(arr[1]);
        }
        return header;
    }

    @Override
    public String toString() {
        return key + ": " + value;
    }

}
