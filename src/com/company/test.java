package com.company;


import com.company.util.JDBCUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class test {

    public static void main(String[] args) {
        XSSFWorkbook wb = null;
        try{
            wb = new XSSFWorkbook(new FileInputStream("test.xlsx"));//创建对象

        }catch (Exception e){
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheetAt(0);

        XSSFRow row1 = sheet.getRow(0);
        XSSFRow row2 = sheet.getRow(1);
        //XSSFRow row3 = sheet.getRow(2);

        //XSSFCell cell1 = row1.createCell(0);
        //cell1.setCellValue("姓名");
        //XSSFCell cell2 = row1.createCell(1);
        //cell2.setCellValue("年龄");
//
        //cell1 = row2.createCell(0);
        //cell1.setCellValue("张三");
        //cell2 = row2.createCell(1);
        //cell2.setCellValue("20");

//        try{
//            FileOutputStream outputStream = new FileOutputStream("test.xlsx");
//            wb.write(outputStream);
//            outputStream.flush();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        //System.out.println(row1.getCell(0) + "  " + row1.getCell(1));
        //System.out.println(row2.getCell(0) + "  " + row2.getCell(1));
        String sql = "insert into person values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = JDBCUtil.getConn();
        PreparedStatement psmt = null;
        try{
            psmt = connection.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i = 0;i<16;i++){
            try{

                //System.out.println(row2.getCell(i).getRichStringCellValue().getString()+"   ");
                String s = row2.getCell(i).getRichStringCellValue().getString();
                psmt.setString(i+1,s);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try{
            psmt.execute();
            psmt.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.showDialog(new JLabel(),"选择" );
        File file = jFileChooser.getSelectedFile();
        if(file.isDirectory()){
            System.out.println("文件夹" + "  " + file.getAbsolutePath());
        }else if(file.isFile()){
            System.out.println("文件" + "  " + file.getAbsolutePath());
        }
        JFrame f = new JFrame();

        //f.add(jFileChooser);
        //f.setSize(300,300);
        //f.setVisible(true);


        System.out.println("success");
    }
}




