package com.example.ruanjian.controller;

import com.example.ruanjian.beans.ClientBean;
import com.example.ruanjian.beans.Unit;
import com.example.ruanjian.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

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
        List<ClientBean> list = clientService.queryUserByunit4(unit);
        for (ClientBean c:
                list
             ) {
            System.out.println(c);
        }

        return list;
    }
    @RequestMapping("/cselectByUnit4")
    @ResponseBody
    public List<ClientBean> un4(@RequestBody Unit unit){

        List<ClientBean> list =new LinkedList<ClientBean>();
                list=clientService.queryUserByunit(unit);
       String s;
        int e[] = new int [list.size()];
        for(int i=0;i<list.size();i++)
        {
            e[i]=-1;
        }
        int i=0,l=0,j=0;
            for (ClientBean c :
                    list
            ) {
                j=0;
                s = c.getUnit4();
                for (ClientBean d : list
                ) {
                    if(d.getUnit4().equals(s)&&i!=j){
                        e[l]=j;
                        l++;
                    }
                    j++;
                }
                i++;
            }
        int k=0;
            j=0;
            int p=0;
            for(i=0;i<list.size();i++){
                if(e[i]==-1){
                    break;
                }
                for(j=i+1;j<list.size();j++){
                    if(e[j]<e[i]){
                        p=e[i];
                        e[i]=e[j];
                        e[j]=p;

                    }
                }
            }
        for(i=0;i<list.size();i++)
        {
            if(e[i]==-1)
            {
                break;
            }
            for(j=i+1;j<list.size();j++)
            {
                if(e[i]==e[j])
                {
                    e[j]=999;
                }
            }
        }
        for(i=0;i<list.size();i++)
        {
            if(e[i]==-1)
            {
                break;
            }
            if(e[i]!=999){
            list.remove(e[i]);
            for(k=0;k<list.size();k++) {
                if (e[k] != -1&&e[k]!=999) {

                    e[k]--;
                }
            }
            }
        }
        for (ClientBean c:list
             ) {
            System.out.println(c);
        }
        return list;
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
