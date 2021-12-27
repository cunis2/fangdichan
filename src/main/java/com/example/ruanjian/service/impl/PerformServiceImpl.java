package com.example.ruanjian.service.impl;

import com.example.ruanjian.beans.PerformBean;
import com.example.ruanjian.mapper.PerformMapper;
import com.example.ruanjian.service.PerformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PerformServiceImpl implements PerformService {
    @Autowired
    PerformMapper performMapper;
    @Override
    public List<PerformBean> selectall() {
        List<PerformBean> list = performMapper.selectall();
        return list;
    }

    @Override
    public PerformBean selectByeid(PerformBean performBean) {
        PerformBean bean = performMapper.selectByeid(performBean);
        return bean;
    }

    @Override
    public PerformBean selectBypid(PerformBean performBean) {
        PerformBean bean = performMapper.selectBypid(performBean);
        return bean;
    }

    @Override
    public int deleteBypid(PerformBean performBean) {
        performMapper.deleteBypid(performBean);
        return 1;
    }

    @Override
    public int insert(PerformBean performBean) {
        performMapper.insert(performBean);
        return 1;
    }
}
