package com.example.ruanjian.mapper;

import com.example.ruanjian.beans.SessionBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SessionMapper {
    List<SessionBean> selectIs();//查询项目是否分配
    int deleteep(SessionBean sessionBean);//删除记录
    int insertep(SessionBean sessionBean);//插入项目分配
    List<SessionBean> selectpid(int pid);//通过项目id查询
    List<SessionBean> selecteid(int eid);//通过员工id查询
    List<SessionBean> selsectIs1();//查询项目是否接受
    List<SessionBean> selectIs2();//查询项目是否拒绝
    int updateaccecpt(SessionBean sessionBean);//接受
    int updaterefuse(SessionBean sessionBean);//拒绝
    List<SessionBean> selectdeBypid(SessionBean sessionBean);//查询一个部门内接受项目
    List<SessionBean> selectrefuse(SessionBean sessionBean);//查询拒绝的
}
