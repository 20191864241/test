package com.dao;

import com.entity.Dept;

import java.util.List;

public interface DeptDao {
    /**
     * 查询所有部门信息
     * @return 返回所有部门信息
     */
    List<Dept> findAll();

    /**
     * 增加部门信息
     * @param dept
     * @return 成功返回大于0的数，失败返回0
     */
    int addDept(Dept dept);
}
