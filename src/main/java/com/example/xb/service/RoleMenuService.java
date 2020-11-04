package com.example.xb.service;

import com.example.xb.domain.RoleMenu;
import com.example.xb.domain.vo.MenuVo;

import java.util.List;

public interface RoleMenuService {

    /**
     * 根据roleId获取权限列表
     * @param roleId
     * @return
     */
    List<MenuVo> queryRoleMenuList(String roleId);

    /**
     * 根据roleId获取按钮权限列表
     * @param roleId
     * @return
     */
    List<String> queryPermissionList(String roleId);

    /**
     * 批量添加角色权限关系数据
     * @param list
     * @return
     */
    int batchSave(List<RoleMenu> list);

    /**
     * 根据roleId删除全部menuId
     * @param roleId
     * @return
     */
    int deleteRoleById(String roleId);
}
