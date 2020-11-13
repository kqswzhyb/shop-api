package com.example.xb.mapper;

import com.example.xb.domain.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserAddressMapper {
    /**
     * 根据userId查询所有地址
     *
     * @param userId
     * @return
     */
    List<UserAddress> addressList(String userId);

    /**
     * 创建新地址
     *
     * @param userAddress
     * @return
     */
    int saveAddress(UserAddress userAddress);

    /**
     * 更新地址
     *
     * @param
     * @return
     */
    int updateAddress(UserAddress userAddress);

    /**
     * 通过addressID删除地址
     *
     * @param
     * @return
     */
    int deleteByAddress(String addressId);


    /**
     * 通过userID删除地址
     *
     * @param
     * @return
     */
    int deleteByUser(String userId);
}
