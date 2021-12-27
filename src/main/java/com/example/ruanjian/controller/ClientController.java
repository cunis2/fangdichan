package com.example.ruanjian.controller;

import com.example.ruanjian.beans.ClientBean;
import com.example.ruanjian.beans.ClientBean;
import com.example.ruanjian.mapper.ClientMapper;
import com.example.ruanjian.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    @RequestMapping("/cselect")
    @ResponseBody
    public List<ClientBean> queryUserlist(){
        List<ClientBean> list = clientService.queryUserList();
        return list;
    }
    @RequestMapping("/cselectByid")
    @ResponseBody
    public ClientBean queryUserById(){
        int cid ;
        cid =1;
        ClientBean clientBean = clientService.queryUserById(cid);
        return clientBean;

    }
    @RequestMapping("/cselectByname")
    @ResponseBody
    public ClientBean querUerByname( String name){
        ClientBean bean = clientService.queryUserByname(name);
        System.out.println(bean);
        return bean;

    }
    @RequestMapping("/cadd")
    @ResponseBody
    public String addUser(){
        clientService.addUser(new ClientBean(11,"123","123","456","456498","789312","12346","312"));
        return "successful";
    }
    @RequestMapping("/cupdata")
    @ResponseBody
    public String updataUser(){
        clientService.updateUser(new ClientBean(1,"123","123","456","456498","789312","12346","312"));
        return "successful";
    }
    @RequestMapping("/cdelete")
    @ResponseBody
    public String deleteUser(){
        clientService.deleteUser("5");
        return "successful";
    }


}
