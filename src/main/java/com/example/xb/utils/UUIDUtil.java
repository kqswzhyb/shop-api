package com.example.xb.utils;

import java.util.UUID;

/**
 * @author Administrator
 */
public class UUIDUtil {

    /**
     * 获取64为UUID
     * */
    public static String NewUUID(){
        UUID uuid=UUID.randomUUID();
        UUID uuid2=UUID.randomUUID();
        String str = uuid.toString();
        String str2 = uuid2.toString();
        String uuidStr=str.replace("-", "");
        String uuidStr2 = str2.replace("-","");

        return  uuidStr+uuidStr2;
    }
    /**
     * 获取32为UUID
     * */
    public static String NewLowUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return  uuidStr;
    }
}
