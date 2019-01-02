package com.company.Dao.impl;

import com.company.Dao.UserDao;
import com.company.util.JDBCUtil;
import com.mysql.cj.jdbc.StatementImpl;

import javax.swing.*;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.*;
import java.util.Vector;

import static com.company.util.JDBCUtil.getConn;
import static com.company.util.JDBCUtil.release;

public class UserDaoImpl implements UserDao {
    public static String userID = null;

    @Override
    public boolean login(String userName, String password, int flag) {
        userID = userName;
        Connection conn = getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        if (userName.equals("root") && flag == 1) {
            sql = "select * from t_user where username = ? and password = ? ;";
        } else {
            if (flag == 1) {
                sql = "select * from person where id = ? and passwd = ?  and authority = '1' ;";
            } else if (flag == 2) {
                sql = "select * from person where id = ? and passwd = ?  ;";
            }
        }


        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps, rs);
        }
        return false;
    }

    @Override
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        Statement st = null;
        Connection connection = getConn();
        try {
            st = connection.createStatement();
            // 执行查询

            rs = st.executeQuery(sql);
            //return rs;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public String joinQuery(String key, String id) {

        //System.out.println(key);
        String value = null;
        String sql;
        if (key == "department") {
            sql = " select * from person," + key + " where person." + key + " = " + key + ".id and person.id = " + id + " ;";
        } else {
            sql = " select * from person," + key + " where person." + key + " = " + key + ".code and person.id = " + id + " ;";
        }
        Connection connection = getConn();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                if (key == "department") {
                    value = resultSet.getString("department.name");
                    //System.out.println(value);
                } else {
                    value = resultSet.getString("description");
                    //System.out.println(value);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(connection, statement, resultSet);
        }
        return value;
    }

    @Override
    public void changePasswd(String passwd, int id) {
        Connection connection = JDBCUtil.getConn();
        System.out.println(id);
        String sql = "update person set passwd = ? where id = ?";
        try {
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setString(1, passwd);
            psmt.setInt(2, id);
            psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int count(String sql) {
        try {
            int i = 1;
            Connection connection = JDBCUtil.getConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from personnek;");
            while (rs.next()) {
                i++;
            }
            return i;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }

    @Override
    public int executeUpdate(String sql) {
        int count = 0;
        Statement stmt = null;

        Connection connection = getConn();
        try {
            stmt = connection.createStatement();
            count = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            release(connection, stmt);
        }

        return count;

    }

    @Override
    public void deletePerson(String id) {
        Connection connection = null;
        Statement statement = null;
        String sql = "delete from person where id = " + id + " ; ";

        try {
            connection = JDBCUtil.getConn();
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertLog(String description, String time) {
        Connection connection = JDBCUtil.getConn();
        String sql = "insert into log values (null, ?, ?)";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, time);
            preparedStatement.execute();

        }catch(Exception e){
            e.printStackTrace();
        }


    }

}
