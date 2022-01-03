package com.example.ruanjian.controller;

import com.example.ruanjian.beans.Url;
import com.example.ruanjian.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UrlController {
    @Autowired
    UrlService urlService;
    public Url selectByid(int pid){
        Url url = urlService.selectbyid(pid);
        return url;
    }
    public String inserturl(Url url){
        urlService.inserturl(url);
        return "successful";
    }
}
