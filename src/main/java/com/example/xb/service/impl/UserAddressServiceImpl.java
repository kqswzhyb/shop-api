package com.example.xb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xb.domain.user.UserAddress;
import com.example.xb.mapper.UserAddressMapper;
import com.example.xb.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper,UserAddress> implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> addressList(String userId) {
        return userAddressMapper.addressList(userId);
    }

    @Override
    public int saveAddress(UserAddress userAddress) {
        return userAddressMapper.saveAddress(userAddress);
    }

    @Override
    public int updateAddress(UserAddress userAddress) {
        return userAddressMapper.updateAddress(userAddress);
    }

    @Override
    public int deleteByAddress(String addressId) {
        return userAddressMapper.deleteByAddress(addressId);
    }

    @Override
    public int deleteByUser(String userId) {
        return userAddressMapper.deleteByUser(userId);
    }
}
