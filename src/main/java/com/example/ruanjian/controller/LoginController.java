package com.example.ruanjian.controller;

import com.example.ruanjian.beans.EmployeeBean;
import com.example.ruanjian.beans.User;
import com.example.ruanjian.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("")
    public String sa(){
        return "login";
    }
    @RequestMapping("/index")
    public String os(){
        return "index";
    }
    @RequestMapping(value = "tologin" , method = RequestMethod.POST)
    @ResponseBody
    public Object a(@RequestBody User user,  HttpSession session){
        EmployeeBean bean = employeeService.selectByid(Integer.parseInt(user.getId()));

        if(Objects.equals(user.getPassword(), bean.getPassword())){
            session.setAttribute("user",bean);
             return "successful";
        }
        return "error";
    }

}
