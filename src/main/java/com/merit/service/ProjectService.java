package com.merit.service;

import com.merit.entity.Project;
import com.merit.utils.dataobject.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/7/16.
 */
public interface ProjectService {

    Map getProjectMapById(int id);

    Project getProjectById(int id);

    List<Project> getAllProject();

    List<Project> getProjectByKeyWord(String keyWord);

    List<Project> getProjectByManaEmplId(int emplId);

    List<Project> getProjectBySectId(int sectId);

    /**
     *<p>功能描述：分页查询项目</p>
     *<ul>
     *<li>@param [pageNum]</li>
     *<li>@return java.util.List<com.merit.entity.Project></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 11:16</li>
     *</ul>
     */
    PageInfo<Map> queryProjectByPage(int pageNum);

    /**
     *<p>功能描述：根据项目名模糊匹配来分页查询项目</p>
     *<ul>
     *<li>@param [projectName, pageNum]</li>
     *<li>@return java.util.List<com.merit.entity.Project></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 15:29</li>
     *</ul>
     */
    PageInfo<Map> getProjectByNameAndPage(String projectName, int pageNum);

    boolean updateProject(Project project);

    boolean addProject(Project project);

    boolean deleteProject(int projectId);

    /**
     *<p>功能描述：分页查询项目</p>
     *<ul>
     *<li>@param [pageNum]</li>
     *<li>@return java.util.List<com.merit.entity.Project></li>
     *<li>@throws </li>
     *<li>@author R</li>s
     *<li>@date 2018/7/12 11:16</li>
     *</ul>
     */
    PageInfo<Map> queryProjectsByPage(int pageNum);
}
