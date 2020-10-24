package com.example.xb.service;

import com.example.xb.domain.Role;
import com.example.xb.domain.User;
import com.example.xb.mapper.RoleMapper;

import java.util.List;

/**
 * @author Administrator
 */
public interface RoleService  extends RoleMapper {
    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    List<Role> roleList(Role role);

    /**
     * 创建新角色数据
     *
     * @param role 角色信息
     * @return 角色数据信息
     */
    @Override
    int saveRole(Role role);

    /**
     * 更新角色数据
     *
     * @param role 角色信息
     * @return 角色数据信息
     */
    @Override
    int updateRole(Role role);

    /**
     * 通过roleID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    int deleteRoleById(String roleId);
}
