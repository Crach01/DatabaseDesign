package com.company.JSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class popUp extends JFrame implements ActionListener{
        public popUp(String info){

            //JDialog dialog = new JDialog();
            JButton jButton = new JButton("确定");
            this.setTitle("提示窗");
            this.setSize(400,300);
            JLabel jLabel = new JLabel();
            jLabel.setText(info);
            jButton.addActionListener(this);
            this.setLayout(null);
            if(info.length()<4){
                jLabel.setBounds(150, 50, 300,50 );
            }else if(info.length()>4 && info.length()<8){
                jLabel.setBounds(140, 50, 300,50 );
            }
            else{
                jLabel.setBounds(120, 50, 300,50 );
            }

            jLabel.setFont(new Font("宋体", Font.PLAIN,20 ));
            jButton.setBounds(120, 150, 150,50 );

            this.add(jLabel);
            this.add(jButton);

            this.setLocationRelativeTo(null);
            this.setVisible(true);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("确定")){
                this.dispose();
            }
        }

    public static void main(String[] args) {
        new popUp("确认结束！");
    }
}
