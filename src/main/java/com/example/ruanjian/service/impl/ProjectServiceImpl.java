package com.example.ruanjian.service.impl;

import com.example.ruanjian.beans.ProjectBean;
import com.example.ruanjian.mapper.ProjectMapper;
import com.example.ruanjian.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public List<ProjectBean> queryProjectList() {
        return projectMapper.queryProjectList();
    }

    @Override
    public int insertProject(ProjectBean projectBean) {
        int i = projectMapper.insertProject(projectBean);
        return i;
    }

    @Override
    public int updateProject(ProjectBean projectBean) {
        int i=projectMapper.updateProject(projectBean);
        return i;
    }

    @Override
    public int deleteProjectByPid(Integer pid) {
        int i = projectMapper.deleteProjectByPid(pid);
        return i;
    }

    @Override
    public ProjectBean selectProjectByPid(Integer pid) {
        ProjectBean projectBean = projectMapper.selectProjectByPid(pid);
        return projectBean;
    }

    @Override
    public ProjectBean selectByname(String name) {
        ProjectBean bean = projectMapper.selectByname(name);
        return bean;
    }

    @Override
    public ProjectBean selectByUrl(String dataUrl) {
        ProjectBean bean = projectMapper.selectByUrl(dataUrl);
        return bean;
    }
}
