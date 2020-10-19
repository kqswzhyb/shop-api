package com.example.xb.domain;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class User extends Base  {
    /**
     * 用户Id
     */
    private  String userId;

    /**
     * 角色Id
     */
    private  String roleId;

    /**
     * 用户名
     */
    private  String userName;

    /**
     * 昵称
     */
    private  String nickName;

    /**
     * 密码
     */
    private  String password;

    /**
     * 状态（0 正常  1 停用)
     */
    private  String status;
}
