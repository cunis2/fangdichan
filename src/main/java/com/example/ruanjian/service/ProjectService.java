package com.example.ruanjian.service;

import com.example.ruanjian.beans.ProjectBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Repository
public interface ProjectService {
    List<ProjectBean> queryProjectList();//查询所有项目
    int insertProject(ProjectBean projectBean);  //插入一个项目
    int updateProject(ProjectBean projectBean);   //修改一个项目
    int deleteProjectByPid(Integer pid); //删除一个项目
    ProjectBean selectProjectByPid(Integer pid);  //根据项目号查信息
    ProjectBean selectByname(String name);
    ProjectBean selectByUrl(String dataUrl);//查询是否为文件未上传项目
}
