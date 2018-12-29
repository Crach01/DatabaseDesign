package com.company.extendFunction;

import com.company.Dao.impl.UserDaoImpl;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;

public class selectFile {

    public selectFile(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setSelectedFile(new File("C:\\Users\\18451\\Desktop\\databaseDesign\\test.xlsx"));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.showDialog(new JLabel(),"选择" );
        File file = jFileChooser.getSelectedFile();
//        if(file.isDirectory()){
//            //System.out.println("文件夹" + "  " + file.getAbsolutePath());
//        }else if(file.isFile()){
//            //System.out.println("文件" + "  " + file.getAbsolutePath());
//        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份选择导入文件"+file.getAbsolutePath(), df.format(System.currentTimeMillis()));

        new excelImport(file.getAbsolutePath());
        //System.out.println("success");
    }

    public static void main(String[] args) {
        new selectFile();
    }
}
