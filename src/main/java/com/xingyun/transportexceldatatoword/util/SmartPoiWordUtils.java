package com.xingyun.transportexceldatatoword.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author 星云
 * @功能
 * @date 10/13/2019 12:22 PM
 */
public final class SmartPoiWordUtils {

    public static void writeDataToWord(String fileName, List<String> dataListArg) throws IOException {
        FileOutputStream out = new FileOutputStream(new File(fileName));
        //创建一个文档
        XWPFDocument xwpfDocument=new XWPFDocument();
        //创建一个段落
        XWPFParagraph xwpfParagraph;
        //创建一片区域
        XWPFRun run;
        for (String lineData:dataListArg
             ) {
            xwpfParagraph= xwpfDocument.createParagraph();
            run=xwpfParagraph.createRun();
            run.setText(lineData);
        }
        xwpfDocument.write(out);
        xwpfDocument.close();
        out.close();
    }
}

