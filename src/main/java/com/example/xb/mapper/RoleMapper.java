package com.example.xb.mapper;


import com.example.xb.domain.role.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface RoleMapper {
    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<Role> roleList(Role role);

    /**
     * 创建新角色数据
     *
     * @param role 角色信息
     * @return 角色数据信息
     */
    int saveRole(Role role);

    /**
     * 更新角色数据
     *
     * @param role 角色信息
     * @return 角色数据信息
     */
    int updateRole(Role role);

    /**
     * 通过roleID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int deleteRoleById(String roleId);

}
