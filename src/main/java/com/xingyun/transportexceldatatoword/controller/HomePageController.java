package com.xingyun.transportexceldatatoword.controller;

import com.xingyun.transportexceldatatoword.customize.SmartUploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

/**
 * @author 星云
 * @功能
 * @date 10/13/2019 9:34 AM
 */
@Controller
public class HomePageController {

    @Autowired
    SmartUploadProperties smartUploadProperties;
    @GetMapping(value = "/")
    public String homePage(){
        File file=new File(smartUploadProperties.getUploadFolder());
        if(!file.exists()){
            file.mkdirs();
        }
        return "index";
    }
}
