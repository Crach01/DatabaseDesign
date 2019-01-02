package com.company.extendFunction;

import com.company.util.JDBCUtil;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.lang.module.ResolutionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class log extends JFrame {

    Vector studentVector = null;
    Vector titleVector = null;

    public log() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        studentVector = new Vector();
        titleVector = new Vector();
        JTable studentJTable = null;


        // 定义表头
        titleVector.add("记录号");
        titleVector.add("操作日志");
        titleVector.add("创建时间");

        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        studentJTable = new JTable(studentVector, titleVector);
        titleVector.setSize(50);
        studentJTable.setPreferredScrollableViewportSize(new Dimension(450, 160));
        JScrollPane studentJScrollPane = new JScrollPane(studentJTable);
        //分别设置水平和垂直滚动条自动出现
        studentJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        Connection connection = JDBCUtil.getConn();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from log");
            studentVector.clear();
            while(resultSet.next()){
                Vector vector = new Vector();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("log"));
                vector.add(resultSet.getString("time"));
                studentVector.add(vector);
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        this.setSize(800,600);
        this.add(studentJScrollPane);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new log();
    }
}
