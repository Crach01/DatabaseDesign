package com.company.mainView;

import com.company.Dao.impl.UserDaoImpl;
import com.company.JSwing.DatabaseCourseDesign;
import com.company.login.JSwingLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class mainViewStaff extends JFrame implements ActionListener {

    public mainViewStaff(){

        this.setTitle("员工登陆");
        this.setLayout(null);

        JButton jBChange = new JButton("修改密码");
        JButton jBQuery = new JButton("查询自己信息");
        JButton jBBack = new JButton("返回登陆界面");


        jBChange.setBounds(150,100 ,200 ,50 );
        jBChange.addActionListener(this);
        jBQuery.setBounds(150,200 ,200 ,50 );
        jBQuery.addActionListener(this);
        jBBack.setBounds(150,300 ,200 ,50 );
        jBBack.addActionListener(this);

        this.add(jBChange);
        this.add(jBQuery);
        this.add(jBBack);

        this.setSize(500,500);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("修改密码")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击修改密码", df.format(System.currentTimeMillis()));
            new passwordChange();
        }else if(e.getActionCommand().equals("查询自己信息")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击查询自己", df.format(System.currentTimeMillis()));
            new DatabaseCourseDesign(2);
        }else if(e.getActionCommand().equals("返回登陆界面")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击返回登陆界面", df.format(System.currentTimeMillis()));
            this.dispose();
            new JSwingLogin();
        }
    }

    public static void main(String[] args) {
        new mainViewStaff();
    }
}
