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
    List<User> selectUserList(User user);
}
