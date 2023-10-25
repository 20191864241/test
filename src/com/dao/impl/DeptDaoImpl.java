package com.dao.impl;


import com.dao.DeptDao;
import com.dao.BaseDao;
import com.entity.Dept;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl extends BaseDao implements DeptDao {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/java?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&rewriteBatchedStatements=true";
    private static String user = "root";
    private static String password = "4141941sb941";
    private static PreparedStatement p = null;
    @Override
    public List<Dept> findAll(){
        String sql = "select * from dept";
        return basequery(Dept.class,sql);
    }

    @Override
    public int addDept(Dept dept) {
        String sql = "insert into dept values(?,?,?)";
        return baseUpdate(sql, dept.getDeptno(), dept.getDname(), dept.getLoc());
    }
}
