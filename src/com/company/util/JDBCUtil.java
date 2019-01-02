package com.company.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    static String driverClass = null;
    static String url = null;
    static String name = null;
    static String password = null;

    static {
        try {
            //创建一个属性配置对象
            Properties properties = new Properties();
            InputStream is = new FileInputStream("C:\\Users\\18451\\Desktop\\databaseDesign\\src\\jdbc.properties");
            //使用类加载器：
            //InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //导入输入流
            properties.load(is);

            //读取属性
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     * @param conn
     * @param st
     * @param rs
     */
    public static void release(Connection conn, Statement st, ResultSet rs) {
        closeConn(conn);
        closeRs(rs);
        closeSt(st);
    }
    public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
        closeConn(conn);
        closeRs(rs);
        closePs(ps);
    }

    public static void release(Connection conn, Statement st){
        closeConn(conn);
        closeSt(st);
    }

    private static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }
    }
    private static void closePs(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps = null;
        }
    }
    private static void closeSt(Statement st) {
        try{
            if(st != null){
                st.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            st = null;
        }
    }
    private static void closeConn(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            conn = null;
        }
    }
}
