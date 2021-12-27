package com.example.ruanjian.service.impl;

import com.example.ruanjian.beans.EmployeeBean;
import com.example.ruanjian.mapper.EmployeeMapper;
import com.example.ruanjian.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public List<EmployeeBean> selectall() {
        List<EmployeeBean> list=employeeMapper.selectall();
        return list;
    }

    @Override
    public int delete(String eid) {
        employeeMapper.delete(eid);
        return 1;
    }

    @Override
    public EmployeeBean selectByname(EmployeeBean employeeBean) {
        EmployeeBean bean = employeeMapper.selectByname(employeeBean);
        return bean;
    }

    @Override
    public List<EmployeeBean> selectBydepartment(String department) {
        List<EmployeeBean> list = employeeMapper.selectBydepartment(department);
        return list;
    }

    @Override
    public int updateByid(EmployeeBean employeeBean) {
        employeeMapper.updateByid(employeeBean);
        return 1;
    }

    @Override
    public EmployeeBean selectByid(int id) {
        EmployeeBean bean = employeeMapper.selectByid(id);
        return bean;
    }

    @Override
    public int inser(EmployeeBean employeeBean) {
        employeeMapper.insert(employeeBean);
        return 1;
    }
}
