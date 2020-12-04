package com.example.xb.utils;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

@Component
public class ImgUtil {
    public int getPoint(String mode) {
        Random r = new Random();
        int max=0;
        int min=0;
        if(mode.equals("x")) {
            max=260;
            min=100;
        }else {
            max=160;
            min=100;
        }
        return r.nextInt(max - min + 1) + min;
    }
    public String getBase64 (BufferedImage img) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] bytes = outputStream.toByteArray();
        String png_base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
//        System.out.println("data:image/jpg;base64," + png_base64);
        return "data:image/jpg;base64," + png_base64;
    }
}
