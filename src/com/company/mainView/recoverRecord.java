package com.company.mainView;

import com.company.Dao.impl.UserDaoImpl;
import com.company.JSwing.popUp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class recoverRecord extends JFrame implements ActionListener{

    String sql = null;
    String sqlAll = null;
    JDialog jDialog = null;
    String id = null;
    JTextField jTextField = null;

    public recoverRecord(){

        this.setSize(350,400);

        JLabel jLabel = new JLabel("请输入员工号");
        jLabel.setFont(new Font("",Font.PLAIN ,16));
        jTextField = new JTextField(10);

        JButton recover = new JButton("恢复员工记录");
        recover.addActionListener(this::actionPerformed);
        JButton recoverAll = new JButton("恢复所有记录");
        recoverAll.addActionListener(this::actionPerformed);
        JButton confirm = new JButton("确定");
        recover.setBounds(70, 80, 200, 50);
        recoverAll.setBounds(70, 230, 200, 50);

        id = jTextField.getText().trim();
        System.out.println(id);


        sqlAll = "update person set isdeleted = 0 ;";

        jLabel.setBounds(50, 100, 100, 30);
        jTextField.setBounds(180, 100, 150, 30);
        confirm.setBounds(200, 250, 100,40 );
        confirm.addActionListener(this::actionPerformed);


        jDialog = new JDialog();
        jDialog.setTitle("提示窗");
        jDialog.setSize(400,400);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(null);

        jDialog.add(jLabel);
        jDialog.add(jTextField);
        jDialog.add(confirm);

        this.setLayout(null);
        this.add(recover);
        this.add(recoverAll);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new recoverRecord();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("恢复员工记录")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击恢复员工记录，成功", df.format(System.currentTimeMillis()));
            this.dispose();
            jDialog.setVisible(true);
        }else if(e.getActionCommand().equals("恢复所有记录")) {
            if (new UserDaoImpl().executeUpdate(sqlAll) < 1) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击恢复所有员工记录，失败", df.format(System.currentTimeMillis()));
                new popUp("恢复失败");
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击恢复所有员工记录，成功", df.format(System.currentTimeMillis()));
                new popUp("恢复成功");
            }
        }else if(e.getActionCommand().equals("确定")){
            id = jTextField.getText().trim();
            sql = "update person set isdeleted = 0 where id = " + id + ";";
            if(new UserDaoImpl().executeUpdate(sql)<1){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击恢复员工记录，失败", df.format(System.currentTimeMillis()));
                new popUp("恢复失败");
            }else{
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击恢复员工记录，成功", df.format(System.currentTimeMillis()));
                new popUp("恢复成功");
            }
        }
    }
}
