package com.example.xb.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Administrator
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends Base  {
    /**
     * 用户Id
     */
    @ColumnWidth(15)
    @ExcelProperty("用户Id")
    private  String userId;

    /**
     * 角色Id
     */
    @ExcelIgnore
    private  String roleId;

    /**
     * 用户名
     */
    @ColumnWidth(15)
    @ExcelProperty("用户名")
    private  String userName;

    /**
     * 昵称
     */
    @ColumnWidth(15)
    @ExcelProperty("昵称")
    private  String nickName;

    /**
     * 密码
     */
    @ExcelIgnore
    private  String password;

    /**
     * 状态（0 正常  1 停用)
     */
    @ExcelProperty("状态")
    private  String status;

    /**
     * 手机号码
     */
    @ColumnWidth(15)
    @ExcelProperty("手机号码")
    private  String phone;
}
