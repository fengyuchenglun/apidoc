package com.github.fengyuchenglun.apidoc.springmvc.example.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单.
 */
@Getter
@Setter
public class Menu {
    /**
     * 菜单编号.
     */
    int id;
    /**
     * 菜单名称.
     */
    String name;
    /**
     * 父菜单编号
     */
    int pId;
    /**
     * 子节点
     */
    List<Menu> children;

}
