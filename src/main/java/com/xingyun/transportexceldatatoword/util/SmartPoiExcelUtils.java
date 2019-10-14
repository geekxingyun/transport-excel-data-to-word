package com.xingyun.transportexceldatatoword.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 星云
 * @功能
 * @date 10/13/2019 10:01 AM
 */
@Slf4j
public final class SmartPoiExcelUtils {

    private static final DataFormatter DATA_FORMATTER = new DataFormatter();

    public static List<String> parseExcelData(String fileName){
        List<String> dataList=new ArrayList<>();
        try {
            Workbook workbook= SmartPoiExcelUtils.createExcelWithXLSX(fileName);
            for (Sheet sheetItem : workbook ) {
                for (Row row : sheetItem) {
                    for (Cell cell : row) {
                        String text = DATA_FORMATTER.formatCellValue(cell);
                        log.info(text);
                        log.debug("cell type:{}",cell.getCellType());
                        dataList.add(text);
                    }
                }
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }


    public static Workbook createExcelWithXLS(String fileName) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        try  (OutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }
        return workbook;
    }

    public static Workbook createExcelWithXLSX(String fileName) throws IOException {
        Workbook workbook = WorkbookFactory.create(new File(fileName));
        return workbook;
    }

    /**
     * Note that sheet name is Excel must not exceed 31 characters
     * and must not contain any of the any of the following characters:
     * 0x0000
     * 0x0003
     * colon (:)
     * backslash (\)
     * asterisk (*)
     * question mark (?)
     * forward slash (/)
     * opening square bracket ([)
     * closing square bracket (])
     * @param workbook
     * @param sheetName
     * @return
     */
    public static Sheet createSheet(Workbook workbook,String sheetName){
        // You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
        // for a safe way to create valid names, this utility replaces invalid characters with a space (' ')
        // returns " O'Brien's sales   "
        String safeName = WorkbookUtil.createSafeSheetName(sheetName);
        Sheet sheet = workbook.createSheet(safeName);
        return sheet;
    }

    public static Cell createCell(Workbook workbook,Sheet sheet){

        CreationHelper createHelper = workbook.getCreationHelper();
        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);

        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);
        // Create a cell and put a date value in it.  The first cell is not styled
        // as a date.
        row.createCell(4).setCellValue(new Date());

        // we style the second cell as a date (and time).  It is important to
        // create a new cell style from the workbook otherwise you can end up
        // modifying the built in style and effecting not only this cell but other cells.
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

        //you can also set date as java.util.Calendar
        cell = row.createCell(2);
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(cellStyle);

        row.createCell(0).setCellValue(1.1);
        row.createCell(1).setCellValue(new Date());
        row.createCell(2).setCellValue(Calendar.getInstance());
        row.createCell(3).setCellValue("a string");
        row.createCell(4).setCellValue(true);

        return cell;
    }
}
