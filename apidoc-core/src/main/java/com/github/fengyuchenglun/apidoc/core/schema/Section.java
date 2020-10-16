package com.github.fengyuchenglun.apidoc.core.schema;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.fengyuchenglun.apidoc.core.common.ObjectMappers;
import com.github.fengyuchenglun.apidoc.core.common.QueryStringBuilder;
import com.github.fengyuchenglun.apidoc.core.common.helper.StringHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static com.github.fengyuchenglun.apidoc.core.common.Constants.TAG_JAVA_DOC_RETURN;

/**
 * 小节，一个请求，封装为一个小节
 *
 * @author fengyuchenglun
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Section extends Node {

    /**
     * 请求方法.
     */
    Method method;
    /**
     * 请求url.
     */
    String uri;
    /**
     * 请求头.
     */
    Map<String, Header> inHeaders = new LinkedHashMap<>();
    /**
     * 路径变量.
     */
    ObjectNode pathVariable = ObjectMappers.instance.createObjectNode();
    /**
     * The Query parameter.
     */
    boolean queryParameter = true;

    /**
     * 查询参数+请求体参数
     */
    JsonNode parameters = ObjectMappers.instance.createObjectNode();
    /**
     * 查询参数
     */
    JsonNode queryParameters = ObjectMappers.instance.createObjectNode();
    /**
     * 请求体参数
     */
    JsonNode requestBodyParameters = ObjectMappers.instance.createObjectNode();

    /**
     * The Request rows.
     */
    Map<String, Row> requestRows = new LinkedHashMap<>();
    /**
     * The Out headers.
     */
    Map<String, Header> outHeaders = new LinkedHashMap<>();
    /**
     * The Response.
     */
    JsonNode response;
    /**
     * The Response rows.
     */
    Map<String, Row> responseRows = new LinkedHashMap<>();
    /**
     * The Raw response.
     */
    Object rawResponse;
    /**
     * 是否为统一结果返回
     */
    Boolean isResultData = false;

    /**
     * Sets parameter.
     *
     * @param parameter the parameter
     */
    public void setParameters(JsonNode parameter) {
        ((ObjectNode) this.parameters).setAll((ObjectNode) parameter);
    }

    /**
     * Sets query parameters.
     *
     * @param parameter the parameter
     */
    public void setQueryParameters(JsonNode parameter) {
        ((ObjectNode) this.queryParameters).setAll((ObjectNode) parameter);
    }

    /**
     * Sets request body parameters.
     *
     * @param parameter the parameter
     */
    public void setRequestBodyParameters(JsonNode parameter) {
        if (parameter.isArray()) {
            this.requestBodyParameters = ObjectMappers.instance.createArrayNode();
            ((ArrayNode) this.requestBodyParameters).add(parameter);
        } else {
            ((ObjectNode) this.requestBodyParameters).setAll((ObjectNode) parameter);

        }
    }

    /**
     * Sets uri.
     *
     * @param uri the uri
     */
    public void setUri(String uri) {
        String value = "";
        if (StringUtils.isNotBlank(uri)) {
            value = uri.replaceAll("\\{", ":").replaceAll("}", "");
        }
        this.uri = value;
    }

    /**
     * Add request row.
     *
     * @param row the row
     */
    public void addRequestRow(Row row) {
        requestRows.put(row.getKey(), row);
    }

    /**
     * Add request rows.
     *
     * @param rows the rows
     */
    public void addRequestRows(Collection<Row> rows) {
        for (Row row : rows) {
            if (row.getKey() != null && !requestRows.containsKey(row.getKey())) {
                requestRows.put(row.getKey(), row);
            }
        }
    }

    /**
     * Get request line string.
     *
     * @return the string
     */
    public String getRequestLine() {
        StringBuilder builder = new StringBuilder(this.method.toString());
        builder.append(" ").append(this.uri);
        if (Objects.equals("GET", this.method)) {
            String parameterString = getQueryParameterString();
            if (StringHelper.nonBlank(parameterString)) {
                builder.append("?").append(parameterString);
            }
        }
        builder.append(" HTTP/1.1");
        return builder.toString();
    }


    /**
     * Gets query parameter string.
     *
     * @return the query parameter string
     */
    public String getParameterString() {
        if (queryParameter) {
            // 查询参数
            new QueryStringBuilder().append((ObjectNode) queryParameters).toString();
        }
        // 请求体参数
        return ObjectMappers.pretty(parameters);
    }


    /**
     * Gets query parameter string.
     *
     * @return the query parameter string
     */
    public String getQueryParameterString() {
        String qs = new QueryStringBuilder().append((ObjectNode) queryParameters).toString();

        qs = StringUtils.replace(qs, "[\"\"]", "1,2,3");
        qs = StringUtils.replace(qs, "[0]", "1,2,3");
        qs = StringUtils.replace(qs, "[0.0]", "1.0,2.0,3.0");
        qs = StringUtils.replace(qs, "[false]", "false,true,false");

        return qs;
    }

    /**
     * Gets request body parameter string.
     *
     * @return the request body parameter string
     */
    public String getRequestBodyParameterString() {
        // 请求体参数
        return ObjectMappers.pretty(requestBodyParameters);
    }

    /**
     * Has request body boolean.
     *
     * @return the boolean
     */
    public boolean hasRequestBody() {
        if (Objects.equals("GET", this.method)) {
            return false;
        }
        return requestBodyParameters != null && requestBodyParameters.size() > 0;
    }

    /**
     * Add response row.
     *
     * @param row the row
     */
    public void addResponseRow(Row row) {
        responseRows.put(row.getKey(), row);
    }

    /**
     * Add response rows.
     *
     * @param rows the rows
     */
    public void addResponseRows(Collection<Row> rows) {
        for (Row row : rows) {
            if (row.getKey() != null && !responseRows.containsKey(row.getKey())) {
                if (row.getIsData()) {
                    Optional.ofNullable(this.tags.get(TAG_JAVA_DOC_RETURN)).ifPresent(x -> row.setRemark(x.content));
                }
                responseRows.put(row.getKey(), row);
            }
        }
    }


    /**
     * Has response body boolean.
     *
     * @return the boolean
     */
    public boolean hasResponseBody() {
        return response != null || rawResponse != null;
    }

    /**
     * Get response string string.
     *
     * @return the string
     */
    public String getResponseString() {
        if (response != null) {
            return ObjectMappers.pretty(response);
        }
        return String.valueOf(rawResponse);
    }

    /**
     * Add in header.
     *
     * @param header the header
     */
    public void addInHeader(Header header) {
        if (!inHeaders.containsKey(header.getKey())) {
            inHeaders.put(header.getKey(), header);
        }
    }

    /**
     * Add out header.
     *
     * @param header the header
     */
    public void addOutHeader(Header header) {
        if (!outHeaders.containsKey(header.getKey())) {
            outHeaders.put(header.getKey(), header);
        }
    }

}
