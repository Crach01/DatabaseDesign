package com.company.Dao;

import java.sql.ResultSet;

/**
 * Dao模式定义访问规则
 */
public interface UserDao {
    /**
     * 返回Boolean类型，判断登陆成功或失败
     *
     * @return true: 登陆成功 false ： 登陆失败
     */
    boolean login(String userName, String password, int flag);
    /**
     *  查询所有信息
     */

    /**
     * 查询
     */
    ResultSet executeQuery(String sql);

    /**
     * 连接查询
     */
    String joinQuery(String key, String id);

    /**
     * 修改密码
     */
    void changePasswd(String passwd, int id);

    /**
     * 查询记录数量
     */
    int count(String sql);

    /**
     * 更新信息
     */
    int executeUpdate(String sql);

    /**
     * 删除信息
     */
    void deletePerson(String id);

    /**
     *
     * @param description 日志描述
     * @param time 插入日志时间
     */
    void insertLog(String description, String time);
}
