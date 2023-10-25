package com.dao;

import com.entity.Emp;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/java?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&rewriteBatchedStatements=true";
    private static String user = "root";
    private static String password = "4141941sb941";
    private static PreparedStatement p = null;
    public int baseUpdate(String sql, Object ... args){
        int rows = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            p = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                p.setObject(i+1, args[i]);
            }
            //执行CURD
            rows = p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != p) {
                try {
                    p.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return rows;
    }

    public List basequery(Class clazz, String sql, Object ... args){
        int rows = 0;
        List list = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            p = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                p.setObject(i+1, args[i]);
            }
            //执行CURD
            resultSet = p.executeQuery();
            list = new ArrayList<>();
            //  获取字节码所有属性
            Field[] fields = clazz.getDeclaredFields();
            for(Field field: fields){
                field.setAccessible(true);
            }
            while(resultSet.next()){
                //  通过反射创建对象
                Object obj = clazz.newInstance();//默认在通过反射调用对象的空参构造方法
                for(Field field : fields){
                    String fieldName = field.getName();
                    Object data = resultSet.getObject(fieldName);
                    field.set(obj, data);
                }
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != p) {
                try {
                    p.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }
}
