package com.example.ruanjian.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.example.ruanjian.beans.ClientBean;

import java.util.List;
@Repository
@Service
public interface ClientService {
    List<ClientBean> queryUserList();
    ClientBean queryUserById(int cid);
    String deleteUser(String cid);
    int updateUser(ClientBean clientBean);
    int addUser(ClientBean clientBean);
    ClientBean queryUserByname(String name);
}
