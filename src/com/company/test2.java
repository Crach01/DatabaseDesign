package com.company;

import javax.swing.*;
import java.io.File;

public class test2 {
    public test2(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setSelectedFile(new File("C:\\Users\\18451\\Desktop\\databaseDesign\\test.xlsx"));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.showDialog(new JLabel(),"选择" );
        File file = jFileChooser.getSelectedFile();
        if(file.isDirectory()){
            System.out.println("文件夹" + "  " + file.getAbsolutePath());
        }else if(file.isFile()){
            System.out.println("文件" + "  " + file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        new test2();
    }
}
