package com.view;

import com.dao.DeptDao;
import com.dao.EmpDao;
import com.dao.impl.DeptDaoImpl;
import com.dao.impl.EmpDaoImpl;
import com.entity.Dept;
import com.entity.Emp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmpManageSystem {
    private static Scanner sc = new Scanner(System.in);
    private static EmpDao emp = new EmpDaoImpl();;
    private static DeptDao dept = new DeptDaoImpl();;
    public static void main(String[] args) {
        showMenu();
    }
    public static void showMenu(){
        while(true){
            System.out.println("**********************");
            System.out.println("* 1.查看所有员工信息");
            System.out.println("* 2.查看所有部门信息");
            System.out.println("* 3.根据工号删除员工信息");
            System.out.println("* 4.根据工号修改员工信息");
            System.out.println("* 5.增加员工信息");
            System.out.println("* 6.增加部门信息");
            System.out.println("* 7.退出");
            System.out.println("***********************");
            System.out.println("请输入选项");
            int num = sc.nextInt();
            switch (num){
                case 1:
                    case1();
                    break;
                case 2:
                    case2();
                    break;
                case 3:
                    case3();
                    break;
                case 4:
                    case4();
                    break;
                case 5:
                    case5();
                    break;
                case 6:
                    case6();
                    break;
                case 7:System.exit(0);break;
                default:System.out.println("请重新输入");break;
            }
        }
    }
    private static void case1() {
        List<Emp> list = emp.findAll();
        for(Emp o:list){
            System.out.println(o);
        }
    }
    private static void case2() {
        DeptDao dept = new DeptDaoImpl();
        List<Dept> list = dept.findAll();
        for(Dept o:list) {
            System.out.println(o);
        }
    }
    private static void case3() {
        System.out.println("请输入要删除的员工工号");
        int empno = sc.nextInt();
        int cur = emp.deleteEmp(empno);
        if(cur>0){
            System.out.println("删除成功");
        }else {
            System.out.println("未发现员工工号");
        }
    }
    private static void case4() {
        System.out.println("请输入要修改的员工工号");
        int empno = sc.nextInt();
        System.out.println("请输入修改后的员工姓名");
        String ename = sc.next();
        System.out.println("请输入修改后的员工岗位");
        String job = sc.next();
        System.out.println("请输入修改后的员工mgr");
        int mgr = sc.nextInt();
        System.out.println("请输入修改后的入职日期,按yyyy-MM-dd输入");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String hiredate = sc.next();
        Date utilDate = null;
        try {
            utilDate = dateFormat.parse(hiredate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println("请输入修改后的员工工资");
        double sal = sc.nextDouble();
        System.out.println("请输入修改后的员工补贴");
        double comm = sc.nextDouble();
        System.out.println("请输入修改后的部门编号");
        int deptno = sc.nextInt();
        Emp emp1 = new Emp(empno, ename, job, mgr, sqlDate, sal, comm, deptno);
        int cur = emp.updateEmp(emp1);
        if(cur>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }

    }
    private static void case5() {
        System.out.println("请输入员工编号");
        int empno = sc.nextInt();
        System.out.println("请输入员工姓名");
        String ename = sc.next();
        System.out.println("请输入员工岗位");
        String job = sc.next();
        System.out.println("请输入员工mgr");
        int mgr = sc.nextInt();
        System.out.println("请输入入职日期,按yyyy-MM-dd输入");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String hiredate = sc.next();
        Date utilDate = null;
        try {
            utilDate = dateFormat.parse(hiredate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println("请输入员工工资");
        double sal = sc.nextDouble();
        System.out.println("请输入员工补贴");
        double comm = sc.nextDouble();
        System.out.println("请输入部门编号");
        int deptno = sc.nextInt();
        Emp emp1 = new Emp(empno, ename, job, mgr, sqlDate, sal, comm, deptno);
        int cur = emp.addEmp(emp1);
        if(cur>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
    private static void case6() {
        System.out.println("请输入部门编号");
        int empno = sc.nextInt();
        System.out.println("请输入部门名");
        String ename = sc.next();
        System.out.println("请输入公司位置");
        String job = sc.next();
        Dept dept1 = new Dept(empno, ename, job);
        int cur = dept.addDept(dept1);
        if(cur>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
}
