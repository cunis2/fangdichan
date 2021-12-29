package com.example.ruanjian.controller;

import com.alibaba.excel.EasyExcel;
import com.example.ruanjian.beans.EmployeeBean;
import com.example.ruanjian.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @RequestMapping( value="lay")
    public String aaa(){
        return "employee";
    }
    @RequestMapping("/dc")
    @ResponseBody
    public String dc(){
        EasyExcel.write("员工表.xlsx",EmployeeBean.class).sheet().doWrite(selectall());
        return "successful";
    }
    @RequestMapping("/eselectall")
    @ResponseBody
    public List<EmployeeBean> selectall(){
        List<EmployeeBean> list =employeeService.selectall();
        return list;
    }
    @RequestMapping("/eselectByid")
    @ResponseBody
    public String selectByid(int id){
        employeeService.selectByid(id);
        return "successful";
    }
    @RequestMapping("/einsert")
    @ResponseBody
    public String insert(@RequestBody EmployeeBean employeeBean){
        employeeService.inser(employeeBean);
        return "successful";
    }
    @RequestMapping("/edelete")
    @ResponseBody
    public String delete(@RequestBody String eid){
        employeeService.delete(eid);
        return "successful";
    }
    @RequestMapping("/eselectde")
    @ResponseBody
    public  List<EmployeeBean> selectBydepartment(HttpSession session){
        EmployeeBean bean = (EmployeeBean) session.getAttribute("user");
        String department = bean.getDepartment();
        List<EmployeeBean> list = employeeService.selectBydepartment(department);
        return list;
    }
    @RequestMapping("/eselectna")
    @ResponseBody
    public String selectByname(){
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setbMoney(100);
        employeeBean.setType("空");
        employeeBean.setDepartment("渲染部门");
        employeeBean.setEid(1);
        employeeBean.setPassword("1234");
        employeeBean.setName("李1");
        employeeBean.setDuty("老板");
        employeeBean.setEvaluate("好");
        employeeService.selectByname(employeeBean);
        return "successful";
    }
    @RequestMapping("/eupdateByid")
    @ResponseBody
    public String updateByid(@RequestBody EmployeeBean employeeBean){
        employeeService.updateByid(employeeBean);
        return "successful";
    }

}
