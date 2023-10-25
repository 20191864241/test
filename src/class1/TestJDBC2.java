package class1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.LinkedList;

public class TestJDBC2 {
    public static void testInjection(Connection conn){
        PreparedStatement p = null;

//        String sql = "select * from account where id = '" +true+ "' and password = '" +true+ "'";
        String sql = "insert into user(uid,username,password) values(default,?,?)";
        LinkedList<Savepoint> savePoints = new LinkedList<>();
        try {
            p = conn.prepareStatement(sql);
            conn.setAutoCommit(false);//手动设置回滚点
            for (int i = 0; i < 1000; i++) {
                p.setString(1, "lili");
                p.setInt(2,123);
                p.addBatch();//将修改放入一个批次中
                if(i%100==0){
                    p.executeBatch();
                    p.clearBatch();//   清除批处理中的数据

                    //设置回滚点
                    Savepoint savepoint = conn.setSavepoint();
                    savePoints.addLast(savepoint);
                }
                if(i==900){int j = 1/0;}
            }
            p.executeBatch();// 执行剩余的数据
        } catch (SQLException e) {
            //选择回滚点
            Savepoint savepoint = null;
            if(savePoints.size()>0){savepoint = savePoints.removeLast();}
            try {
                conn.rollback(savepoint);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
