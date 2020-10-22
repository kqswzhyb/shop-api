package com.example.xb.service.impl;

import com.example.xb.domain.LoginBody;
import com.example.xb.domain.User;
import com.example.xb.domain.Password;
import com.example.xb.mapper.UserMapper;
import com.example.xb.service.IUserService;
import com.example.xb.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapeer;

    @Value("${token.password.secret}")
    private String secretKey;

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

    /**
     *
     * @param userName 用户信息
     * @return
     */
    @Override
    public List<User> queryByUserName(String userName)
    {
        return  userMapeer.queryByUserName(userName);


    }

    /**
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public int saveUser(User user) {
        return userMapeer.saveUser(user);
    }

    /**
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public int updateUser(User user) {
        return userMapeer.updateUser(user);
    }

    /**
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public  int deleteUserById(String userId) {
        return userMapeer.deleteUserById(userId);
    }

    /**
     *
     * @param loginBody 登录体
     * @return
     */
    @Override
    public  String userLogin(LoginBody loginBody) {
        return userMapeer.userLogin(loginBody);
    }

    @Override
    public int updatePassword(Password password) {
        String oldPassword2= userMapeer.queryPasswordById(password.getUserId());
        if(oldPassword2.equals(AESUtil.encryptIntoHexString(password.getOldPassword(), secretKey))) {
            password.setNewPassword(AESUtil.encryptIntoHexString(password.getNewPassword(), secretKey));
            return userMapeer.updatePassword(password);
        }
        return 0;
    }

    @Override
    public  String queryPasswordById(String userId) {
        return userMapeer.queryPasswordById(userId);
    }
}
