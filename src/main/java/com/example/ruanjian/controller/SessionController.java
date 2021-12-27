package com.example.ruanjian.controller;

import com.example.ruanjian.beans.SessionBean;
import com.example.ruanjian.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Repository
public class SessionController {
    @Autowired
    SessionService sessionService;
    @RequestMapping("/sesionseis")
    @ResponseBody
    public List<SessionBean> selectIs(HttpSession session) {

        List<SessionBean> list = sessionService.selectIs();
        return list;
    }
    @RequestMapping("/sesionseis1")//接受
    @ResponseBody
    public List<SessionBean> selectIs1(){
        List<SessionBean> list = sessionService.selsectIs1();
        return list;
    }
    @RequestMapping("/seseionseis2")//拒绝
    @ResponseBody
    public List<SessionBean> selectIs2(){
        List<SessionBean> list = sessionService.selectIs2();
        return list;
    }
    @RequestMapping("/sesionsepid")
    @ResponseBody
    public List<SessionBean> selectpid(){
        int pid = 1;
        List<SessionBean> list = sessionService.selectpid(pid);
        return list;
    }
    @RequestMapping("/sesionseeid")
    @ResponseBody
    public List<SessionBean> selecteid(){
        int eid = 1;
        List<SessionBean> list = sessionService.selecteid(eid);
        return list;
    }
    @RequestMapping("/sesionde")
    @ResponseBody
    public String deleteep(SessionBean sessionBean){
        sessionBean.setPid(1);
        sessionBean.setSend(2);
        sessionBean.setReceive(1);
        sessionBean.setSid(1);
        sessionBean.setState("未接受");
        sessionService.deleteep(sessionBean);
        return "successful";
    }
    @RequestMapping("/sesionin")
    @ResponseBody
    public String insertep(SessionBean sessionBean){
        sessionBean.setPid(1);
        sessionBean.setSend(2);
        sessionBean.setReceive(2);
        sessionBean.setSid(2);
        sessionBean.setState("未接受");
        sessionService.insertep(sessionBean);
        return "successful";
    }

}
