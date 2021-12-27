package com.example.ruanjian.mapper;

import com.example.ruanjian.beans.EmployeeBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {
    List<EmployeeBean> selectall();//查询所有员工
    int delete(String eid);//通过id删除员工
    EmployeeBean selectByname(EmployeeBean employeeBean);//通过姓名查找
    List<EmployeeBean> selectBydepartment(String department);//通过部门查找
    int updateByid(EmployeeBean employeeBean);//修改员工通过id
    int insert(EmployeeBean employeeBean);//增加员工
    EmployeeBean selectByid(int id);//通过工号查找

}
