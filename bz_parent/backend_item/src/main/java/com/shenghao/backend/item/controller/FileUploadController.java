package com.shenghao.backend.item.controller;

import com.shenghao.backend.item.service.FileUploadService;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 图片上传方法
     */
    @RequestMapping("/upload")
    public Result fileUpload(MultipartFile file){
        try{
            return this.fileUploadService.fileUpload(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
