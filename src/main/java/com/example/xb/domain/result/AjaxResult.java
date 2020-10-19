package com.example.xb.domain.result;

import com.example.xb.enums.Code;
import lombok.Data;

import java.io.Serializable;

@Data
public class AjaxResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    private Object data;

    public AjaxResult(){
        this.code="0";
        this.message="操作成功";
        this.data=null;
    }

    public AjaxResult(ResultInfo resultInfo){
        this.code=resultInfo.getCode();
        this.message=resultInfo.getInfo();
        this.data=null;
    }

    public AjaxResult(ResultInfo resultInfo,Object data){
        this.code=resultInfo.getCode();
        this.message=resultInfo.getInfo();
        this.data=data;
    }
}
