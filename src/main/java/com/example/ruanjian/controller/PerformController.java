package com.example.ruanjian.controller;

import com.example.ruanjian.beans.EmployeeBean;
import com.example.ruanjian.beans.PerformBean;
import com.example.ruanjian.beans.ProjectBean;
import com.example.ruanjian.service.EmployeeService;
import com.example.ruanjian.service.PerformService;
import com.example.ruanjian.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class PerformController {
    @Autowired
    PerformService performService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProjectService projectService;
    @RequestMapping("/perform")
    public String perform(){
        return "performA";
    }
    @RequestMapping("/updateeva")
    @ResponseBody
    public String evaluateupdate(@RequestBody PerformBean performBean){
            performService.update(performBean);
            return "successful";
    }
    @RequestMapping("/selectownp")
    @ResponseBody
    public List<ProjectBean> selectownp(@RequestBody String eid){
        List<PerformBean> list = performService.selectByeid(Integer.parseInt(eid));
        List<ProjectBean> l = new ArrayList<>();
        for (PerformBean p :
                list) {
            int i = p.getpId();
            ProjectBean projectBean = projectService.selectProjectByPid(i);
            l.add(projectBean);
        }
        return l;
    }
    @RequestMapping("/eevaluate")
    @ResponseBody
    public String empevaluate(@RequestBody EmployeeBean employeeBean){
        EmployeeBean bean = employeeService.selectByid(employeeBean.getEid());
        bean.setEvaluate(employeeBean.getEvaluate());
        employeeService.updateByid(bean);
        return "successful";
    }
    @RequestMapping("/pselecta")
    @ResponseBody
    public List<EmployeeBean> selectall(HttpSession session){
        EmployeeBean employeeBean = (EmployeeBean) session.getAttribute("user");
        String duty = employeeBean.getDuty();
        String department = employeeBean.getDepartment();
        List<EmployeeBean> employeeBeans;
        if(Objects.equals(duty, "老板")){
            employeeBeans = employeeService.selectall();
        }
        else{
            employeeBeans = employeeService.selectBydepartment(department);
        }


        return employeeBeans;
    }
    @RequestMapping("/pselectByeid")
    @ResponseBody
    public String selectByeid(){
        PerformBean performBean = new PerformBean();
        performBean.seteId(1);
        performBean.setId(1);
        performBean.seteId(2);
        performBean.setEvaluate("通过");
        performBean.setSuggestion("过");
        performService.selectByeid(performBean.geteId());
        return "successful";
    }
    @RequestMapping("/pselectBypid")
    @ResponseBody
    public String slectBypid(int pid){
        performService.selectBypid(pid);
        return "success";
    }
    @RequestMapping("/pdelete")
    @ResponseBody
    public String delete(){
        PerformBean performBean = new PerformBean();
        performBean.seteId(1);
        performBean.setId(1);
        performBean.seteId(1);
        performBean.setEvaluate("通过");
        performBean.setSuggestion("过");
        performService.deleteBypid(performBean);
        return "success";
    }
    @RequestMapping("/pinsert")
    @ResponseBody
    public String insert(){
        PerformBean performBean = new PerformBean();
        performBean.seteId(1);
        performBean.setId(1);
        performBean.setpId(2);
        performBean.setEvaluate("通过");
        performBean.setSuggestion("过");
        performService.insert(performBean);
        return "success";
    }
}
