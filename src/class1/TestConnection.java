package class1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class TestConnection {
    public static void main(String[] args) {

        Connection conn = null;
//  3.获得语句对象Statement
        Statement stmt = null;
////  1.加载驱动
//        Driver driver = new com.mysql.jdbc.Driver();
////  2.注册驱动
//        DriverManager.registerDriver(driver);

        try {
//  1.通过反射加载驱动Driver  注册驱动DriverManager
            Class.forName("com.mysql.cj.jdbc.Driver");
//  2.获得连接Connection
            String url = "jdbc:mysql://localhost:3306/java?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&rewriteBatchedStatements=true";
            String user = "root";
            String password = "4141941sb941";
            conn = DriverManager.getConnection(url, user, password);
//  3.获得语句对象Statement
            stmt = conn.createStatement();
//  4.执行sql语句，返回结果
//            class1.TestJDBC1.testQuery(stmt);
            TestJDBC2.testInjection(conn);
        } catch (Exception e) {
            try {
                throw new RuntimeException(e);
            } finally {
//  5.关闭连接
                if (null != stmt) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (null != conn) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        }
    }
}
