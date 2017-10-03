package www.lesson.common.utils;


import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    //-------------------------根据上传excel并读取数据-----------------------------------------------
    //上传方法
    public static List<List<Object>> upExcel(InputStream in, String filename){
        Workbook wb = praiseExcel(in, filename);
        List<List<Object>> list2 = new ArrayList<List<Object>>();

        for(int i=0;i<wb.getNumberOfSheets();i++){
            Sheet sheet = wb.getSheetAt(i);

            //getPhysicalNumberOfRows()获取的是物理行数，也就是不包括那些空行（隔行）的情况。
            //getLastRowNum()获取的是最后一行的行数（编号从0开始）所以比它的编号少1
            for(int j=sheet.getFirstRowNum();j<=sheet.getLastRowNum();j++){
                Row row = sheet.getRow(j);
                //row为空并跳过第一行
                if(row==null||j==sheet.getFirstRowNum()){continue;}


                List<Object> list1 = new ArrayList<Object>();
                for(int k=row.getFirstCellNum();k<row.getLastCellNum();k++){
                    Cell cell = row.getCell(k);
                    Object o = getCellValue(cell);
                    list1.add(o);
                }

                list2.add(list1);
            }
        }

        return list2;

    }

    //解析文件
    public static Workbook praiseExcel(InputStream in,String filename){
        Workbook wb = null;

        String fileType = filename.substring(filename.lastIndexOf("."));


        try {

            if(".xls".equals(fileType)){
                wb = new HSSFWorkbook(in);
            }
            if(".xlsx".equals(fileType)){
                wb = new XSSFWorkbook(in);
            }
            else {

                throw new Exception("解析的文件格式有误！");
            }


        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return wb;

    }


    //格式化数据
    public static Object getCellValue(Cell cell){

        Object value=null;




        switch(cell.getCellType()){

            case Cell.CELL_TYPE_STRING:
                value=cell.getRichStringCellValue().getString(); break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = new SimpleDateFormat("yyyy/MM/dd").format(cell.getDateCellValue());
                } else {
                    value = cell.getNumericCellValue();
                    DecimalFormat df = new DecimalFormat("0");
                    value = df.format(value);
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();    break;
            case Cell.CELL_TYPE_BLANK:
                value = "";    break;
            default:    break;

        }


        return value;
    }

}
