package com.company.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class noAuthority extends JFrame {

    public noAuthority(){
        this.setTitle("提示窗");
        JLabel jLabel = new JLabel("您没有操作权限！");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(jLabel);
        this.setSize(100,80);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


}
