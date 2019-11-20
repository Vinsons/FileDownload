package com.example.image.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FDownloadController
 * @Description
 * @Author 黄文聪
 * @Date 2019-11-20 17:13
 * @Version 1.0
 **/
@RestController
@Slf4j
public class FDownloadController {

    @GetMapping(value = "/download")
    public String download(String url,String localPath){
        return "?";
    }

}
