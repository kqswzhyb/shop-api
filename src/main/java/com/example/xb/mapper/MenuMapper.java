package com.example.xb.mapper;

import com.example.xb.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface MenuMapper {
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
