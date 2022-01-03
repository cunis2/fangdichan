package com.example.ruanjian.controller;

import com.example.ruanjian.beans.EmployeeBean;
import com.example.ruanjian.beans.Myperform;
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
    @RequestMapping("/myperform")
    public String myperform(){
        return "Myperform";
    }
    @RequestMapping("/updateeva")
    @ResponseBody
    public String evaluateupdate(@RequestBody PerformBean performBean){
            performService.update(performBean);
            return "successful";
    }
    @RequestMapping("/Myperform")
    @ResponseBody
    public List<Myperform> myperforms(HttpSession session){
        EmployeeBean user = (EmployeeBean) session.getAttribute("user");
        int eid = user.getEid();
        EmployeeBean employeeBean = employeeService.selectByid(eid);
        Myperform myperform = new Myperform();
        myperform.seteId(employeeBean.getEid());
        myperform.setName(employeeBean.getName());
        myperform.setDepartment(employeeBean.getDepartment());
        myperform.setType(employeeBean.getType());
        myperform.setDuty(employeeBean.getDuty());
        myperform.setEvaluate(employeeBean.getEvaluate());
        myperform.setBaseMoney(employeeBean.getbMoney());
        int p = 0;
        List<PerformBean> list = performService.selectByeid(eid);
        for (PerformBean performBean :
                list) {
            p++;
        }
        p=p*1000;
        myperform.setPerformMoney(p);
        myperform.setTotalMoney(p+employeeBean.getbMoney());
        List<Myperform> l = new ArrayList<>();
        l.add(myperform);
        return l;
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
