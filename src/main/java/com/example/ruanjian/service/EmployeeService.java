package com.example.ruanjian.service;

import com.example.ruanjian.beans.EmployeeBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Repository
public interface EmployeeService {
    List<EmployeeBean> selectall();//查询所有项目
    int delete(String  eid);//通过id删除
    EmployeeBean selectByname(EmployeeBean employeeBean);//通过姓名查找
    List<EmployeeBean> selectBydepartment(String department);//通过部门查找
    int updateByid(EmployeeBean employeeBean);//修改员工通过id
    int inser(EmployeeBean employeeBean);//增加员工
    EmployeeBean selectByid(int id);//通过工号查找
}
