package com.dao;

import com.entity.Emp;

import java.util.List;

public interface EmpDao {
    /**
     * 增加员工信息
     * @param emp 员工对象
     * @return 成功返回大于0的数，失败返回0
     */
    int addEmp(Emp emp);


    /**
     * 根据员工编号删除员工信息
     * @param empno 要删除的员工编号
     * @return  成功返回大于0的数，失败返回0
     */
    int deleteEmp(int empno);


    /**
     * 根据员工编号修改员工其他所有字段
     * @param emp 员工对象
     * @return  成功返回大于0的数，失败返回0
     */
    int updateEmp(Emp emp);
    /**
     * 查询所有员工信息
     * @return 返回所有员工信息
     */
    List<Emp> findAll();
}
