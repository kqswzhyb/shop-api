package com.example.xb.service.impl;

import com.example.xb.domain.User;
import com.example.xb.mapper.UserMapper;
import com.example.xb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapeer;

    /**
     * 根据条件分页查询用户数据
     *
     * @param user 用户信息
     * @return 用户数据集合信息
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapeer.selectUserList(user);
    }
}
