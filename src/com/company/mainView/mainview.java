package com.company.mainView;


import com.company.Dao.impl.UserDaoImpl;
import com.company.JSwing.DatabaseCourseDesign;
import com.company.addPerson.addNewPerson;
import com.company.extendFunction.log;
import com.company.login.JSwingLogin;
import com.company.personnelChange.personnelChange;

import javax.print.attribute.standard.JobName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class mainview extends JFrame implements ActionListener{

    public mainview(){

        this.setTitle("选择操作");
        this.setSize(600,700);
        this.setLocationRelativeTo(null);

        this.setLayout(null);

        JButton btn1 = new JButton("新增员工信息");
        btn1.setBounds(190,60 ,200 ,60 );
        JButton btn2 = new JButton("人事变更信息");
        btn2.setBounds(190,160 ,200 ,60 );
        JButton btn5 = new JButton("个人、员工信息查询修改");
        btn5.setBounds(190,260 ,200 ,60 );
        JButton recover = new JButton("恢复员工记录");
        recover.setBounds(190,360 ,200 ,60 );
        JButton back = new JButton("返回登陆界面");
        back.setBounds(190,560 ,200 ,60 );
        JButton logButton = new JButton("查询操作日志");
        logButton.setBounds(190,460 ,200 ,60 );


        this.add(btn1);
        this.add(btn2);
        this.add(btn5);
        this.add(logButton);
        this.add(recover);
        this.add(back);
        back.addActionListener(this::actionPerformed);


        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份打开新增员工信息窗口", df.format(System.currentTimeMillis()));
                new addNewPerson();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份打开人事变更信息窗口", df.format(System.currentTimeMillis()));
                new personnelChange();
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份打开员工信息查询修改窗口", df.format(System.currentTimeMillis()));
                new DatabaseCourseDesign(1);
            }
        });
        recover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份打开恢复员工记录窗口", df.format(System.currentTimeMillis()));
                new recoverRecord();
            }
        });
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new log();
            }
        });

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setVisible(true);

    }

    public static void main(String[] args) {
        new mainview();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("返回登陆界面")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份返回登陆界面", df.format(System.currentTimeMillis()));
            this.dispose();
            new JSwingLogin();
        }
    }
}
