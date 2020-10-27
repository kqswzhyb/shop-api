package com.example.xb.utils;

import com.example.xb.domain.vo.MenuVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class MenuUtil {

    public List<MenuVo> buildTree(List<MenuVo> menus, List<MenuVo> all) {
        for(MenuVo menu:menus) {
            List<MenuVo> list = all.stream().filter(v -> menu.getMenuId().equals(v.getParentId())).collect(Collectors.toList());
            if (list.size() != 0) {
                menu.setChildren(this.buildTree(list, all));
            }
        }
        return menus;
    }
}
