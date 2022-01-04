package com.example.ruanjian.mapper;

import com.example.ruanjian.beans.Url;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UrlMapper {
    Url selectbyid(int pid);//id查询
    int inserturl(Url url);//插入
    int updateurl(Url url);//修改
}
