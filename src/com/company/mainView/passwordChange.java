package com.company.mainView;

import com.company.Dao.impl.UserDaoImpl;
import com.company.JSwing.popUp;
import com.company.util.JDBCUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class passwordChange extends JFrame implements ActionListener {
    String newPasswd = null;
    JTextField JIDLabel = null;
    JTextField JnewPasswd = null;
    JTextField JnewPasswdAgain = null;
    JTextField JoldPasswd = null;
    JTextField Jname = null;

    public passwordChange(){

        JLabel IDLabel  = new JLabel("员  工  号");
        JLabel name = new JLabel("姓        名");
        JLabel oldPasswd = new JLabel("旧  密  码");
        JLabel newPasswd = new JLabel("新  密  码");
        JLabel newPasswdAgain = new JLabel("确认密码");

        JIDLabel  = new JTextField(10);
        Jname = new JTextField(10);
        JoldPasswd = new JPasswordField(10);
        JnewPasswd = new JPasswordField(10);
        JnewPasswdAgain = new JPasswordField(10);
        JIDLabel.setText(new UserDaoImpl().userID);
        JIDLabel.setEnabled(false);
        JButton confirm = new JButton("确    认");
        confirm.addActionListener(this);

        JButton back = new JButton("返    回");
        back.addActionListener(this);

        JPanel jp1, jp2, jp3, jp4, jp5, jp6 = null;
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();

        jp1.add(IDLabel);
        jp1.add(JIDLabel);
        jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp1.setPreferredSize(new Dimension(20, 20));

        jp2.add(name);
        jp2.add(Jname);
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp2.setPreferredSize(new Dimension(20, 20));

        jp3.add(oldPasswd);
        jp3.add(JoldPasswd);
        jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp3.setPreferredSize(new Dimension(20, 20));

        jp4.add(newPasswd);
        jp4.add(JnewPasswd);
        jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp4.setPreferredSize(new Dimension(20, 20));

        jp5.add(newPasswdAgain);
        jp5.add(JnewPasswdAgain);
        jp5.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp5.setPreferredSize(new Dimension(20, 20));

        jp6.add(confirm);
        jp6.add(back);
        jp6.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jp6.setPreferredSize(new Dimension(20, 20));

        this.setLayout(new GridLayout(6,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
   @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("确    认")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击确认修改密码", df.format(System.currentTimeMillis()));
            String sql = "select * from person where id = " + JIDLabel.getText().trim() + ";";
            ResultSet rs = new UserDaoImpl().executeQuery(sql);
            String name2 = null;
            String password = null;
            try{
                rs.next();
                name2 = rs.getString("name");
                password = rs.getString("passwd");
            }catch (Exception ee){
                ee.printStackTrace();
            }
            if(JnewPasswd.getText().trim().equals("")
                ||JnewPasswdAgain.getText().trim().equals("")
                ||JIDLabel.getText().trim().equals("")
                ||Jname.getText().trim().equals("")
                ||JoldPasswd.getText().trim().equals("")){

                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击确认修改密码，输入为空", df.format(System.currentTimeMillis()));
                new popUp("输入为空，请输入！");
            }else if(!JnewPasswd.getText().trim().equals(JnewPasswdAgain.getText().trim())){
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击确认修改密码，密码不一致", df.format(System.currentTimeMillis()));
                new popUp("密码不一致，请重新输入！");
            }else if(JnewPasswd.getText().trim().equals(JoldPasswd.getText().trim())){
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击确认修改密码，新密码和旧密码重复", df.format(System.currentTimeMillis()));
                new popUp("新密码和旧密码重复！");
            }else if(!Jname.getText().trim().equals(name2)){
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击确认修改密码，用户名错误", df.format(System.currentTimeMillis()));
                new popUp("用户名错误！");
            }else if(!JoldPasswd.getText().trim().equals(password)){
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击确认修改密码，密码错误", df.format(System.currentTimeMillis()));
                new popUp("密码错误！");
            }
            else{
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击确认修改密码，密码修改成功", df.format(System.currentTimeMillis()));
                new popUp("密码修改成功！");
                new UserDaoImpl().changePasswd(JnewPasswd.getText().trim(), Integer.valueOf(JIDLabel.getText().trim()));
            }
        }else if(e.getActionCommand().equals("返    回")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击返回", df.format(System.currentTimeMillis()));
            this.dispose();
        }
    }
    public static void main(String[] args) {
        new passwordChange();
    }
}
