package com.example.ruanjian.service.impl;

import com.example.ruanjian.beans.Url;
import com.example.ruanjian.mapper.UrlMapper;
import com.example.ruanjian.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class UrlServiceMapper implements UrlService {
    @Autowired
    UrlMapper urlMapper;
    @Override
    public Url selectbyid(int pid) {
        Url url = urlMapper.selectbyid(pid);
        return url;
    }

    @Override
    public int inserturl(Url url) {
        urlMapper.inserturl(url);
        return 1;
    }
}
