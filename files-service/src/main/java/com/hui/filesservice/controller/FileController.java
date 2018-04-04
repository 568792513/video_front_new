package com.hui.filesservice.controller;

import com.hui.filesservice.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/File")
public class FileController {

    // 存放头像的目录
    @Value("${FTP_HEADIMGPATH}")
    private String FTP_HEADIMGPATH;

    // 存放视频封面的目录
    @Value("${FTP_VIDEOIMGPATH}")
    private String FTP_VIDEOIMGPATH;

    // 存放视频的目录
    @Value("${FTP_VIDEOFILEPATH}")
    private String FTP_VIDEOFILEPATH;

    @Resource
    private FileService fileService;

    /**
     * 上传头像
     * @param multipartFile
     * @param newName
     * @return
     */
    @PostMapping(value = "/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("newName") String newName){
        return fileService.uploadFile(multipartFile, newName, FTP_HEADIMGPATH);
    }

    /**
     * 上传视频封面
     * @param multipartFile
     * @param newName
     * @return
     */
    @PostMapping(value = "/uploadFile",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean uploadFile(@RequestParam("imgFile") MultipartFile multipartFile, @RequestParam("ImgFileName") String newName){
        return fileService.uploadFile(multipartFile, newName, FTP_VIDEOIMGPATH);
    }

    /**
     * 上传视频
     * @param multipartFile
     * @param newName
     * @return
     */
    @PostMapping(value = "/uploadVideoFile",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean uploadVideoFile(@RequestParam("videoFile") MultipartFile multipartFile, @RequestParam("videoFileName") String newName){
        return fileService.uploadFile(multipartFile, newName, FTP_VIDEOFILEPATH);
    }

    /**
     * 删除视频文件
     * @param fileName
     * @return
     */
    @PostMapping(value = "/removeVideoFile")
    public Boolean removeVideoFile(@RequestParam("fileName") String fileName){
        return fileService.removeFile(fileName, FTP_VIDEOFILEPATH);
    }

    /**
     * 删除视频封面
     * @param fileName
     * @return
     */
    @PostMapping(value = "/removeVideoImg")
    public Boolean removeVideoImg(@RequestParam("fileName") String fileName){
        return fileService.removeFile(fileName, FTP_VIDEOIMGPATH);
    }

    /**
     * 删除头像
     * @param fileName
     * @return
     */
    @PostMapping(value = "/removeHeadImg")
    public Boolean removeHeadImg(@RequestParam("fileName") String fileName){
        return fileService.removeFile(fileName, FTP_HEADIMGPATH);
    }
}
