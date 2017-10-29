package www.lesson.common.utils;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    //-------------------------根据数据生成Excel(动态反射)-----------------------------------------------

    public static void downExcel(Map<String,List> forExcel, File file){

        HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel工作簿对象
        try{

            int w=0;

            for(String key:forExcel.keySet()){
                HSSFSheet sheet = workbook.createSheet();//在工作簿中创建工作表对象
                workbook.setSheetName(w++, "publicLesson"+key);//设置工作表的名称
                List toExcel = forExcel.get(key);//获得单个课程数据

                HSSFRow row = null;//行
                HSSFCell cell = null;//列


                Field[] fileds = toExcel.get(0).getClass().getDeclaredFields();  //获得属性
                List<String> methodNames = new ArrayList<String>(); //属性方法名



                row = sheet.createRow(0);//创建第1行
                //获得属性名,生成方法名和属性类,生成文件头
                for (int i = 0; i < fileds.length; i++) {
                    Field field = fileds[i];
                    //field.setAccessible(true);  //可以操作private
                    String fieldName = field.getName();

                    cell = row.createCell(i);//新增一列
                    cell.setCellValue(fieldName); //向单元格中写入数据

                    String methodName ="get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    methodNames.add(methodName);//生成属性方法名

                }


                //生成属性
                for (int i = 0; i < toExcel.size(); i++) {
                    Object values = toExcel.get(i);   //获得转入信息

                    row = sheet.createRow(i+1);//新增一行

                    for (int j = 0; j < methodNames.size(); j++) {

                        Method getMethod = toExcel.get(i).getClass().getMethod(methodNames.get(j));
                        Object value = getMethod.invoke(values); //获得值

                        cell = row.createCell(j);
                        cell.setCellValue(value.toString());    //设置值
                    }
                }

            }


            FileOutputStream fos = new FileOutputStream(file);//创建一个向指定 File 对象表示的文件中写入数据的文件输出流。
            workbook.write(fos);//将文档对象写入文件输出流
            fos.close();//关闭流

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("解析错误");
        }


    }

}
