package com.example.ruanjian.controller;

import com.example.ruanjian.beans.*;
import com.example.ruanjian.service.ApportionService;
import com.example.ruanjian.service.EmployeeService;
import com.example.ruanjian.service.ProjectService;
import com.example.ruanjian.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ApportionController {
    @Autowired
    ApportionService apportionService;
    @RequestMapping("/aselectByid")
    @ResponseBody
    public Apportion selectBypid(int pid){
        Apportion apportion = apportionService.selectBypid(pid);
        return apportion;
    }
    @Autowired
    SessionService sessionService;
    @RequestMapping("/aupdate")
    @ResponseBody
    public String update(@RequestBody SessionBean s, HttpSession session){
        EmployeeBean bean = (EmployeeBean) session.getAttribute("user");
        String department = bean.getDepartment();
        Apportion apportion = apportionService.selectBypid(s.getPid());
        apportion.setpId(s.getPid());
        sessionService.insertep(s);
        if(Objects.equals(department, "模型部门")){
            apportion.setmState("已分配");
        }
        if(Objects.equals(department, "渲染部门")){
            apportion.setaState("已分配");
        }
        if(Objects.equals(department, "后期部门")){
            apportion.setlState("已分配");
        }
        apportionService.update(apportion);
        return "successful";
    }
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProjectService projectService;
    @RequestMapping("/aselect")
    @ResponseBody
    public List<ProjectBean> selectall(HttpSession session){
        List<Apportion> list;
        List<ProjectBean> l = new ArrayList<>();
        EmployeeBean bean = (EmployeeBean) session.getAttribute("user");
        String d = bean.getDepartment();
        if(Objects.equals(d, "渲染部门")){
        list= apportionService.selectalla();
            for (Apportion a :
                    list) {
                ProjectBean bean1 = projectService.selectProjectByPid(a.getpId());
                l.add(bean1);
            }
        }
        if(Objects.equals(d, "模型部门")){
            list= apportionService.selectallm();
            for (Apportion a :
                    list) {
                ProjectBean bean1 = projectService.selectProjectByPid(a.getpId());
                l.add(bean1);
            }
        }
        if (Objects.equals(d, "后期部门")){
            list = apportionService.selectalll();
            for (Apportion a :
                    list) {
                ProjectBean bean1 = projectService.selectProjectByPid(a.getpId());
                l.add(bean1);
            }
        }
        return l;

    }
    @RequestMapping("/ainsert")
    @ResponseBody
    public String insert(Apportion apportion){
        String s=apportionService.insert(apportion);
        return s;
    }
}
