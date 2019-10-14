package com.xingyun.transportexceldatatoword.controller.api;

import com.xingyun.transportexceldatatoword.constant.CommonConstant;
import com.xingyun.transportexceldatatoword.model.AppResponse;
import com.xingyun.transportexceldatatoword.util.DownloadFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author 星云
 * @功能
 * @date 10/13/2019 2:56 PM
 */
@Slf4j
@RequestMapping(value = "/api/v1")
@RestController
public class DownloadController {

    @GetMapping(value = "/download.do")
    public  void downloadFtpFileList(HttpServletResponse response) throws Exception{

        String downloadFileName="download.docx";
        //获取下载文件路径
        String downloadFilePath= CommonConstant.shareFileMap.get("downloadFile");

        log.info("下载文件名称:"+downloadFileName);
        log.info("下载文件路径:"+downloadFilePath);

        //执行下载文件
        Boolean downloadResult= DownloadFileUtils.downloadFile(downloadFilePath,downloadFileName,response);
        if(downloadResult){
            log.info("下载成功");
        }else{
           log.info("下载失败");
        }
    }
}
