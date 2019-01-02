package com.company.JSwing;

import com.company.Dao.impl.UserDaoImpl;
import com.company.extendFunction.exportExcel;
import com.company.login.noAuthority;
import com.company.mainView.mainview;
import com.company.mainView.recoverRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.UnrecoverableEntryException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class DatabaseCourseDesign extends JFrame implements ActionListener {

    int flag = -1;

    //
    JLabel jLStudentInfoTable = null;//学生信息表
    JLabel jLSelectQueryField = null;//选择查询字段
    JLabel jLEqual = null;//=
    JLabel jLSNo = null;//学号
    JLabel jLSPassword = null;
    JLabel jLSAuthority = null;
    JLabel jLSName = null;//姓名
    JLabel jLSSex = null;//性别
    JLabel jLSBirthday = null;//生日
    JLabel jLSDepartment = null;//所在部门
    JLabel jLSJob = null;//职务
    JLabel jLSEdu = null;//受教育程度
    JLabel jLSSpecialty = null;//专业技能
    JLabel jLSAddress = null;//住址
    JLabel jLSTel = null;//电话
    JLabel jLSEmail = null;//电子邮箱
    JLabel jLSState = null;//当前状态
    JLabel jLSRemark = null;

    JTextField jTFQueryField = null;//查询字段
    JTextField jTFNo = null;//学号
    JTextField jTFPassword = null;
    JTextField jTFAuthority = null;
    JTextField jTFName = null;//姓名
    JTextField jTFSex = null;//性别
    JTextField jTFBirthday = null;//生日
    JTextField jTFDepartment = null;//所在部门
    JTextField jTFJob = null;//职务
    JTextField jTFEdu = null;//受教育程度
    JTextField jTFSpecialty = null;//专业技能
    JTextField jTFAddress = null;//住址
    JTextField jTFTel = null;//电话
    JTextField jTFEmail = null;//电子邮箱
    JTextField jTFState = null;//当前状态
    JTextField jTFRemark = null;

    JButton jBQuery = null;//查询
    JButton jBQueryAll = null;//查询所有记录
    JButton jBUpdate = null;//更新
    JButton jBDeleteCurrentRecord = null;//删除当前记录
    JButton jBDeleteAllRecords = null;//删除所有记录
    JButton jBBack = null;//返回主界面
    JButton jBRecover = null;//数据恢复
    JButton jBExport = null;//数据导出

    JComboBox<String> jCBSelectQueryField = null;//查询字段
    JPanel jP1, jP2, jP3, jP4, jP5, jP6, jP7, jP8, jP9, jP10, jP11 = null;
    JPanel jPTop, jPBottom = null;
    DefaultTableModel studentTableModel = null;
    JTable studentJTable = null;
    JScrollPane studentJScrollPane = null;
    Vector studentVector = null;
    Vector titleVector = null;

    //private static UserDaoImpl userDaoImpl;
    String SelectQueryFieldStr = "员工号";

    // 构造函数
    public DatabaseCourseDesign(int flag) {
        //System.out.println("userID" + new UserDaoImpl().userID);
        this.flag = flag;
        // 创建组件
        jLStudentInfoTable = new JLabel("员工信息表");
        jLSelectQueryField = new JLabel("选择查询字段");
        jLEqual = new JLabel(" = ");
        jLSNo = new JLabel("员工号");
        jLSName = new JLabel("姓    名");
        jLSSex = new JLabel("性别");
        jLSBirthday = new JLabel("生日");
        jLSDepartment = new JLabel("部    门");
        jLSJob = new JLabel("职位");
        jLSEdu = new JLabel("学历");
        jLSSpecialty = new JLabel("技    能");
        jLSAddress = new JLabel("地址");
        jLSTel = new JLabel("电话");
        jLSEmail = new JLabel("邮    箱");
        jLSState = new JLabel("状态");
        jLSAuthority = new JLabel("权限");
        jLSPassword = new JLabel("密码");
        jLSRemark = new JLabel("备注");

        jTFQueryField = new JTextField(10);//查询字段
        if(flag == 2){
            jTFQueryField.setText(new UserDaoImpl().userID);
        }
        jTFNo = new JTextField(10);
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
        jTFRemark = new JTextField(10);
        //jTFPersonnel = new JTextField(10);


        jBQuery = new JButton("查询");
        jBQueryAll = new JButton("查询所有记录");
        //jBInsert = new JButton("插入");
        jBUpdate = new JButton("更新");
        jBDeleteCurrentRecord = new JButton("删除当前记录");
        jBDeleteAllRecords = new JButton("删除所有记录");
        jBBack = new JButton("返回主界面");
        jBRecover = new JButton("恢复记录");
        jBExport = new JButton("导出");

        jBRecover.addActionListener(this);
        // 设置监听
        jBQuery.addActionListener(this);
        jBQueryAll.addActionListener(this);

        jBUpdate.addActionListener(this);
        jBDeleteCurrentRecord.addActionListener(this);
        jBDeleteAllRecords.addActionListener(this);
        jBBack.addActionListener(this);

        //下拉框
        jCBSelectQueryField = new JComboBox<String>();//查询字段
        jCBSelectQueryField.addItem("员工号");
        jCBSelectQueryField.addItem("姓名");
        jCBSelectQueryField.addItem("性别");
        jCBSelectQueryField.addItem("生日");
        jCBSelectQueryField.addItem("部门");
        jCBSelectQueryField.addItem("职位");
        jCBSelectQueryField.addItem("学历");
        jCBSelectQueryField.addItem("技能");
        jCBSelectQueryField.addItem("住址");
        jCBSelectQueryField.addItem("电话");
        jCBSelectQueryField.addItem("邮箱");
        jCBSelectQueryField.addItem("状态");
        jCBSelectQueryField.addItemListener(new ItemListener() {//下拉框事件监听
            @Override
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        SelectQueryFieldStr = (String) event.getItem();
                        //System.out.println("选中：" + SelectQueryFieldStr);
                        break;
                    case ItemEvent.DESELECTED:
                        //System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });

        studentVector = new Vector();
        titleVector = new Vector();

        // 定义表头
        titleVector.add("员工号");
        titleVector.add("姓名");
        titleVector.add("性别");
        titleVector.add("部门");
        titleVector.add("职位");
        titleVector.add("学历");
        titleVector.add("电话");
        titleVector.add("邮箱");
        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        studentJTable = new JTable(studentVector, titleVector);
        titleVector.setSize(50);
        studentJTable.setPreferredScrollableViewportSize(new Dimension(460, 210));
        studentJScrollPane = new JScrollPane(studentJTable);
        //分别设置水平和垂直滚动条自动出现
        studentJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //为表格添加监听器
        studentJTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
                Vector v = new Vector();
                v = (Vector) studentVector.get(row);
                //System.out.println(v.get(0).getClass());
                //System.out.println(String.valueOf(v.get(0)));
                showSingle(String.valueOf(v.get(0)));
                //showSingle(Integer.toString((Integer) v.get(0)));
            }
        });


        jP1 = new JPanel();
        jP2 = new JPanel();
        jP3 = new JPanel();
        jP4 = new JPanel();
        jP5 = new JPanel();
        jP6 = new JPanel();
        jP7= new JPanel();
        jP8 = new JPanel();
        jP9 = new JPanel();
        jP10 = new JPanel();
        jP11 = new JPanel();
        jPTop = new JPanel();
        jPBottom = new JPanel();

        jP1.add(jLStudentInfoTable, BorderLayout.SOUTH);
        jP2.add(studentJScrollPane);


        jP3.add(jLSelectQueryField);
        jP3.add(jCBSelectQueryField);
        jP3.add(jLEqual);
        jP3.add(jTFQueryField);
        jP3.add(jBQuery);
        jP3.add(jBQueryAll);

        jP3.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP3.setPreferredSize(new Dimension(20, 20));

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


        jP6.add(jBRecover);
        jP6.add(jBUpdate);
        jP6.add(jBDeleteCurrentRecord);
        jP6.add(jBDeleteAllRecords);
        jP6.add(jBBack);

        jP6.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP6.setPreferredSize(new Dimension(20, 20));


        jP11.add(jBExport);
        jBExport.addActionListener(this);
        jP11.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jP11.setPreferredSize(new Dimension(20, 20));

        jPTop.add(jP1);
        jPTop.add(jP2);

        jPBottom.setLayout(new GridLayout(9, 1));
        jPBottom.add(jP11);
        jPBottom.add(jP3);
        jPBottom.add(jP4);
        jPBottom.add(jP5);
        jPBottom.add(jP7);
        jPBottom.add(jP8);
        jPBottom.add(jP9);
        jPBottom.add(jP10);
        jPBottom.add(jP6);

        this.add("North", jPTop);
        this.add("South", jPBottom);
        this.setLayout(new GridLayout(2, 1));
        this.setTitle("员工信息查询修改窗口");
        this.setSize(500, 600);
        this.setLocation(150, 150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("查询")
                && !jTFQueryField.getText().isEmpty()) {
            String sQueryField = jTFQueryField.getText().trim();
            String id = jTFQueryField.getText().trim();
            if(flag==2 ){
                if(!new UserDaoImpl().userID.equals(id)){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份打开员工信息查询别人信息记录，没有权限", df.format(System.currentTimeMillis()));
                    new popUp("您没有权限查看别人信息！");
                }else{
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份打开员工信息查询自己记录成功", df.format(System.currentTimeMillis()));
                    queryProcess(sQueryField);
                    jTFQueryField.setText("");
                }
            }else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份打开员工信息查询记录成功", df.format(System.currentTimeMillis()));
                queryProcess(sQueryField);
                jTFQueryField.setText("");
            }
        } else if (e.getActionCommand().equals("查询所有记录")
                && flag == 1
        ) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份打开员工信息查询所有记录成功", df.format(System.currentTimeMillis()));
            queryAllProcess();
        } else if (e.getActionCommand().equals("更新")
                && !jTFNo.getText().isEmpty()
                && !jTFName.getText().isEmpty()
                && !jTFSex.getText().isEmpty()
                && !jTFSpecialty.getText().isEmpty()
                && !jTFAddress.getText().isEmpty()
                && flag == 1) {
            //new popUp("更新成功！");
            //System.out.println("actionPerformed(). 更新");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份更新员工信息成功", df.format(System.currentTimeMillis()));
            updateProcess();
        } else if (e.getActionCommand().equals("删除当前记录")
                && flag == 1) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份删除员工信息成功", df.format(System.currentTimeMillis()));
            new popUp("删除成功！");
            //System.out.println("actionPerformed(). 删除当前记录");
            deleteCurrentRecordProcess();
        } else if (e.getActionCommand().equals("删除所有记录")
                && flag == 1) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份删除所有员工信息成功", df.format(System.currentTimeMillis()));
            new popUp("删除成功！");
            //System.out.println("actionPerformed(). 删除所有记录");
            deleteAllRecordsProcess();
        }else if(e.getActionCommand().equals("返回主界面")){
            //if(flag){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份返回主界面", df.format(System.currentTimeMillis()));
            this.dispose();
                //new mainview();
            //}else{
                //this.dispose();
                //new mainViewStaff();
            //}
        }else if(flag == 2
                && !e.getActionCommand().equals("查询")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以员工身份点击非查询按钮，没有权限", df.format(System.currentTimeMillis()));
            new popUp("您没有此权限！");
        }else if(e.getActionCommand().equals("恢复记录") && flag == 1){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份恢复记录，成功", df.format(System.currentTimeMillis()));
            new recoverRecord();
        }else if(e.getActionCommand().equals("导出")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份导出记录，成功", df.format(System.currentTimeMillis()));
            new exportExcel();
            new popUp("导出成功");
        }
    }

    public static void main(String[] args) {
         //TODO Auto-generated method stub
        new DatabaseCourseDesign(1);
    }

    public void queryProcess(String sQueryField) {

        try {
            // 建立查询条件
            String sql = "select * from person where isdeleted = 0  and  ";
            String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
            if (queryFieldStr.equals("Age")) {//int sAge.
                sql = sql + queryFieldStr;
                sql = sql + " = " + sQueryField;
            } else if(!queryFieldStr.equals("id")){
                sql = sql + queryFieldStr;
                sql = sql + " like ";
                sql = sql + "'%" + sQueryField + "%';";
            }else{
                sql = sql + queryFieldStr;
                sql = sql + " = ";
                sql = sql + "'" + sQueryField + "';";
            }

            //System.out.println("queryProcess(). sql = " + sql);

            //dbProcess.connect();
            //ResultSet rs = dbProcess.executeQuery(sql);
            ResultSet rs = new UserDaoImpl().executeQuery(sql);
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            studentVector.clear();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(Integer.valueOf(rs.getInt("id")));
                jTFNo.setText(rs.getString("id"));

                //v.add(rs.getString("passwd"));
                jTFPassword.setText(rs.getString("passwd"));

                //v.add(rs.getString("Authority"));
                jTFAuthority.setText(rs.getString("Authority"));

                v.add(rs.getString("Name"));
                jTFName.setText(rs.getString("Name"));
                v.add(rs.getString("Sex"));
                jTFSex.setText(rs.getString("sex"));
                //v.add(rs.getString("birthday"));
                jTFBirthday.setText(rs.getString("birthday"));

                v.add(new UserDaoImpl().joinQuery("department",rs.getString("id") ));
                jTFDepartment.setText(new UserDaoImpl().joinQuery("department",rs.getString("id") ));

                v.add(new UserDaoImpl().joinQuery("job", rs.getString("id")));
                jTFJob.setText(new UserDaoImpl().joinQuery("job",rs.getString("id")));
                v.add(new UserDaoImpl().joinQuery("edu_level",rs.getString("id")));
                jTFEdu.setText(new UserDaoImpl().joinQuery("edu_level",rs.getString("id")));

                jTFSpecialty.setText(rs.getString("spcialty"));

                jTFAddress.setText(rs.getString("address"));

                v.add(rs.getString("tel"));
                jTFTel.setText(rs.getString("tel"));

                v.add(rs.getString("email"));
                jTFEmail.setText(rs.getString("email"));

                jTFState.setText(rs.getString("state"));

                jTFRemark.setText(rs.getString("remark"));

                studentVector.add(v);
            }

            studentJTable.updateUI();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void queryAllProcess() {
        String sql = "select * from person where isdeleted = 0;";
        try {

            ResultSet rs = new UserDaoImpl().executeQuery(sql);
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            studentVector.clear();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));

                v.add(rs.getString("name"));

                v.add(rs.getString("sex"));
                v.add(new UserDaoImpl().joinQuery("department",rs.getString("id") ));
                v.add(new UserDaoImpl().joinQuery("job", rs.getString("id")));

                v.add(new UserDaoImpl().joinQuery( "edu_level", rs.getString("id")));
                v.add(rs.getString("tel"));
                v.add(rs.getString("email"));
                studentVector.add(v);
            }
            studentJTable.updateUI();
        } catch (SQLException sqle) {
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void updateProcess() {
        String id = jTFNo.getText().trim();
        String Name = jTFName.getText().trim();
        String passwd = jTFPassword.getText().trim();
        String authority = jTFAuthority.getText().trim();

        String Sex = jTFSex.getText().trim();
        String birthday = jTFBirthday.getText().trim();
        String dep = jTFDepartment.getText().trim();

        String job = jTFJob.getText().trim();
        String edu = jTFEdu.getText().trim();
        String Specialty = jTFSpecialty.getText().trim();
        String Address = jTFAddress.getText().trim();
        String tel = jTFTel.getText().trim();
        String email = jTFEmail.getText().trim();
        String state = jTFState.getText().trim();
        String remark = jTFRemark.getText().trim();

        // 建立更新条件
        String sql = "update person set ";
        //sql = sql + "id = " + id + " ,' ";
        sql = sql + "passwd = '" + passwd + "',";
        sql = sql + "authority = '" + authority + "',";
        sql = sql + "name = '" + Name + "',";
        sql = sql + "sex = '" + Sex + "',";
        sql = sql + "birthday = '" + birthday + "', ";
        sql = sql + "department = " + depN(dep) + " , ";
        sql = sql + "job = " + jobN(job) + " , ";
        sql = sql + "edu_level = " + eduQ(edu) + " , ";
        sql = sql + "Spcialty = '" + Specialty + "' , ";
        sql = sql + "Address = '" + Address + "', ";
        sql = sql + "tel = " +tel + " ,";
        sql = sql + "email = '" +email + "',";
        sql = sql + "remark = '" + remark + "',";

        sql = sql + "state = '" +state + "'  where id="+id+" ; ";
        //System.out.println(sql);

        try {
            if (new UserDaoImpl().executeUpdate(sql) < 1) {
                System.out.println("updateProcess(). update database failed.");
            }else{
                new popUp("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        queryAllProcess();
    }

    public String depN(String dep) {

        if (dep.equals("数据平台事业部")) {
            return "1";
        } else if (dep.equals("阿里云事业部")) {
            return "2";
        } else if(dep.equals("互动娱乐事业群")) {
            return "3";
        }else if(dep.equals("游戏事业部")) {
            return "4";
        }else if(dep.equals("财务事业部")) {
            return "5";
        }else if(dep.equals("法务事业部")) {
            return "6";
        }else if(dep.equals("天猫事业部")) {
            return "7";
        }else if(dep.equals("本地生活事业部")) {
            return "8";
        }else if(dep.equals("数字业务事业部")) {
            return "9";
        }else if(dep.equals("消费者业务事业部")) {
            return "10";
        }
        return "1";
    }

    public void deleteCurrentRecordProcess() {
        String sNo = jTFNo.getText().trim();

        // 建立删除条件
        //String sql = "delete from person where id = '" + sNo + "';";
        String sql = "update person set isdeleted = 1 where id = '"  + sNo + "'; ";
        try {
            if (new UserDaoImpl().executeUpdate(sql) < 1) {
                System.out.println("deleteCurrentRecordProcess(). delete database failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        queryAllProcess();
    }

    public void deleteAllRecordsProcess() {
        // 建立删除条件
        //String sql = "delete from student;";
        String sql = "update person set isdeleted = 1;";
        try {
            if (new UserDaoImpl().executeUpdate(sql) < 1) {
                System.out.println("deleteAllRecordsProcess(). delete database failed.");
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
    }

    public String jCBSelectQueryFieldTransfer(String InputStr) {
        String outputStr = "";

        if (InputStr.equals("员工号")) {
            outputStr = "id";
        } else if (InputStr.equals("姓名")) {
            outputStr = "Name";
        } else if (InputStr.equals("性别")) {
            outputStr = "Sex";
        } else if (InputStr.equals("学历")) {
            outputStr = "edu_level";
        } else if (InputStr.equals("技能")) {
            outputStr = "Spcialty";
        } else if (InputStr.equals("住址")) {
            outputStr = "Address";
        } else if(InputStr.equals("部门")){
            outputStr = "department";
        } else if(InputStr.equals("状态")){
            outputStr = "state";
        }
        return outputStr;
    }

    public  String eduQ(String edu){

        if (edu.equals("小学")) {
           return "0";
        } else if (edu.equals("初中")) {
            return "1";
        }else if (edu.equals("高中")) {
            return  "2";
        }else if (edu.equals("大本")) {
            return "4";
        }else if (edu.equals("大专")) {
            return "5";
        }else if (edu.equals("硕士")) {
            return "6";
        }else if (edu.equals("博士")) {
            return "7";
        }else if (edu.equals("博士后")) {
            return "8";
        }else if (edu.equals("职高")) {
            return "3";
        }
        return "0";
    }

    public String jobN(String job){

        if("CEO".equals(job)){
            return "1";
        } else if("技术总监".equals(job)){
            return "2";
        }else if(job.equals("部门经理")){
            return "3";
        }else if(job.equals("职员")){
            return "4";
        }else if(job.equals("财务总监")){
            return "5";
        }else if(job.equals("轮值CEO")){
            return "6";
        }else if(job.equals("财务专员")){
            return "7";
        }else if(job.equals("法务专员")){
            return "8";
        }else if(job.equals("游戏策划")){
            return "9";
        }else if(job.equals("Java工程师")){
            return "10";
        }else if(job.equals("美工")){
            return "11";
        }else if(job.equals("CMO")){
            return "12";
        }

        return "0";
    }

    public void showSingle(String id){
        try {
            // 建立查询条件
            String sql = "select * from person where id = " + id;

            ResultSet rs = new UserDaoImpl().executeQuery(sql);
            while (rs.next()) {
                jTFNo.setText(rs.getString("id"));

                jTFPassword.setText(rs.getString("passwd"));

                jTFAuthority.setText(rs.getString("Authority"));

                jTFName.setText(rs.getString("Name"));
                jTFSex.setText(rs.getString("sex"));
                jTFBirthday.setText(rs.getString("birthday"));

                jTFDepartment.setText(new UserDaoImpl().joinQuery("department",rs.getString("id") ));

                jTFJob.setText(new UserDaoImpl().joinQuery("job",rs.getString("id")));
                jTFEdu.setText(new UserDaoImpl().joinQuery("edu_level",rs.getString("id")));

                jTFSpecialty.setText(rs.getString("spcialty"));

                jTFAddress.setText(rs.getString("address"));

                jTFTel.setText(rs.getString("tel"));

                jTFEmail.setText(rs.getString("email"));

                jTFState.setText(rs.getString("state"));

                jTFRemark.setText(rs.getString("remark"));

            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

}
