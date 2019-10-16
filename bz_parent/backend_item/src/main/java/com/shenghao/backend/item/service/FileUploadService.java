package com.shenghao.backend.item.service;

import com.shenghao.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    Result fileUpload(MultipartFile file);
}
