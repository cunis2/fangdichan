package com.example.ruanjian.mapper;

import com.example.ruanjian.beans.ProjectBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {
    List<ProjectBean> queryProjectList();//查询所有项目
    int insertProject(ProjectBean projectBean);  //插入一个项目
    int updateProject(ProjectBean projectBean);   //修改一个项目
    int deleteProjectByPid(Integer pid); //删除一个项目
    ProjectBean selectProjectByPid(Integer pid);  //根据项目号查信息
    ProjectBean selectByname(String name);//名字查
    ProjectBean selectByUrl(String dataUrl);//查询是否为文件未上传项目
}
