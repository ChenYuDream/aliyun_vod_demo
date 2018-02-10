package org.jypj.dev.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对象存储工具类
 *
 * @author yu_chen
 * @create 2018-01-31 14:14
 **/
public class OssUtil {

    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAIpoHj04Q5pZ4W";
    private static String accessKeySecret = "Zp1DWewlePo7TZB5TC2nwe8QQqFVJf";
    private static String bucketName = "vod-users-done";


    private static OSS oss = getInstance();


    public static void main(String[] args) {
        putObject(new File("C:\\Users\\ChenYu\\Desktop\\test.mp4"));
    }

    /**
     * 获取OSS实例
     *
     * @return
     */
    public static OSS getInstance() {

        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }


    /**
     * 上传文件
     *
     * @param file
     */
    public static String putObject(MultipartFile file) {
        String filePath = getFilePath(file.getOriginalFilename());
        try {
            oss.putObject(new PutObjectRequest(bucketName, filePath, file.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oss.shutdown();
        }

        return filePath;

    }

    /**
     * 上传文件
     *
     * @param file
     */
    public static String putObject(File file) {
        String filePath = getFilePath(file.getName());
        try {
            oss.putObject(new PutObjectRequest(bucketName, filePath, new FileInputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oss.shutdown();
        }
        return filePath;
    }

    /**
     * 根据时间计算文件的路径
     *
     * @return
     */
    public static String getFilePath(String fileName) {
        int i = fileName.lastIndexOf(".");
        String ext = fileName.substring(i);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = simpleDateFormat.format(new Date());
        String substring = format.substring(0, 6);
        return substring + "/" + format + ext;
    }

    /**
     * 关闭oss
     */
    public static void shutDown() {
        oss.shutdown();
    }
}
