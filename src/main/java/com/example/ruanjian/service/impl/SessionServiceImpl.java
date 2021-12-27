package com.example.ruanjian.service.impl;

import com.example.ruanjian.beans.SessionBean;
import com.example.ruanjian.mapper.SessionMapper;
import com.example.ruanjian.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionMapper sessionMapper;

    @Override
    public List<SessionBean> selectIs() {
        List<SessionBean> list = sessionMapper.selectIs();
        return list;
    }

    @Override
    public int deleteep(SessionBean sessionBean) {
        sessionMapper.deleteep(sessionBean);
        return 1;
    }

    @Override
    public int insertep(SessionBean sessionBean) {
        sessionMapper.insertep(sessionBean);
        return 1;
    }

    @Override
    public List<SessionBean> selectpid(int pid) {
        List<SessionBean> list = sessionMapper.selectpid(pid);
        return list;
    }

    @Override
    public List<SessionBean> selecteid(int eid) {
        List<SessionBean> list = sessionMapper.selecteid(eid);
        return list;
    }

    @Override
    public List<SessionBean> selsectIs1() {
        List<SessionBean> list = sessionMapper.selsectIs1();
        return list;
    }

    @Override
    public List<SessionBean> selectIs2() {
        List<SessionBean> list = sessionMapper.selectIs2();
        return list;
    }

    @Override
    public int updateaccecpt(SessionBean sessionBean) {
        sessionMapper.updateaccecpt(sessionBean);
        return 1;
    }

    @Override
    public int updaterefuse(SessionBean sessionBean) {
        sessionMapper.updaterefuse(sessionBean);
        return 1;
    }
}
