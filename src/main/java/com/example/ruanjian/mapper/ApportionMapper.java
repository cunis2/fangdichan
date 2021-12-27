package com.example.ruanjian.mapper;

import com.example.ruanjian.beans.Apportion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApportionMapper {
    public Apportion selectBypid(int pid);//查询
    public void update(Apportion apportion);//修改
    public List<Apportion> selectallm();//建模未分配查询
    public List<Apportion> selectalla();//渲染未分配查询
    public List<Apportion> selectalll();//后期未分配查询
    public void insert(Apportion apportion);//插入

}
