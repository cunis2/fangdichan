package com.example.ruanjian.controller;

import com.example.ruanjian.beans.*;
import com.example.ruanjian.config.FileConfig;
import com.example.ruanjian.service.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    ClientService clientService;
    @Autowired
    ApportionService apportionService;
    @Autowired
    SessionService sessionService;
    @Autowired
    PerformService performService;
    @Autowired
    EmployeeService employeeService;
    @RequestMapping(value = "/select")
    @ResponseBody
    public List<ProjectBean> queryProjectList(){
        List<ProjectBean> list = projectService.queryProjectList();
        return list;
    }
    @RequestMapping("/project")
    public String pro(){
        return "project";
    }
    @RequestMapping("/projectaccept")
    @ResponseBody
    public String accept (@RequestBody String pid,HttpSession session){
        EmployeeBean employeeBean = (EmployeeBean) session.getAttribute("user");
        int eid = employeeBean.getEid();
        SessionBean sessionBean=new SessionBean();
        sessionBean.setPid(Integer.parseInt(pid));
        sessionBean.setReceive(eid);
        sessionBean.setState("接受");
        sessionService.updateaccecpt(sessionBean);
        PerformBean performBean = new PerformBean();
        performBean.setSuggestion(null);
        performBean.setEid(eid);
        performBean.setPid(Integer.parseInt(pid));
        performBean.setEvaluate(null);
        performService.insert(performBean);
        return "successful";
    }
    @RequestMapping("toseeemployee")
    @ResponseBody
    public List<EmployeeBean> selectEmpl(@RequestBody String pid){
        ProjectBean projectBean = projectService.selectProjectByPid(Integer.valueOf(pid));
        List<SessionBean> list = sessionService.selectpid(Integer.parseInt(pid));
        List<EmployeeBean> l = new ArrayList<>();
        for (SessionBean s :
                list) {
            EmployeeBean employeeBean = employeeService.selectByid(s.getReceive());
            l.add(employeeBean);
        }
        return l;
    }
    @RequestMapping("/projectrefuse")
    @ResponseBody
    public String refuse (@RequestBody String pid,HttpSession session){
        EmployeeBean employeeBean = (EmployeeBean) session.getAttribute("user");
        int eid = employeeBean.getEid();
        SessionBean sessionBean=new SessionBean();
        sessionBean.setPid(Integer.parseInt(pid));
        sessionBean.setReceive(eid);
        sessionBean.setState("拒绝");
        sessionService.updateaccecpt(sessionBean);
        return "successful";
    }
    @RequestMapping("/insert")
    @ResponseBody
    public String insertProject(@RequestBody PartProject project){
        ProjectBean projectBean = new ProjectBean();
        projectBean.setType(project.getType());
        projectBean.setState("建模中");
        projectBean.setDataUrl("null");
        projectBean.setaPrinciple("李四");
        projectBean.setmPrinciple("张三");
        projectBean.setlPrinciple("王五");
        projectBean.setbTime(project.getbTime());
        projectBean.seteTime(project.geteTime());
        projectBean.setpName(project.getpName());
        int cid;
        ClientBean bean = clientService.queryUserByname(project.getcName());
        cid=bean.getCid();
        projectBean.setcId(cid);
        projectService.insertProject(projectBean);
        ProjectBean bean1 = projectService.selectByname(project.getpName());
        Apportion apportion = new Apportion();
        apportion.setpId(bean1.getpId());
        apportion.setmState("未分配");
        apportion.setaState("未分配");
        apportion.setlState("未分配");
        apportionService.insert(apportion);
        return "successful";
    }
    @RequestMapping("/selectstate")
    @ResponseBody
    public List<ProjectBean> selectis(HttpSession session){
        EmployeeBean employeeBean = (EmployeeBean) session.getAttribute("user");
        int eid = employeeBean.getEid();
        List<SessionBean> sessionBeans = sessionService.selectIs();
        List<ProjectBean> l =new ArrayList<>();
        for (SessionBean s :
                sessionBeans) {
            if(s.getReceive()==eid) {
                ProjectBean projectBean = projectService.selectProjectByPid(s.getPid());
                l.add(projectBean);
            }
        }
        return l;
    }
    @RequestMapping("/selectByname")
    @ResponseBody
    public ProjectBean selectByname(String name){
        ProjectBean bean = projectService.selectByname(name);
        return bean;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteProjectByPid(){
        int pid=3;
        projectService.deleteProjectByPid(pid);
        return "successful";
    }
    @RequestMapping("/update")
    @ResponseBody
    public String updateProject(@RequestBody ProjectBean projectBean){
        projectService.updateProject(projectBean);
    return "successful";
    }
    @RequestMapping("/selectByid")
    @ResponseBody
    public ProjectBean selectByid(int id){

        ProjectBean bean = projectService.selectProjectByPid(id);
        return bean;
    }

    @RequestMapping("/selectmyproject")
    @ResponseBody
    public List<ProjectBean> selectmyproject(HttpSession session){
        EmployeeBean user = (EmployeeBean) session.getAttribute("user");
        int eid =user.getEid();
        List<SessionBean> list = sessionService.selecteid(eid);
        String s = "接受";
        List<ProjectBean> l= new ArrayList<>();
        for (SessionBean sessionBean:
            list ) {
            if(Objects.equals(sessionBean.getState(), s)){
                ProjectBean projectBean = projectService.selectProjectByPid(sessionBean.getPid());
                l.add(projectBean);
            }
        }
        return l;
    }
    @RequestMapping(value = "upload" ,method = RequestMethod.POST)
    @ResponseBody
    public Object  aa(@RequestBody MultipartFile file){     //接收前台文件
        System.out.println(file);
        ProjectBean bean = projectService.selectByUrl("null");
        String s;
        ClientBean clientBean = clientService.queryUserById(bean.getcId());
        s=bean.getpName()+"-"+clientBean.getName()+"-"+clientBean.getUnit3()+"-"+bean.getbTime();
        String url=FileConfig.saveFileReturnUrl(file,s,"资料","");
        System.out.println("文件路径："+url);
        JSONObject resObj = new JSONObject();
        resObj.put("msg","ok");
        return resObj;
    }
}
