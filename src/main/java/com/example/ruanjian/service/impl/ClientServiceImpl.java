package com.example.ruanjian.service.impl;

import com.example.ruanjian.beans.Unit;
import com.example.ruanjian.service.ClientService;
import com.example.ruanjian.beans.ClientBean;
import com.example.ruanjian.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientMapper clientMapper;

    @Override
    public List<ClientBean> queryUserByunit(Unit unit){
        List<ClientBean> clientBeans = clientMapper.queryUserByunit(unit);
        return clientBeans;
    }

    @Override
    public List<ClientBean> queryUserByunit4(Unit unit) {
        List<ClientBean> clientBeans = clientMapper.queryUserByunit4(unit);
        return clientBeans;
    }

    @Override
    public List<ClientBean> queryUserList() {
        List<ClientBean> list = clientMapper.queryUserList();
        return list;
    }

    @Override
    public ClientBean queryUserById(int cid) {
        ClientBean bean = clientMapper.queryUserById(cid);
        return bean;
    }


    @Override
    public String deleteUser(String cid) {
        clientMapper.deleteUser(cid);
        return "successful";
    }

    @Override
    public int updateUser(ClientBean clientBean) {
        clientMapper.updateUser(clientBean);
        return 1;
    }

    @Override
    public int addUser(ClientBean clientBean) {
        clientMapper.addUser(clientBean);
        return 1;
    }

    @Override
    public ClientBean queryUserByname(String name) {
        ClientBean bean = clientMapper.queryUserByname(name);
        return bean;
    }
}
