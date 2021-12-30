package com.example.ruanjian.mapper;

import com.example.ruanjian.beans.ClientBean;
import com.example.ruanjian.beans.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import  java.util.List;
@Mapper
@Repository
public interface ClientMapper {

    List<ClientBean> queryUserList();

    ClientBean queryUserById(int cid);

    int addUser(ClientBean user);

    int updateUser(ClientBean user);

    int deleteUser(String cid);

    ClientBean queryUserByname(String name);

    List<ClientBean> queryUserByunit(Unit unit);

}
