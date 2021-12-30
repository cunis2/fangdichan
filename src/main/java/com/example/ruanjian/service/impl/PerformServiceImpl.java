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
    public List<PerformBean> selectByeid(int eid) {
        List<PerformBean> list = performMapper.selectByeid(eid);
        return list;
    }

    @Override
    public List<PerformBean> selectBypid(int pid) {
        List<PerformBean> list = performMapper.selectBypid(pid);
        return list;
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

    @Override
    public int update(PerformBean performBean) {
        performMapper.update(performBean);
        return 1;
    }

    @Override
    public int updatesuggesstion(PerformBean performBean) {
        performMapper.updatesuggesstion(performBean);
        return 1;
    }

    @Override
    public int updateallsugg(PerformBean performBean) {
        performMapper.updateallsugg(performBean);
        return 1;
    }
}
