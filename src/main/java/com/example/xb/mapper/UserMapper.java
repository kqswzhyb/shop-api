package com.example.xb.mapper;

import com.example.xb.domain.LoginBody;
import com.example.xb.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface UserMapper {
    /**
     * 根据条件分页查询用户数据
     *
     * @param user 用户信息
     * @return 用户数据集合信息
     */
    List<User> selectUserList(User user);



    /**
     * 根据username查询
     *
     * @param userName 用户信息
     * @return 用户数据集合信息
     */
    List<User> queryByUserName(String userName);

    /**
     * 创建新用户数据
     *
     * @param user 用户信息
     * @return 用户数据信息
     */
    int saveUser(User user);

    /**
     * 更新新用户数据
     *
     * @param user 用户信息
     * @return 用户数据信息
     */
    int updateUser(User user);

    /**
     * 通过用户ID删除角色
     *
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserById(String userId);

    /**
     * 登录
     *
     * @param loginBody 用户信息
     * @return 用户数据信息
     */
    String userLogin(LoginBody loginBody);
}
