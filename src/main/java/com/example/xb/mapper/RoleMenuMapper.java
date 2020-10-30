package com.example.xb.mapper;

import com.example.xb.domain.RoleMenu;
import com.example.xb.domain.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuMapper {
    /**
     * 根据roleId获取权限列表
     * @param roleId
     * @return
     */
    List<MenuVo> queryRoleMenuList(String roleId);

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
