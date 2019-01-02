package com.company.addPerson;

import com.company.Dao.impl.UserDaoImpl;
import com.company.JSwing.popUp;
import com.company.extendFunction.excelImport;
import com.company.extendFunction.selectFile;
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
import java.util.Stack;


public class addNewPerson extends JFrame implements ActionListener {

    public JPanel jP4 = null, jP5 = null, jP7 = null, jP8 = null, jP9 = null;
    public JPanel jPBottom = null;
    public JPanel jPButton = null;
    public JButton btn = null;
    public JButton backMain = null;

    public JLabel jLSNo = null;//学号
    public JLabel jLSPassword = null;
    public JLabel jLSAuthority = null;
    public JLabel jLSName = null;//姓名
    public JLabel jLSSex = null;//性别
    public JLabel jLSBirthday = null;//生日
    public JLabel jLSDepartment = null;//所在部门
    public JLabel jLSJob = null;//职务
    public JLabel jLSEdu = null;//受教育程度
    public JLabel jLSSpecialty = null;//专业技能
    public JLabel jLSAddress = null;//住址
    public JLabel jLSTel = null;//电话
    public JLabel jLSEmail = null;//电子邮箱
    public JLabel jLSState = null;//当前状态
    public JLabel jLSRemark = null;

    public JTextField jTFNo = null;//学号
    public JTextField jTFPassword = null;
    public JTextField jTFAuthority = null;
    public JTextField jTFName = null;//姓名
    public JTextField jTFSex = null;//性别
    public JTextField jTFBirthday = null;//生日
    public JTextField jTFDepartment = null;//所在部门
    public JTextField jTFJob = null;//职务
    public JTextField jTFEdu = null;//受教育程度
    public JTextField jTFSpecialty = null;//专业技能
    public JTextField jTFAddress = null;//住址
    public JTextField jTFTel = null;//电话
    public JTextField jTFEmail = null;//电子邮箱
    public JTextField jTFState = null;//当前状态
    public JTextField jTFRemark = null;//当前状态

