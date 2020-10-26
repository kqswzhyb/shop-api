package com.example.xb.utils;


import com.example.xb.domain.upload.CloudSetting;
import com.example.xb.service.FileRecordService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileUploadUtil {

    @Autowired
    private CloudSetting cloudSetting;

    @Autowired
    private FileRecordService fileService;

    @Value("${cloudSetting.uri}")
    private String uri;

    public  Map<String,String> SimpleUploadFileFromStream(String key, File localFile, String extName,String dic) {
        Map<String,String> map = new HashMap<>();
        String path = StringUtils.isEmptyOrWhitespace(dic) ?"common":dic+"/"+key+"."+extName;
        String secretKey = cloudSetting.getSecretKey();
        String secretId = cloudSetting.getSecretId();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        String region = cloudSetting.getRegion();
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        COSClient cosClient = new COSClient(cred, clientConfig);

        String bucketName = cloudSetting.getBucketName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, localFile);
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            map.put("fileName",key);
            map.put("fileFullPath",uri+path);
            map.put("filePath",StringUtils.isEmptyOrWhitespace(dic) ?"common":dic+"/");
            map.put("fileExt",extName);
        } catch (CosClientException e) {
            e.printStackTrace();
        }

        // 关闭客户端
        cosClient.shutdown();
        return map;
    }
}
