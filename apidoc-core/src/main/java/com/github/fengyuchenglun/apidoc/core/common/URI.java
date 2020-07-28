package com.github.fengyuchenglun.apidoc.core.common;

import com.github.fengyuchenglun.apidoc.core.common.helper.StringHelper;
import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Uri.
 *
 * @author duanledexianxianxian
 */
@Getter
@EqualsAndHashCode
public class URI {

    /**
     * The Text.
     */
    private String text;

    /**
     * The Next.
     */
    private URI next;

    /**
     * Instantiates a new Uri.
     */
    public URI() {
    }

    /**
     * Instantiates a new Uri.
     *
     * @param text the text
     */
    public URI(String text) {
        this.text = text;
    }

    /**
     * Add uri.
     *
     * @param text the text
     * @return the uri
     */
    public URI add(String text) {
        return add(new URI(text));
    }

    /**
     * Add uri.
     *
     * @param uri the uri
     * @return the uri
     */
    public URI add(URI uri) {
        if (next != null) {
            next.add(uri);
        } else {
            next = uri;
        }
        return this;
    }

    /**
     * Remove.
     *
     * @param uri the uri
     */
    public void remove(URI uri) {
        if (Objects.equals(next, uri)) {
            next = null;
        } else {
            next.remove(uri);
        }
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        appendTo(list);
        StringBuilder builder = new StringBuilder();
        for (String text : list) {
            if (StringHelper.nonBlank(text)) {
                builder.append("/");
                builder.append(text);
            }
        }
        return builder.toString();
    }

    /**
     * Append to.
     *
     * @param list the list
     */
    private void appendTo(List<String> list) {
        if (Objects.nonNull(text)) {
            list.addAll(Lists.newArrayList(text.split("/")));
        }
        if (next != null) {
            next.appendTo(list);
        }
    }
}
