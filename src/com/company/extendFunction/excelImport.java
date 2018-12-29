package com.company.extendFunction;

import com.company.Dao.impl.UserDaoImpl;
import com.company.JSwing.popUp;
import com.company.util.JDBCUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class excelImport {
    public excelImport(String path){
        XSSFWorkbook wb = null;
        try{
            wb = new XSSFWorkbook(new FileInputStream(path));//创建对象
        }catch (Exception e){
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheetAt(0);

        XSSFRow row1 = sheet.getRow(0);
        XSSFRow row2 = null;
        int rowNum = sheet.getLastRowNum();
        String sql = "insert into person values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = JDBCUtil.getConn();
        PreparedStatement psmt = null;
        try{
            psmt = connection.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean f = true;
        for(int j = 0;j < rowNum;j++) {
            row2 = sheet.getRow(j+1);
            for (int i = 0; i < 16; i++) {
                try {
                    String s = row2.getCell(i).getRichStringCellValue().getString();
                    if (i == 6) {
                        psmt.setString(i + 1, depN(s));
                    } else if (i == 7) {
                        psmt.setString(i + 1, jobN(s));
                    } else if (i == 8) {
                        psmt.setString(i + 1, eduQ(s));
                    } else {
                        psmt.setString(i + 1, s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                ResultSet rs = psmt.executeQuery();
                f = psmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!f) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份选择导入文件成功", df.format(System.currentTimeMillis()));
            new popUp("导入成功！");
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份选择导入文件失败", df.format(System.currentTimeMillis()));
            new popUp("导入失败，请重新导入！");
        }
        try{
            psmt.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String depN(String dep) {

        if (dep.equals("数据平台事业部")) {
            return "1";
        } else if (dep.equals("阿里云事业部")) {
            return "2";
        } else if(dep.equals("互动娱乐事业群")) {
            return "3";
        }else if(dep.equals("游戏事业部")) {
            return "4";
        }else if(dep.equals("财务事业部")) {
            return "5";
        }else if(dep.equals("法务事业部")) {
            return "6";
        }else if(dep.equals("天猫事业部")) {
            return "7";
        }else if(dep.equals("本地生活事业部")) {
            return "8";
        }else if(dep.equals("数字业务事业部")) {
            return "9";
        }else if(dep.equals("消费者业务事业部")) {
            return "10";
        }
        return "1";
    }
    public  String eduQ(String edu){

        if (edu.equals("小学")) {
            return "0";
        } else if (edu.equals("初中")) {
            return "1";
        }else if (edu.equals("高中")) {
            return  "2";
        }else if (edu.equals("大本")) {
            return "4";
        }else if (edu.equals("大专")) {
            return "5";
        }else if (edu.equals("硕士")) {
            return "6";
        }else if (edu.equals("博士")) {
            return "7";
        }else if (edu.equals("博士后")) {
            return "8";
        }else if (edu.equals("职高")) {
            return "3";
        }
        return "0";
    }

    public String jobN(String job){

        if("CEO".equals(job)){
            return "1";
        } else if("技术总监".equals(job)){
            return "2";
        }else if(job.equals("部门经理")){
            return "3";
        }else if(job.equals("职员")){
            return "4";
        }else if(job.equals("财务总监")){
            return "5";
        }else if(job.equals("轮值CEO")){
            return "6";
        }else if(job.equals("财务专员")){
            return "7";
        }else if(job.equals("法务专员")){
            return "8";
        }else if(job.equals("游戏策划")){
            return "9";
        }else if(job.equals("Java工程师")){
            return "10";
        }else if(job.equals("美工")){
            return "11";
        }else if(job.equals("CMO")){
            return "12";
        }

        return "0";
    }
}
