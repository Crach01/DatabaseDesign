package com.company.extendFunction;

import com.company.util.JDBCUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class exportExcel {

    Connection connection = JDBCUtil.getConn();
    String sql = "select * from person;";
    Statement statement = null;
    ResultSet rs = null;


    public exportExcel(){
        XSSFWorkbook wb = null;
        try{
            wb = new XSSFWorkbook();//创建对象

        }catch (Exception e){
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.createSheet();

        //XSSFRow row1 = sheet.createRow(0);
        //XSSFRow row2 = sheet.createRow(1);
        //XSSFRow row3 = sheet.createRow(2);

        //XSSFCell cell1 = row1.createCell(0);
        //cell1.setCellValue("姓名");
        //XSSFCell cell2 = row1.createCell(1);
        //cell2.setCellValue("年龄");
        XSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("id");
        row1.createCell(1).setCellValue("passwd");
        row1.createCell(2).setCellValue("authority");
        row1.createCell(3).setCellValue("name");
        row1.createCell(4).setCellValue("sex");
        row1.createCell(5).setCellValue("birthday");
        row1.createCell(6).setCellValue("department");
        row1.createCell(7).setCellValue("job");
        row1.createCell(8).setCellValue("edu_level");
        row1.createCell(9).setCellValue("spcialty");
        row1.createCell(10).setCellValue("address");
        row1.createCell(11).setCellValue("tel");
        row1.createCell(12).setCellValue("email");
        row1.createCell(13).setCellValue("state");
        row1.createCell(14).setCellValue("remark");
        row1.createCell(15).setCellValue("isdeleted");

        int i = 1;

        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()){

                XSSFRow row = sheet.createRow(i++);
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("passwd"));
                row.createCell(2).setCellValue(rs.getString("authority"));
                row.createCell(3).setCellValue(rs.getString("name"));
                row.createCell(4).setCellValue(rs.getString("sex"));
                row.createCell(5).setCellValue(rs.getString("birthday"));
                row.createCell(6).setCellValue(rs.getString("department"));
                row.createCell(7).setCellValue(rs.getString("job"));
                row.createCell(8).setCellValue(rs.getString("edu_level"));
                row.createCell(9).setCellValue(rs.getString("spcialty"));
                row.createCell(10).setCellValue(rs.getString("address"));
                row.createCell(11).setCellValue(rs.getString("tel"));
                row.createCell(12).setCellValue(rs.getString("email"));
                row.createCell(13).setCellValue(rs.getString("state"));
                row.createCell(14).setCellValue(rs.getString("remark"));
                row.createCell(15).setCellValue(rs.getString("isdeleted"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //cell1 = row2.createCell(0);
        //cell1.setCellValue("张三");
        //cell2 = row2.createCell(1);
        //cell2.setCellValue("20");

        try{
            FileOutputStream outputStream = new FileOutputStream("test2.xlsx");
            wb.write(outputStream);
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Success");
        //System.out.println(row1.getCell(0) + "  " + row1.getCell(1));
        //System.out.println(row2.getCell(0) + "  " + row2.getCell(1));
    }

    public static void main(String[] args) {
        new exportExcel();
    }

}
