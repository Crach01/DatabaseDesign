package com.company.personnelChange;

import com.company.Dao.impl.UserDaoImpl;
import com.company.util.JDBCUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;


public class personnelView extends JFrame implements ActionListener {
    JTable studentJTable = null;
    Vector studentVector = null;

    //private static DbProcess dbProcess;
    Vector titleVector = null;
    JTextField jTextField = null;

    public personnelView() {

        JLabel jLabel = new JLabel("员工号");
        jTextField = new JTextField(10);
        JButton btn = new JButton("查询");
        JButton btnAll = new JButton("查询所有记录");
        JButton back = new JButton("返回");
        btn.addActionListener(this);
        back.addActionListener(this);
        btnAll.addActionListener(this);

        studentVector = new Vector();
        titleVector = new Vector();

        // 定义表头
        titleVector.add("记录号");
        titleVector.add("员工号");
        titleVector.add("姓名");
        titleVector.add("变化描述");
        titleVector.add("描述");
        titleVector.add("时间");

        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        studentJTable = new JTable(studentVector, titleVector);
        titleVector.setSize(50);
        studentJTable.setPreferredScrollableViewportSize(new Dimension(450, 160));
        JScrollPane studentJScrollPane = new JScrollPane(studentJTable);
        //分别设置水平和垂直滚动条自动出现
        studentJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        this.setLayout(new BorderLayout());
        this.add(studentJScrollPane, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));


        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(btn);
        jPanel.add(back);
        jPanel.add(btnAll);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(jPanel);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);

        this.setVisible(true);


    }

    public static void main(String[] args) {
        new personnelView();
    }

    public void queryProcess(int person) {
        try {
            // 建立查询条件
            String sql = "select * from personnek where person = " + person + " ; ";
            //System.out.println("queryProcess(). sql = " + sql);
            Connection connection = JDBCUtil.getConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);


            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            studentVector.clear();
            System.out.println("hello");
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt("id"));
                v.add(rs.getInt("person"));
                v.add(joinQueryPerson(rs.getInt("person"), "person.name"));
                //v.add(rs.getInt("change"));
                //v.add(rs.getString("description"));
                v.add(joinQueryPerson(rs.getInt("change"), "personnel_change.description"));
                v.add(rs.getString("description"));
                v.add(rs.getString("changeDate"));

                studentVector.add(v);
            }

            studentJTable.updateUI();

            statement.close();
            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queryAll() {
        try {
            // 建立查询条件

            String sql = "select * from personnek  ; ";
            //System.out.println("queryProcess(). sql = " + sql);
            Connection connection = JDBCUtil.getConn();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);


            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            studentVector.clear();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt("id"));
                v.add(rs.getInt("person"));
                v.add(joinQueryPerson(rs.getInt("person"), "person.name"));
                v.add(joinQueryPerson(rs.getInt("change"), "personnel_change.description"));
                v.add(rs.getString("description"));
                v.add(rs.getString("changeDate"));

                studentVector.add(v);
            }

            studentJTable.updateUI();

            statement.close();
            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("查询")) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击查询人事信息", df.format(System.currentTimeMillis()));
            queryProcess(Integer.valueOf(jTextField.getText().trim()));
        } else if (e.getActionCommand().equals("返回")) {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击在查询人事信息窗口返回", df.format(System.currentTimeMillis()));

            this.dispose();
            new personnelChange();
        } else if (e.getActionCommand().equals("查询所有记录")) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            new UserDaoImpl().insertLog(new UserDaoImpl().userID+"号以管理员身份点击查询所有记录", df.format(System.currentTimeMillis()));
            queryAll();
        }


    }

    public String joinQueryPerson(int id, String s) {
        Connection connection = JDBCUtil.getConn();
        ResultSet rs = null;
        String sql = "select * from person,personnek,personnel_change where person.id = personnek.person and personnel_change.code = personnek.change and person.id = " + id + ";";
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                return rs.getString(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "f";
    }

}
