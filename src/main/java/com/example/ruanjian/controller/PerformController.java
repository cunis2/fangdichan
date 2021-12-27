package com.example.ruanjian.controller;

import com.example.ruanjian.beans.PerformBean;
import com.example.ruanjian.service.PerformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PerformController {
    @Autowired
    PerformService performService;
    @RequestMapping("/pselecta")
    @ResponseBody
    public List<PerformBean> selectall(){
        List<PerformBean> list = performService.selectall();
        return list;
    }
    @RequestMapping("/pselectByeid")
    @ResponseBody
    public String selectByeid(){
        PerformBean performBean = new PerformBean();
        performBean.setEid(1);
        performBean.setId(1);
        performBean.setPid(2);
        performBean.setEvaluate("通过");
        performBean.setSuggestion("过");
        performService.selectByeid(performBean);
        return "successful";
    }
    @RequestMapping("/pselectBypid")
    @ResponseBody
    public String slectBypid(){
        PerformBean performBean = new PerformBean();
        performBean.setEid(1);
        performBean.setId(1);
        performBean.setPid(2);
        performBean.setEvaluate("通过");
        performBean.setSuggestion("过");
        performService.selectBypid(performBean);
        return "success";
    }
    @RequestMapping("/pdelete")
    @ResponseBody
    public String delete(){
        PerformBean performBean = new PerformBean();
        performBean.setEid(1);
        performBean.setId(1);
        performBean.setPid(1);
        performBean.setEvaluate("通过");
        performBean.setSuggestion("过");
        performService.deleteBypid(performBean);
        return "success";
    }
    @RequestMapping("/pinsert")
    @ResponseBody
    public String insert(){
        PerformBean performBean = new PerformBean();
        performBean.setEid(1);
        performBean.setId(1);
        performBean.setPid(2);
        performBean.setEvaluate("通过");
        performBean.setSuggestion("过");
        performService.insert(performBean);
        return "success";
    }
}
