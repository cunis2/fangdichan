package com.example.ruanjian.controller;

import com.alibaba.excel.EasyExcel;
import com.example.ruanjian.beans.*;
import com.example.ruanjian.config.FileConfig;
import com.example.ruanjian.service.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ProjectController {
    int PID;
    @Value("${web.upload-path}")
    //保存文件的路径，该路径有http访问权限
    private String staticPath;
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
    @Autowired
    UrlService urlService;
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
    @RequestMapping("/selectsugg")
    @ResponseBody
    public String selecteva(@RequestBody String pid){
        List<PerformBean> list = performService.selectBypid(Integer.parseInt(pid));
        PerformBean bean = list.get(0);
        String suggestion = bean.getSuggestion();
        System.out.println(suggestion);
        return suggestion;
    }
    @RequestMapping("prosugupdate")
    @ResponseBody
    public String evapj(@RequestBody PerformBean performBean){
        performService.update(performBean);
        return "successful";
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
        performBean.seteId(eid);
        performBean.setpId(Integer.parseInt(pid));
        performBean.setEvaluate(null);
        System.out.println(performBean);
        performService.insert(performBean);
        return "successful";
    }
    @RequestMapping("/pdc")
    @ResponseBody
    public String pdc(){
        EasyExcel.write("项目表.xlsx",ProjectBean.class).sheet().doWrite(queryProjectList());
        return "successful";
    }

    @RequestMapping("/changestate")
    @ResponseBody
    public String changestate(@RequestBody Suggesstion suggesstion,HttpSession session){
        int oldState = suggesstion.getOldState();
        int state = suggesstion.getState();
        PerformBean performBean = new PerformBean();
        int i = suggesstion.getpId();
        EmployeeBean employeeBean = (EmployeeBean) session.getAttribute("user");
        String department = employeeBean.getDepartment();
        performBean.setpId(i);
        SessionBean sessionBean = new SessionBean();
        sessionBean.setPid(i);
        sessionBean.setState("接受");
        ProjectBean projectBean = projectService.selectProjectByPid(i);
        String sta;
        if (suggesstion.getState()==1){
            sta = "建模中";
        }
        else if(suggesstion.getState()==2){
            sta = "渲染中";
        }else if(suggesstion.getState()==3){
            sta = "后期中";
        }else {
            sta = "已完成";
        }
        projectBean.setState(sta);
        projectService.updateProject(projectBean);
        String s ;
        int eid = 0;
        if(state>oldState){//交付
            if(oldState==1){department = "模型部门";}
            if(oldState==2){department = "渲染部门";}
            if(oldState==3){department = "后期部门";}
            s = department+"主管";
            List<EmployeeBean> list = employeeService.selectBydepartment(department);
            for (EmployeeBean e :
                    list) {
                if (Objects.equals(e.getDuty(), s)){
                    eid = e.getEid();
                    break;
                }
            }
            sessionBean.setSend(eid);
            List<SessionBean> sessionBeans = sessionService.selectdeBypid(sessionBean);
            for (SessionBean bean   :
                  sessionBeans  ) {
                int receive = bean.getReceive();
                performBean.setSuggestion(suggesstion.getSuggestion());
                performBean.seteId(receive);
                performService.updatesuggesstion(performBean);
            }
        }
        if(state<=oldState){//重制
            for(int j = oldState;j>=state;j--){
                if(j==3){ department="后期部门";}
                if(j==2){ department="渲染部门";}
                if(j==1){department="模型部门";}
                s = department+"主管";
                List<EmployeeBean> list = employeeService.selectBydepartment(department);
                for (EmployeeBean e :
                        list) {
                    if (Objects.equals(e.getDuty(), s)){
                        eid = e.getEid();
                        break;
                    }
                }
                sessionBean.setSend(eid);
                List<SessionBean> sessionBeans = sessionService.selectdeBypid(sessionBean);
                for (SessionBean bean   :
                        sessionBeans  ) {
                    int receive = bean.getReceive();
                    performBean.seteId(receive);
                    performBean.setSuggestion(suggesstion.getSuggestion());
                    System.out.println(s+performBean);
                    performService.updatesuggesstion(performBean);
                }
            }
        }
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
        projectBean.setPrice(project.getPrice());
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
        Url url= new Url();
        url.setpId(projectBean.getpId());
        url.setUrl1(null);
        url.setUrl2(null);
        url.setUrl3(null);
        url.setUrl4(null);
        url.setUrl5(null);
        url.setUrl6(null);
        url.setUrl7(null);
        urlService.inserturl(url);
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
    @RequestMapping("/getpid")
    @ResponseBody
    public String getipid(@RequestBody String pid){
        PID= Integer.parseInt(pid);
        return "successful";
    }
    @RequestMapping(value = "upload" ,method = RequestMethod.POST)
    @ResponseBody
    public Object  aa(@RequestBody MultipartFile file, HttpServletRequest request)throws UnknownHostException {     //接收前台文件
        ProjectBean bean = projectService.selectByUrl("null");
        ClientBean clientBean = clientService.queryUserById(bean.getcId());
        String s = "project"+bean.getpId()+clientBean.getName();
        String url=FileConfig.saveFileReturnUrl(file,staticPath,s,"中文","c",request);
        System.out.println("文件路径："+url);
        bean.setDataUrl(s);
        projectService.updateProject(bean);
        JSONObject resObj = new JSONObject();
        resObj.put("msg","ok");
        return resObj;
    }
    @RequestMapping(value = "eupload" ,method = RequestMethod.POST)
    @ResponseBody
    public Object  aaa(@RequestBody MultipartFile file, HttpServletRequest request,HttpSession session)throws UnknownHostException {     //接收前台文件
        ProjectBean projectBean = projectService.selectProjectByPid(PID);
        String s = projectBean.getDataUrl();
        EmployeeBean user = (EmployeeBean) session.getAttribute("user");
        String department = user.getDepartment();
        String url=FileConfig.saveFileReturnUrl(file,staticPath,s,department,"原始文件",request);
        JSONObject resObj = new JSONObject();
        resObj.put("msg","ok");
        return resObj;
    }
}
