package com.example.xb.service;

import com.example.xb.domain.Menu;

import java.util.List;

/**
 * @author Administrator
 */
public interface MenuService {
    /**
     * 获取权限列表
     * @return 权限列表
     */

    List<Menu> queryMenuList();

    /**
     *创建权限
     * @param menu 菜单
     * @return 结果
     */
    int saveMenu(Menu menu);

    /**
     *更新权限
     * @param menu 菜单
     * @return 结果
     */
    int updateMenu(Menu menu);

    /**
     *删除
     * @param menuId 菜单id
     * @return 结果
     */
    int deleteMenuById(String menuId);
}
