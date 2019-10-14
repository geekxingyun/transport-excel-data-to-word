package com.xingyun.transportexceldatatoword.controller;

import com.xingyun.transportexceldatatoword.constant.CommonConstant;
import com.xingyun.transportexceldatatoword.customize.SmartUploadProperties;
import com.xingyun.transportexceldatatoword.util.SmartPoiExcelUtils;
import com.xingyun.transportexceldatatoword.util.SmartPoiWordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 星云
 * @功能
 * @date 10/13/2019 9:43 AM
 */
@Slf4j
@Controller
public class UploadApiController {
    @Autowired
    SmartUploadProperties smartUploadProperties;

    @PostMapping(value = "/upload.do")
    public String upload(@RequestParam(value = "uploadFileName") MultipartFile multipartFile, HttpServletResponse response){

        log.info(multipartFile.getOriginalFilename());

        //构建保存文件路径
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(smartUploadProperties.getUploadFolder());
        stringBuilder.append(File.separator);
        stringBuilder.append(multipartFile.getOriginalFilename());

        //上传文件路径
        String uploadFilePath=stringBuilder.toString();

        //文件
        File file=new File(uploadFilePath);
        try {
            //将上传的文件保存下来
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }

        //将Excel中的数据进行解析成对象
        List<String> dataList= SmartPoiExcelUtils.parseExcelData(file.getAbsolutePath());

        //生成World 的文件路径
        StringBuilder worldName=new StringBuilder();
        worldName.append(smartUploadProperties.getUploadFolder());
        worldName.append(File.separator);
        worldName.append("data.docx");

        //将数据写入到文档中
        try {
            SmartPoiWordUtils.writeDataToWord(worldName.toString(),dataList);
        } catch (IOException e) {
            log.error("IO Exception:",e);
        }

        //写入完成后放入这个列表中
        CommonConstant.shareFileMap.put("downloadFile",worldName.toString());

        return "redirect:/api/v1/download.do";
    }
}
