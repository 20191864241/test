package com.dao.impl;

import com.dao.BaseDao;
import com.dao.EmpDao;
import com.entity.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDaoImpl extends BaseDao implements EmpDao {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet resultSet = null;
    private static int rows;
    private static String url = "jdbc:mysql://localhost:3306/java?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&rewriteBatchedStatements=true";
    private static String user = "root";
    private static String password = "4141941sb941";
    private static PreparedStatement p = null;

    @Override
    public int addEmp(Emp emp) {
        String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
        return baseUpdate(sql,emp.getEmpno(), emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(), emp.getComm(), emp.getDeptno());
    }

    @Override
    public int deleteEmp(int empno) {
        String sql = "delete from emp where empno=?";
        return baseUpdate(sql,empno);

    }

    @Override
    public int updateEmp(Emp emp) {
        String sql = "update emp set ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=? where empno=?";
        return baseUpdate(sql, emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(), emp.getComm(), emp.getDeptno(), emp.getEmpno());
    }

    @Override
    public List<Emp> findAll() {
        String sql = "select  * from emp";
        return basequery(Emp.class, sql);
    }
}
