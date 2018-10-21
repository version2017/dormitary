package com.merit.service.impl;

import com.merit.dao.ProjectDao;
import com.merit.entity.Project;
import com.merit.service.ProjectService;
import com.merit.utils.dataobject.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/7/16.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public Map getProjectMapById(int id) {
        return projectDao.queryProjectMapById(id);
    }

    public Project getProjectById(int id) {
        return projectDao.queryProjectById(id);
    }

    public List<Project> getAllProject() {
        return projectDao.queryAllProject();
    }

    public List<Project> getProjectByKeyWord(String keyWord) {
        return projectDao.queryProjectByKeyWord(keyWord);
    }

    public List<Project> getProjectByManaEmplId(int emplId) {
        return projectDao.queryProjectByManaEmplId(emplId);
    }

    public List<Project> getProjectBySectId(int sectId) {
        return projectDao.queryProjectBySectId(sectId);
    }

    public PageInfo<Map> queryProjectByPage(int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        pageInfo.setCurrentPage(pageNum);
        int total = projectDao.getTotalNum();//总条数
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        //计算起始条数和末尾条数
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> projects = projectDao.queryProjectByPage(start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(projects);
        return pageInfo;
    }

    public PageInfo<Map> getProjectByNameAndPage(String projectName, int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        int total = projectDao.getNumOfQueryProjectByName(projectName);
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        pageInfo.setCurrentPage(pageNum);
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> projects = projectDao.queryProjectByNameAndPage(projectName, start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(projects);
        return pageInfo;
    }

    public boolean updateProject(Project project) {
        int res = projectDao.updateProject(project);
        if(res == 1)
            return true;
        return false;
    }

    public boolean addProject(Project project) {
        int res = projectDao.addProject(project);
        if(res == 1){
            return true;
        }
        return false;
    }

    public boolean deleteProject(int projectId) {
        return projectDao.delProject(projectId) == 1;
    }

    public PageInfo<Map> queryProjectsByPage(int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        pageInfo.setCurrentPage(pageNum);
        int total = projectDao.getTotalNum();//总条数
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        //计算起始条数和末尾条数
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> projects = projectDao.queryProjectsByPage(start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(projects);
        return pageInfo;
    }
}
