package com.example.ruanjian.service;

import com.example.ruanjian.beans.Apportion;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface ApportionService {
    public Apportion selectBypid(int pid);//查询
    public String update(Apportion apportion);//修改
    public List<Apportion> selectallm();//建模未分配查询
    public List<Apportion> selectalla();//渲染未分配查询
    public List<Apportion> selectalll();//后期未分配查询
    public String insert(Apportion apportion);//插入

}
