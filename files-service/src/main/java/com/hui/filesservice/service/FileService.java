package com.hui.filesservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    Boolean uploadFile(MultipartFile uploadFile, String newName, String basePath);
}
