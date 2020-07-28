package com.github.fengyuchenglun.apidoc.core.common.postman;

import com.github.fengyuchenglun.apidoc.core.schema.Header;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Response{

    String name;
    Request originalRequest ;
    List<Header> headers = new ArrayList<>();
    String body;
    String status;
    Integer code;
    String _postman_previewlanguage = "json";

}
