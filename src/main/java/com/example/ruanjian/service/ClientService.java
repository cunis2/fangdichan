package com.example.ruanjian.service;

import com.example.ruanjian.beans.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.example.ruanjian.beans.ClientBean;

import java.util.List;
@Repository
@Service
public interface ClientService {
    @Autowired
    List<ClientBean> queryUserList();
    ClientBean queryUserById(int cid);
    String deleteUser(String cid);
    int updateUser(ClientBean clientBean);
    int addUser(ClientBean clientBean);
    ClientBean queryUserByname(String name);
    List<ClientBean> queryUserByunit(Unit unit);
    List<ClientBean> queryUserByunit4(Unit unit);
}
