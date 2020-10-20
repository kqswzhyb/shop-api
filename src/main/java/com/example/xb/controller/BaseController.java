package com.example.xb.controller;

import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.ResultInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.util.StringUtils;

public class BaseController {

    public ResultInfo startPage(DataDomain dd) {
        ResultInfo resultInfo =new ResultInfo();
        resultInfo.success(null);
        try {
            int pageNum = !StringUtils.isEmpty(dd.getCurrent()) ? Integer.parseInt(String.valueOf(dd.getCurrent())) : 1;
            int pageSize = !StringUtils.isEmpty(dd.getSize()) ? Integer.parseInt(String.valueOf(dd.getSize())) : 10;
            PageHelper.startPage(pageNum, pageSize);
        }catch (Exception e) {
            if(e.toString().contains("NumberFormatException")) {
                resultInfo.error("非法参数");
            }else {
                resultInfo.error(e.toString());
            }

        }
        return resultInfo;



    }
}
