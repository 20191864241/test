package class1;

import com.entity.Emp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestJDBC1 {
    //查询
    public static void testQuery(Statement stat) {
        //  4.执行sql语句，返回结果
        try {
            String sql = "select  * from emp";
            ResultSet resultSet = stat.executeQuery(sql);
            List<Emp> list = new ArrayList<>();
            while(resultSet.next()){
                int empno = resultSet.getInt(1);
                String ename = resultSet.getString(2);
                String job = resultSet.getString(3);
                int mgr = resultSet.getInt(4);
                Date hiredate = resultSet.getDate(5);
                double sal = resultSet.getDouble(6);
                double comm = resultSet.getDouble(7);
                int deptno = resultSet.getInt(8);
                Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
                list.add(emp);
                System.out.println(list);//会遍历list中的每个Emp对象，并调用每个Emp对象的toString方法来获取其字符串表示形式。
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
