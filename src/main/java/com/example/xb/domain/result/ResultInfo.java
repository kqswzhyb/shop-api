package com.example.xb.domain.result;

import com.example.xb.enums.Code;
import lombok.Data;

@Data
public class ResultInfo {

    private  String code;

    private  String info;

    public  ResultInfo() {
        this.code= Code.OK.getCode();
        this.info= Code.OK.getInfo();
    }


    public void success(String info) {
        this.code= Code.OK.getCode();
        this.info=info!=null?info:Code.OK.getInfo();
    }

    public void error(String info) {
        this.code= Code.ERROR.getCode();
        this.info=info!=null?info:Code.ERROR.getInfo();
    }
}
