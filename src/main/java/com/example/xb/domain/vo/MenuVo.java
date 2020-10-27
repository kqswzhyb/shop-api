package com.example.xb.domain.vo;

import com.example.xb.domain.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Administrator
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuVo  extends Menu {

    private List<MenuVo> children;
}
