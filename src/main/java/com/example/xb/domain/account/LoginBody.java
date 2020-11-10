package com.example.xb.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
public class LoginBody {

    private String userName;

    private String password;
}
