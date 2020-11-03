package com.example.xb.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Administrator
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Menu  extends  Base{
    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 权限名称
     */
    private String permission;
    /**
     * 前端url
     */
    private  String path;
    /**
     * 父级id
     */
    private  String parentId;
    /**
     * 图标名称
     */
    private  String icon;
    /**
     * 页面文件路径
     */
    private  String component;
    /**
     * 排序
     */
    private  String sort;
    /**
     * 缓存
     */
    private  String keepAlive;
    /**
     * 类型（0菜单，1按钮)
     */
    private  String type;

    /**
     * 状态
     */
    private  String status;
}
