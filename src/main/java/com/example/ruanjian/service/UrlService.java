package com.example.ruanjian.service;

import com.example.ruanjian.beans.Url;

public interface UrlService {
    Url selectbyid(int pid);//id查询
    int inserturl(Url url);//插入
    int updateurl(Url url);//修改

}
