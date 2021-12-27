package com.example.ruanjian.service;

import com.example.ruanjian.beans.PerformBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Repository
public interface PerformService {
    List<PerformBean> selectall();//查询全部
    PerformBean selectByeid(PerformBean performBean);//通过员工查询
    PerformBean selectBypid(PerformBean performBean);//通过项目查询
    int deleteBypid(PerformBean performBean);//删除
    int insert(PerformBean performBean);//插入
}