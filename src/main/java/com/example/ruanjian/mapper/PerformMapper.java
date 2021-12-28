package com.example.ruanjian.mapper;

import com.example.ruanjian.beans.PerformBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PerformMapper {
    List<PerformBean> selectall();//查询全部
    List<PerformBean> selectByeid(int eid);//通过员工查询
    List<PerformBean> selectBypid(int pid);//通过项目查询
    int deleteBypid(PerformBean performBean);//删除
    int insert(PerformBean performBean);//插入
}
