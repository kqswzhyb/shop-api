package com.example.xb.service.impl;

import com.example.xb.domain.Role;
import com.example.xb.mapper.RoleMapper;
import com.example.xb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 分页获取角色列表
     * @param role 角色信息
     * @return 角色列表
     */
    @Override
    public List<Role> roleList(Role role) {
        return roleMapper.roleList(role);
    }

    @Override
    public int saveRole(Role role) {
        return roleMapper.saveRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int deleteRoleById(String roleId) {
        return roleMapper.deleteRoleById(roleId);
    }
}
