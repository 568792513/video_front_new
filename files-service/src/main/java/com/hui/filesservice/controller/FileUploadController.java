package com.hui.filesservice.controller;

import com.hui.filesservice.service.FileService;
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
public class FileUploadController {

    @Resource
    private FileService fileService;

    @PostMapping(value = "/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("newName") String newName){
        return fileService.uploadFile(multipartFile, newName);
    }
}
