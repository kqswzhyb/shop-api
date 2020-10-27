package com.example.xb.service.impl;

import com.example.xb.domain.Menu;
import com.example.xb.domain.vo.MenuVo;
import com.example.xb.mapper.MenuMapper;
import com.example.xb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVo> queryMenuList() {
        return menuMapper.queryMenuList();
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
