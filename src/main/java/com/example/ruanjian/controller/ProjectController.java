package com.example.ruanjian.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.example.ruanjian.beans.*;
import com.example.ruanjian.config.FileConfig;
import com.example.ruanjian.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
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
        System.out.println(PID);
        return "successful";
    }
    @RequestMapping(value = "upload" ,method = RequestMethod.POST)
    @ResponseBody
    public Object  aa(@RequestBody MultipartFile file, HttpServletRequest request)throws UnknownHostException {     //接收前台资料文件
        ProjectBean bean = projectService.selectByUrl("null");

        String s2=""+bean.getpId();//url文件是pid
        String url2=FileConfig.saveFileReturnUrl(file,staticPath,s2,"Data","",request);//保存到数据库的url
        Url url = new Url();
        url.setpId(bean.getpId());
        urlService.inserturl(url);
        bean.setDataUrl(url2);
        System.out.println("文件路径："+url2);
        projectService.updateProject(bean);
        JSONObject resObj = new JSONObject();
        resObj.put("msg","ok");
        return resObj;
    }
    @RequestMapping(value = "uploadEmployeeFilesA" ,method = RequestMethod.POST)     //上传初期文件
    @ResponseBody
    public Object  aaa(@RequestBody MultipartFile file, HttpServletRequest request,HttpSession session)throws UnknownHostException {     //接收前台文件
        ProjectBean projectBean = projectService.selectProjectByPid(PID);
        EmployeeBean user = (EmployeeBean) session.getAttribute("user");
        String department = user.getDepartment();
        String unit2Name = "";


        if(department.equals("模型部门"))
        {
            unit2Name="model";


        }
        else if(department.equals("渲染部门"))
        {
            unit2Name="rend";


        }
        else if(department.equals("后期部门"))
        {
             unit2Name="latter";


        }
        String s=Integer.toString(PID);
        String url=FileConfig.saveFileReturnUrl(file,staticPath,s,unit2Name,"RawData",request); //url访问的



        System.out.println("访问路径"+url);
        Url u = urlService.selectbyid(projectBean.getpId());
        if(department.equals("模型部门"))
        {
            u.setUrl2(url);
        }
        else if(department.equals("渲染部门"))
        {
            u.setUrl4(url);
        }
        else if(department.equals("后期部门"))
        {
            u.setUrl6(url);
        }
        urlService.updateurl(u);                                          //这里需要把url保存到url表里面！！!对应的初期文件url
        System.out.println(u);
        JSONObject resObj = new JSONObject();
        resObj.put("msg","ok");
        return resObj;
    }
    @RequestMapping(value = "uploadEmployeeFilesB" ,method = RequestMethod.POST) //上传最终文件
    @ResponseBody
    public Object  sadda(@RequestBody MultipartFile file, HttpServletRequest request,HttpSession session) throws UnknownHostException{
        ProjectBean projectBean = projectService.selectProjectByPid(PID);
        EmployeeBean user = (EmployeeBean) session.getAttribute("user");
        String department = user.getDepartment();
        String unit2Name = "";


        if(department.equals("模型部门"))
        {
            unit2Name="model";


        }
        else if(department.equals("渲染部门"))
        {
            unit2Name="rend";


        }
        else if(department.equals("后期部门"))
        {
            unit2Name="latter";


        }
        String s=Integer.toString(PID);
        String url=FileConfig.saveFileReturnUrl(file,staticPath,s,unit2Name,"FinalData",request); //url访问的

        System.out.println("访问路径"+url);
        Url u = urlService.selectbyid(projectBean.getpId());
        if(department.equals("模型部门"))
        {
            u.setUrl3(url);

        }
        else if(department.equals("渲染部门"))
        {
            u.setUrl5(url);

        }
        else if(department.equals("后期部门"))
        {
            u.setUrl7(url);

        }                                        //这里需要把url保存到url表里面！！！对应的最终文件url
        urlService.updateurl(u);

        JSONObject resObj = new JSONObject();
        resObj.put("msg", "ok");
        return resObj;
    }
    @RequestMapping(value = "uploadEmployeeFilesXY" ,method = RequestMethod.POST)  //上传小样
    @ResponseBody
    public Object  saddda(@RequestBody MultipartFile file, HttpServletRequest request,HttpSession session) throws UnknownHostException{
        ProjectBean projectBean = projectService.selectProjectByPid(PID);


        String s=Integer.toString(PID);

        String url=FileConfig.saveFileReturnUrl(file,staticPath,s,"Sample","",request);//访问的url
        Url u = urlService.selectbyid(projectBean.getpId());
        u.setUrl8(url);
        urlService.updateurl(u);
                                                //url保存到数据库，是小样的url
        System.out.println("访问"+url);
        JSONObject resObj = new JSONObject();
        resObj.put("msg", "ok");
        return resObj;
    }

    @RequestMapping(value = "uploadEmployeeFilesJPG" ,method = RequestMethod.POST)  //上传jpg
    @ResponseBody
    public Object  sddaa(@RequestBody MultipartFile file, HttpServletRequest request  ) throws UnknownHostException{
        ProjectBean projectBean = projectService.selectProjectByPid(PID);

        String s=Integer.toString(PID);
        String url=FileConfig.saveFileReturnUrl(file,staticPath,s,"JPG","",request);//访问的url
        Url u = urlService.selectbyid(projectBean.getpId());
        u.setUrl1(url);
        urlService.updateurl(u);
        System.out.println("访问"+url);
        JSONObject resObj = new JSONObject();
        resObj.put("msg", "ok");
        return resObj;
    }
    @RequestMapping(value = "getAllProjectData" ,method = RequestMethod.POST)  //根据项目编号获取url对象
    @ResponseBody
  public List<Url> fdsfds(@RequestBody String pId){

        System.out.println(pId);
        List<Url> urls=new LinkedList<>();
        Url selectByid = urlService.selectbyid(Integer.parseInt(pId));
        urls.add(selectByid);
        return urls;
    }


}
