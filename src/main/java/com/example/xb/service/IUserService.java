package com.example.xb.service;

import com.example.xb.domain.User;
import com.example.xb.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;

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
}
