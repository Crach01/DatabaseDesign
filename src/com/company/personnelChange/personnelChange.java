package com.company.personnelChange;

import com.company.Dao.impl.UserDaoImpl;
import com.company.JSwing.DatabaseCourseDesign;
import com.company.addPerson.addNewPerson;
import com.company.mainView.mainview;
import com.company.util.JDBCUtil;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class personnelChange extends JFrame implements ActionListener{

    public JPanel jp1, jp2, jp3, jp4, jp5, jp6 = null;
    public JPanel jPanel = new JPanel();

    public JLabel jLSId = new JLabel("记录编号    ");//记录编号
    public JTextField jTFId = new JTextField(10);


    public JLabel jLSPerson = new JLabel("员  工  号    ");//员工号
    public JTextField jTFPerson = new JTextField(10);

    public JLabel jLSChange = new JLabel("变更代码    ");//变更代码
    public JTextField jTFChange = new JTextField(10);

    public JLabel jLSDescription = new JLabel("详细描述    ");//详细描述
    public JTextField jTFDescription = new JTextField(10);

    public JLabel jLSChangeDate = new JLabel("变动时间    ");//详细描述
    public JTextField jTFChangeDate = new JTextField(10);

    public JButton jbtn = new JButton("提交");//提交按钮
    public JButton back = new JButton("返回主界面");
    public JButton query = new JButton("查询人事信息");//提交按钮

    public personnelChange(){

        jLSId.setFont(new Font("", Font.PLAIN, 20));

        jLSPerson.setFont(new Font("", Font.PLAIN, 20));
        jLSChange.setFont(new Font("", Font.PLAIN, 20));
        jLSDescription.setFont(new Font("", Font.PLAIN, 20));
        jLSChangeDate.setFont(new Font("", Font.PLAIN, 20));
        //jLSId.setFont(new Font("", Font.PLAIN, 20));
        jTFId.setFont(new Font("",Font.PLAIN ,15 ));
        jTFId.setEnabled(false);
        jTFPerson.setFont(new Font("",Font.PLAIN ,15 ));
        jTFChange.setFont(new Font("",Font.PLAIN ,15 ));
        jTFDescription.setFont(new Font("",Font.PLAIN ,15 ));
        jTFChangeDate.setFont(new Font("",Font.PLAIN ,15 ));

        jbtn.setFont(new Font("",Font.PLAIN ,15 ));
        back.setFont(new Font("",Font.PLAIN ,15 ));
        query.setFont(new Font("",Font.PLAIN ,15 ));



        this.setTitle("人事变动信息输入");

        jTFId.setText(Integer.toString(new UserDaoImpl().count("select * from personnek;")));
        jp1 = new JPanel();
        jp1.add(jLSId);
        jp1.add(jTFId);
        jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp1.setPreferredSize(new Dimension(20, 20));

        jp2 = new JPanel();
        jp2.add(jLSPerson);
        jp2.add(jTFPerson);
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp2.setPreferredSize(new Dimension(20, 20));

        jp3 = new JPanel();
        jp3.add(jLSChange);
        jp3.add(jTFChange);
        jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp3.setPreferredSize(new Dimension(20, 20));

        jp4 = new JPanel();
        jp4.add(jLSDescription);
        jp4.add(jTFDescription);
        jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp4.setPreferredSize(new Dimension(20, 20));

        jp6 = new JPanel();
        jp6.add(jLSChangeDate);
        jp6.add(jTFChangeDate);
        jp6.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp6.setPreferredSize(new Dimension(20, 20));

        jp5 = new JPanel();
        jp5.add(jbtn);
        jp5.add(back);
        jp5.add(query);
        jbtn.addActionListener(this);
        back.addActionListener(this);
        query.addActionListener(this);
        jp5.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jPanel.setLayout(new GridLayout(6,1));
        jPanel.add(jp1);
        jPanel.add(jp2);
        jPanel.add(jp3);
        jPanel.add(jp4);
        jPanel.add(jp6);
        jPanel.add(jp5);

        this.add(jPanel);
        this.setLayout(new GridLayout(1,1));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,350);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("提交")
                //&& !jTFId.getText().isEmpty()
                && !jTFChange.getText().isEmpty()
                && !jTFDescription.getText().isEmpty()
                && !jTFPerson.getText().isEmpty()
                && !jTFChangeDate.getText().isEmpty()){
            if(jTFChange.getText().trim().equals("2")){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份辞退"+jTFPerson.getText()+"人事信息", df.format(System.currentTimeMillis()));
                new UserDaoImpl().deletePerson(jTFPerson.getText().trim());
            }else if(jTFChange.getText().trim().equals("0")) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份添加辞退"+jTFPerson.getText()+"人事信息", df.format(System.currentTimeMillis()));
                new addNewPerson();
            }
            submit();
        }else if(e.getActionCommand().equals("返回主界面")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击返回主界面", df.format(System.currentTimeMillis()));
            this.dispose();
        }else if(e.getActionCommand().equals("查询人事信息")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击查询人事信息", df.format(System.currentTimeMillis()));
            new personnelView();
        }
    }
    public void submit(){
        String id = jTFId.getText().trim();
        String person = jTFPerson.getText().trim();
        String change = jTFChange.getText().trim();
        String description = jTFDescription.getText().trim();
        String date = jTFChangeDate.getText().trim();

        String sql = "insert into personnek values(?,?,?,?,?)";
        Connection connection = JDBCUtil.getConn();
        PreparedStatement psmt = null;
        try{
             psmt = connection.prepareStatement(sql);
             psmt.setInt(1,Integer.valueOf(id));
             psmt.setInt(2,Integer.valueOf(person));
             psmt.setInt(3,Integer.valueOf(change));
             psmt.setString(4,description);
             psmt.setString(5,date);

             psmt.executeUpdate();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份添加"+person+"人事信息", df.format(System.currentTimeMillis()));

            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new personnelChange();
    }


}
