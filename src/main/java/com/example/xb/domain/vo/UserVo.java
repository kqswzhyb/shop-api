package com.example.xb.domain.vo;

import com.example.xb.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Administrator
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVo  extends User {

    /**
     * 角色名称
     */
    private  String roleName;
}
