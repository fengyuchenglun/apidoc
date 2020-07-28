package com.github.fengyuchenglun.apidoc.core.common.postman;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Url{

    String raw;
    String protocol = "http";
    String host = "{{host}}";
    String path;
    String port;
    List<Parameter> query = new ArrayList<>();

}
