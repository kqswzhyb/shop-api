package com.example.xb.service.impl;

import com.example.xb.domain.role.Menu;
import com.example.xb.domain.vo.MenuVo;
import com.example.xb.mapper.MenuMapper;
import com.example.xb.service.MenuService;
import com.example.xb.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVo> queryMenuList() {
        List<MenuVo>list = menuMapper.queryMenuList();
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
    public List<MenuVo> queryMenuAllList() {
        List<MenuVo>list = menuMapper.queryMenuList();
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
        return new MenuUtil().buildAllTree(treeList,list);
    }

    @Override
    public int saveMenu(Menu menu) {
        return menuMapper.saveMenu(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenuById(String menuId) {
        return menuMapper.deleteMenuById(menuId);
    }
}
