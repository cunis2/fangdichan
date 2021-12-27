package com.example.ruanjian.service.impl;

import com.example.ruanjian.beans.Apportion;
import com.example.ruanjian.mapper.ApportionMapper;
import com.example.ruanjian.service.ApportionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApportionServiceImpl implements ApportionService {
    @Autowired
    ApportionMapper apportionMapper;
    @Override
    public Apportion selectBypid(int pid) {
        Apportion apportion = apportionMapper.selectBypid(pid);
        return apportion;
    }

    @Override
    public String update(Apportion apportion) {
        apportionMapper.update(apportion);
        return "successful";
    }

    @Override
    public List<Apportion> selectallm() {
        List<Apportion> list = apportionMapper.selectallm();
        return list;
    }

    @Override
    public List<Apportion> selectalla() {
        List<Apportion> list = apportionMapper.selectalla();
        return list;
    }

    @Override
    public List<Apportion> selectalll() {
        List<Apportion> list = apportionMapper.selectalll();
        return list;
    }

    @Override
    public String insert(Apportion apportion) {
        apportionMapper.insert(apportion);
        return "successful";
    }
}
