package com.example.xb.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
public class Password {

    private  String userId;

    private  String oldPassword;

    private  String newPassword;
}
