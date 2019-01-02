package com.company.login;

import com.company.Dao.UserDao;
import com.company.Dao.impl.UserDaoImpl;
import com.company.JSwing.popUp;
import com.company.mainView.mainViewStaff;
import com.company.mainView.mainview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;


public class JSwingLogin  implements ActionListener{
    ButtonGroup group = null;
    /**
     * flag : 1表示管理员，2表示员工
     */
    public int flag = -1;
    JPanel jPanel = null;
    public String passwordContent = null;
    public String userNameContent = null;
    public  JSwingLogin(){
        JFrame f = new JFrame();
        f.setTitle("登陆界面");
        f.setSize(600,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setLocationRelativeTo(null);

        JLabel welcome = new JLabel("欢迎使用人事管理系统");
        welcome.setFont(new Font("宋体", Font.PLAIN, 50));
        JTextField userName = new JTextField();
        JTextField password = new JPasswordField();

        group = new ButtonGroup();
        jPanel = new JPanel();
        JRadioButton radioButton1 = new JRadioButton("管理员");
        JRadioButton radioButton2 = new JRadioButton("员工");
        group.add(radioButton1);
        group.add(radioButton2);
        jPanel.add(radioButton1);
        jPanel.add(radioButton2);
        radioButton1.addActionListener(this);
        radioButton2.addActionListener(this);

        JDialog dialog = new JDialog(f);

        dialog.setSize(200,160);
        dialog.setLayout(new FlowLayout());
        dialog.setLocation(520,240);
        dialog.setModal(true);

        JButton loginButton = new JButton("登陆");
        JButton confirm = new JButton("确定");
        dialog.add(confirm);
        JLabel userNameLable = new JLabel("账户名：");
        userNameLable.setFont(new Font("宋体",Font.PLAIN ,20 ));
        JLabel passwordLable = new JLabel("密码：");
        passwordLable.setFont(new Font("宋体", Font.PLAIN,20 ));

        welcome.setBounds(50, 30, 600, 60);

        userNameLable.setBounds(140,160,80,40);

        userName.setBounds(240,163,180,30);
        passwordLable.setBounds(140,230,80,40);
        password.setBounds(240,233,180,30);
        jPanel.setBounds(200,280,200 ,30 );
        loginButton.setBounds(300,350,80,30);
        //loginButton.setBackground(Color.blue);


        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                userNameContent = userName.getText();
                passwordContent = password.getText();
                if(flag == -1){
                    new popUp("请选择管理员或员工！");
                }
                boolean is = new UserDaoImpl().login(userNameContent, passwordContent, flag);
                if(is){
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            /**
                             * 解决登陆出现的 Bug
                             */
                            boolean is = new UserDaoImpl().login(userNameContent, passwordContent, flag);
                            if (is) {
                                dialog.dispose();
                                f.dispose();
                                if (flag == 1) {
                                    new mainview();
                                } else {
                                    new mainViewStaff();
                                }
                            }
                        }
                    });

                    if(flag==1){
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        new UserDaoImpl().insertLog(userNameContent+"号以管理员身份登陆系统", df.format(System.currentTimeMillis()));
                        dialog.setTitle("管理员登陆成功");
                    }else{
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        new UserDaoImpl().insertLog(userNameContent+"号以员工身份登陆系统", df.format(System.currentTimeMillis()));
                        dialog.setTitle("员工登陆成功");
                    }
                    dialog.setVisible(true);
                }else{
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.dispose();
                        }
                    });
                    if(flag == 1) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        new UserDaoImpl().insertLog(userNameContent + "号以管理员身份登陆系统失败", df.format(System.currentTimeMillis()));
                    }else{
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        new UserDaoImpl().insertLog(userNameContent + "号以员工身份登陆系统失败", df.format(System.currentTimeMillis()));
                    }
                    dialog.setTitle("登陆失败");
                    dialog.setVisible(true);
                }
            }
        });
        f.add(welcome);
        f.add(jPanel);
        f.add(userNameLable);
        f.add(passwordLable);
        f.add(userName);
        f.add(password);
        f.setLocationRelativeTo(null);
        f.add(loginButton);
        f.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("管理员")){
            flag = 1;
        }else if(e.getActionCommand().equals("员工")) {
            flag = 2;
        }
    }
}
