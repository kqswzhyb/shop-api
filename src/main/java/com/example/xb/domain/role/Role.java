package com.example.xb.domain.role;

import com.example.xb.domain.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Administrator
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Role  extends Base {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名称
     */
    private  String roleName;

    /**
     * 状态（0 正常  1 停用)
     */
    private  String status;
}
