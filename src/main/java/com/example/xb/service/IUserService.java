package com.example.xb.service;

import com.example.xb.domain.LoginBody;
import com.example.xb.domain.User;
import com.example.xb.domain.Password;
import com.example.xb.mapper.UserMapper;

import java.util.List;

/**
 * @author Administrator
 */
public interface IUserService extends UserMapper {
    /**
     * 根据条件分页查询用户数据
     *
     * @param user 用户信息
     * @return 用户数据集合信息
     */
    @Override
    List<User> selectUserList(User user);

    /**
     * 根据username查询
     *
     * @param userName 用户信息
     * @return 用户数据集合信息
     */
    @Override
    List<User> queryByUserName(String userName);

    /**
     * 创建新用户数据
     *
     * @param user 用户信息
     * @return 用户数据信息
     */
    @Override
    int saveUser(User user);

    /**
     * 更新新用户数据
     *
     * @param user 用户信息
     * @return 用户数据信息
     */
    @Override
    int updateUser(User user);


    /**
     * 通过用户ID删除角色
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    int deleteUserById(String userId);

    /**
     * 登录
     *
     * @param loginBody 登录体
     * @return 结果
     */
    @Override
    String userLogin(LoginBody loginBody);

    /**
     *  修改密码
     * @param password 密码实体
     * @return 结果
     */
    @Override
    int updatePassword(Password password);

    /**
     * 通过userId找密码
     * @param userId 用户ID
     * @return 密码
     */
    @Override
    String queryPasswordById(String userId);
}
