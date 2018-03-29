package com.hui.filesservice.service.impl;

import com.hui.filesservice.service.FileService;
import com.hui.filesservice.utils.FtpFileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {
    //获取ip地址
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    //端口号
    @Value("${FTP_PORT}")
    private String FTP_PORT;
    //用户名
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    //密码
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    //基本路径
    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;
    //下载地址地基础url
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public Boolean uploadFile(MultipartFile uploadFile, String newName) {
        try {
            // 生成一个文件名
            // 获取旧的名字
            String oldName = uploadFile.getOriginalFilename();
            //新名字
//            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //端口号
            int port = Integer.parseInt(FTP_PORT);
            System.out.println(FTP_BASEPATH);
            //调用方法，上传文件
            boolean result = FtpFileUtil.uploadFile(FTP_ADDRESS, port,
                    FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH,
                    newName, uploadFile.getInputStream());

            return result;

        } catch (IOException e) {
            return false;
        }
    }
}
