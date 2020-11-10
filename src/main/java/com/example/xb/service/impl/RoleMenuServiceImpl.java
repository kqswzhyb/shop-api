package com.example.xb.service.impl;

import com.example.xb.domain.role.RoleMenu;
import com.example.xb.domain.vo.MenuVo;
import com.example.xb.mapper.RoleMenuMapper;
import com.example.xb.service.RoleMenuService;
import com.example.xb.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuVo> queryRoleMenuList(String roleId) {
        List<MenuVo> list= roleMenuMapper.queryRoleMenuList(roleId);
        List<MenuVo> treeList = list.stream()
                .filter(v -> "0".equals(v.getParentId()))
                .map(menu -> {
                    MenuVo node = new MenuVo();
                    node.setChildren(new ArrayList<>());
                    node.setMenuId(menu.getMenuId());
                    node.setName(menu.getName());
                    node.setPermission(menu.getPermission());
                    node.setSort(menu.getSort());
                    node.setRemark(menu.getRemark());
                    node.setType(menu.getType());
                    node.setParentId(menu.getParentId());
                    if ("0".equals(menu.getType())) {
                        node.setComponent(menu.getComponent());
                        node.setIcon(menu.getIcon());
                        node.setKeepAlive(menu.getKeepAlive());
                        node.setPath(menu.getPath());
                    }
                    return node;
                })
                .collect(Collectors.toList());
        return new MenuUtil().buildTree(treeList,list);
    }

    @Override
    public List<String> queryPermissionList(String roleId) {
        List<MenuVo> list= roleMenuMapper.queryRoleMenuList(roleId).stream().filter(v->"1".equals(v.getType())).collect(Collectors.toList());
        List<String> permission= new ArrayList<>();
        for(MenuVo child:list) {
            permission.add(child.getPermission());
        }
        return permission;
    }

    @Override
    public int batchSave(List<RoleMenu> list) {
        return roleMenuMapper.batchSave(list);
    }

    @Override
    public int deleteRoleById(String roleId) {
        return roleMenuMapper.deleteRoleById(roleId);
    }
}