    public addNewPerson() {


        jLSNo = new JLabel("员工号");
        jLSNo.setFont(new Font("", Font.PLAIN, 20));

        jLSPassword = new JLabel("密码");
        jLSPassword.setFont(new Font("", Font.PLAIN, 20));

        jLSAuthority = new JLabel("权限");
        jLSAuthority.setFont(new Font("", Font.PLAIN, 20));

        jLSName = new JLabel("姓    名");
        jLSName.setFont(new Font("", Font.PLAIN, 20));

        jLSSex = new JLabel("性别");
        jLSSex.setFont(new Font("", Font.PLAIN, 20));

        jLSBirthday = new JLabel("生日");
        jLSBirthday.setFont(new Font("", Font.PLAIN, 20));

        jLSDepartment = new JLabel("部    门");
        jLSDepartment.setFont(new Font("", Font.PLAIN, 20));

        jLSJob = new JLabel("职位");
        jLSJob.setFont(new Font("", Font.PLAIN, 20));

        jLSEdu = new JLabel("学历");
        jLSEdu.setFont(new Font("", Font.PLAIN, 20));

        jLSSpecialty = new JLabel("技    能");
        jLSSpecialty.setFont(new Font("", Font.PLAIN, 20));

        jLSAddress = new JLabel("地址");
        jLSAddress.setFont(new Font("", Font.PLAIN, 20));

        jLSTel = new JLabel("电话");
        jLSTel.setFont(new Font("", Font.PLAIN, 20));

        jLSEmail = new JLabel("邮    箱");
        jLSEmail.setFont(new Font("", Font.PLAIN, 20));

        jLSState = new JLabel("状态");
        jLSState.setFont(new Font("", Font.PLAIN, 20));

        jLSRemark = new JLabel("备注");
        jLSRemark.setFont(new Font("", Font.PLAIN, 20));


        jTFNo = new JTextField(10);
        jTFNo.setText(String.valueOf(staffNum() + 1));
        jTFName = new JTextField(10);
        jTFSex = new JTextField(10);
        jTFBirthday = new JTextField(10);
        jTFDepartment = new JTextField(10);
        jTFJob = new JTextField(10);
        jTFEdu = new JTextField(10);
        jTFSpecialty = new JTextField(10);
        jTFAddress = new JTextField(10);
        jTFTel = new JTextField(10);
        jTFEmail = new JTextField(10);
        jTFState = new JTextField(10);
        jTFAuthority = new JTextField(10);
        jTFPassword = new JTextField(10);
        jTFPassword.setText("123");
        jTFRemark = new JTextField(10);

        jP4 = new JPanel();
        jP5 = new JPanel();
        jP7 = new JPanel();
        jP8 = new JPanel();
        jP9 = new JPanel();

        jLSAddress.setSize(30, 20);

        jP4.add(jLSNo);
        jP4.add(jTFNo);
        jP4.add(jLSPassword);
        jP4.add(jTFPassword);
        jP4.add(jLSAuthority);
        jP4.add(jTFAuthority);
        jP4.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP4.setPreferredSize(new Dimension(20, 20));


        jP5.add(jLSName);
        jP5.add(jTFName);
        jP5.add(jLSSex);
        jP5.add(jTFSex);
        jP5.add(jLSBirthday);
        jP5.add(jTFBirthday);
        jP5.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP5.setPreferredSize(new Dimension(20, 20));

        jP7.add(jLSDepartment);
        jP7.add(jTFDepartment);
        jP7.add(jLSJob);
        jP7.add(jTFJob);
        jP7.add(jLSEdu);
        jP7.add(jTFEdu);
        jP7.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP7.setPreferredSize(new Dimension(20, 20));

        jP8.add(jLSSpecialty);
        jP8.add(jTFSpecialty);
        jP8.add(jLSAddress);
        jP8.add(jTFAddress);
        jP8.add(jLSTel);
        jP8.add(jTFTel);
        jP8.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP8.setPreferredSize(new Dimension(20, 20));

        jP9.add(jLSEmail);
        jP9.add(jTFEmail);
        jP9.add(jLSState);
        jP9.add(jTFState);
        jP9.add(jLSRemark);
        jP9.add(jTFRemark);
        jP9.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP9.setPreferredSize(new Dimension(20, 20));


        jPBottom = new JPanel();
        jPBottom.setLayout(new GridLayout(5, 1));
        jPBottom.add(jP4);
        jPBottom.add(jP5);
        jPBottom.add(jP7);
        jPBottom.add(jP8);
        jPBottom.add(jP9);


        btn = new JButton("提交");
        backMain = new JButton("返回主界面");
        JButton excelImport = new JButton("从excel导入");


        jPButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPButton.add(btn);
        jPButton.add(backMain);
        jPButton.add(excelImport);
        btn.addActionListener(this);
        excelImport.addActionListener(this);

        this.add(jPBottom);
        this.add(jPButton);
        jPBottom.setBounds(0, 0, 500, 250);
        jPButton.setBounds(0, 300, 500, 250);
        backMain.addActionListener(this);

        this.setLayout(null);
        this.setTitle("增加新员工信息");
        this.setSize(500, 500);
        this.setLocation(150, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("提交")) {
            int i = judge();
            if (i == 0) {
                new popUp("添加成功");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID + "号以管理员身份添加新员工" + jTFNo.getText().trim() + "信息成功", df.format(System.currentTimeMillis()));
                addInfo();
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID + "号以管理员身份添加新员工" + jTFNo.getText().trim() + "信息失败", df.format(System.currentTimeMillis()));
                new popUp("添加失败");
            }
        } else if (e.getActionCommand().equals("返回主界面")) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID + "号以管理员身份点击返回主界面", df.format(System.currentTimeMillis()));
            this.dispose();
        } else if (e.getActionCommand().equals("从excel导入")) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID + "号以管理员身份点击从excel导入数据", df.format(System.currentTimeMillis()));
            new selectFile();
        }

    }

    public void addInfo() {
        String id = jTFNo.getText().trim();
        String name = jTFName.getText().trim();
        String sex = jTFSex.getText().trim();
        String birthday = jTFBirthday.getText().trim();
        String dep = jTFDepartment.getText().trim();
        String job = jTFJob.getText().trim();
        String edu = jTFEdu.getText().trim();
        String specialty = jTFSpecialty.getText().trim();
        String address = jTFAddress.getText().trim();
        String tel = jTFTel.getText().trim();
        String email = jTFEmail.getText().trim();
        String state = jTFState.getText().trim();
        String password = jTFPassword.getText().trim();
        String authority = jTFAuthority.getText().trim();
        String remark = jTFRemark.getText().trim();


        String sql = "insert into person values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = JDBCUtil.getConn();
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, Integer.valueOf(id));
            psmt.setString(2, password);

            psmt.setString(3, authority);
            psmt.setString(4, name);
            psmt.setString(5, sex);
            psmt.setString(6, birthday);
            psmt.setInt(7, Integer.valueOf(depN(dep)));
            psmt.setInt(8, Integer.valueOf(jobN(job)));
            psmt.setInt(9, Integer.valueOf(eduQ(edu)));
            psmt.setString(10, specialty);
            psmt.setString(11, address);
            psmt.setString(12, tel);
            psmt.setString(13, email);
            psmt.setString(14, state);
            psmt.setString(15, remark);
            psmt.setInt(16, 0);

            psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new addNewPerson();
    }

    public String depN(String dep) {

        if (dep.equals("数据平台事业部")) {
            return "1";
        } else if (dep.equals("阿里云事业部")) {
            return "2";
        } else if (dep.equals("互动娱乐事业群")) {
            return "3";
        }
        return "1";
    }

    public String eduQ(String edu) {

        if (edu.equals("小学")) {
            return "0";
        } else if (edu.equals("初中")) {
            return "1";
        } else if (edu.equals("高中")) {
            return "2";
        } else if (edu.equals("大本")) {
            return "4";
        } else if (edu.equals("大专")) {
            return "5";
        } else if (edu.equals("硕士")) {
            return "6";
        } else if (edu.equals("博士")) {
            return "7";
        } else if (edu.equals("博士后")) {
            return "8";
        } else if (edu.equals("职高")) {
            return "3";
        }
        return "0";
    }

    public String jobN(String job) {

        if ("CEO".equals(job)) {
            return "1";
        } else if ("技术总监".equals(job)) {
            return "2";
        } else if (job.equals("部门经理")) {
            return "3";
        } else if (job.equals("职员")) {
            return "4";
        }
        return "0";
    }

    public int judge() {

        String id = jTFNo.getText().trim();
        Connection connection = JDBCUtil.getConn();
        Statement statement = null;
        ResultSet rs = null;

        String sql = "select * from person;";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("id").equals(id)) {
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int staffNum() {
        int i = 0;
        Connection connection = JDBCUtil.getConn();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from person ; ");
            while (rs.next()) {
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return i;
    }
}
