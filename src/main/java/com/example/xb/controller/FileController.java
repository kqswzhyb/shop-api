package com.example.xb.controller;

import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.utils.FileUploadUtil;
import com.example.xb.utils.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/file")
@Api(tags = "文件管理")
public class FileController {


    @Autowired
    private  FileUploadUtil fileUploadUtil;

    /**
     * 创建新用户
     *
     * @return
     */
    @PostMapping("/simpleUpload")
    @ApiOperation(value = "上传单文件", notes = "上传单文件")
    public AjaxResult simpleUpload(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "dic") String dic) {
        ResultInfo resultInfo = new ResultInfo();
        File localFile;
        Map<String,String> data;

        int index = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");

        if (index == -1) {
            return null;
        }
        String extName = file.getOriginalFilename().substring(index + 1);

        try {
            localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);

            String fileName= UUIDUtil.NewLowUUID().substring(0,9)+"-"+System.currentTimeMillis();

            data = fileUploadUtil.SimpleUploadFileFromStream(fileName, localFile,extName,dic);
            resultInfo.success("上传成功");
        }catch (Exception e){
            resultInfo.error("上传失败");
            throw new RuntimeException(e.getMessage());
        }

        return new AjaxResult(resultInfo, data);
    }
}
