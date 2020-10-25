package com.example.xb.domain.upload;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class CloudSetting {
    @Value("${cloudSetting.secretId}")
    private String secretId;
    @Value("${cloudSetting.secretKey}")
    private String secretKey;
    @Value("${cloudSetting.region}")
    private String region;
    @Value("${cloudSetting.bucketName}")
    private String bucketName;
}
