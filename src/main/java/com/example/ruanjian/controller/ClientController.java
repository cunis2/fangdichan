package com.example.ruanjian.controller;

import com.alibaba.excel.EasyExcel;
import com.example.ruanjian.beans.ClientBean;
import com.example.ruanjian.beans.ClientBean;
import com.example.ruanjian.beans.Unit;
import com.example.ruanjian.mapper.ClientMapper;
import com.example.ruanjian.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    @RequestMapping("/tiaozhuan")
    public String tianzhuan(){
        return "client";
    }
    @RequestMapping("/cadd")
    @ResponseBody
    public String in(@RequestBody ClientBean clientBean){
        clientService.addUser(clientBean);
        return "successful";
    }
    @RequestMapping("/cdelete")
    @ResponseBody
    public  String de(@RequestBody String cid){
        System.out.println(cid);
        clientService.deleteUser(cid);
        return "successful";
    }
    @RequestMapping("/cselect")
    @ResponseBody
    public List<ClientBean> queryUserlist(){
        List<ClientBean> list = clientService.queryUserList();
        return list;
    }
    @RequestMapping("/cselectByUnit")
    @ResponseBody
    public List<ClientBean> un(@RequestBody Unit unit){
        System.out.println(unit);
        List<ClientBean> list = clientService.queryUserByunit(unit);
        for (ClientBean c:
                list
        ) {
            System.out.println(c);
        }
        return list;
    }
    @RequestMapping("/cdc")
    @ResponseBody
    public String cdc(){
        EasyExcel.write("导出员工信息.xlsx",ClientBean.class).sheet().doWrite(queryUserlist());
        return "successful";
    }
    @RequestMapping("/cselectBy")
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
    @RequestMapping("/cad")
    @ResponseBody
    public String addUser(){
        clientService.addUser(new ClientBean(11,"123","123","456","456498","789312","12346","312","641"));
        return "successful";
    }
    @RequestMapping("/cupdata")
    @ResponseBody
    public String updataUser(){
        clientService.updateUser(new ClientBean(1,"123","123","456","456498","789312","12346","312","648"));
        return "successful";
    }

    @RequestMapping("/cdelet")
    @ResponseBody
    public String deleteUser(@RequestBody String cid){
        System.out.println(cid);
        clientService.deleteUser(cid);
        return "successful";
    }


}
