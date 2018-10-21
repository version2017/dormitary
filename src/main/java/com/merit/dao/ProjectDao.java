package com.merit.dao;

import com.merit.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/7/16.
 */
public interface ProjectDao {

    Map queryProjectMapById(int id);

    Project queryProjectById(int id);

    List<Project> queryAllProject();

    List<Project> queryProjectByKeyWord(String keyWord);

    List<Project> queryProjectByManaEmplId(int emplId);

    List<Project> queryProjectBySectId(int sectId);

    /**
     *<p>功能描述：获取项目总个数</p>
     *<ul>
     *<li>@param []</li>
     *<li>@return int</li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 10:49</li>
     *</ul>
     */
    int getTotalNum();

    /**
     *<p>功能描述：根据页码分页查询项目</p>
     *<ul>
     *<li>@param [start, pageSize]</li>
     *<li>@return java.util.List<com.merit.entity.Project></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 15:26</li>
     *</ul>
     */
    List<Map> queryProjectByPage(@Param("start")int start, @Param("pageSize")int pageSize);

    /**
     *<p>功能描述：根据项目名关键字来分页查询项目</p>
     *<ul>
     *<li>@param [projectName]</li>
     *<li>@return java.util.List<com.merit.entity.Project></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 15:30</li>
     *</ul>
     */
    List<Map> queryProjectByNameAndPage(@Param("projectName")String projectName,
                                       @Param("start")int start, @Param("pageSize")int pageSize);

    /**
     *<p>功能描述：获取结果总条数</p>
     *<ul>
     *<li>@param [projectName]</li>
     *<li>@return int</li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 16:48</li>
     *</ul>
     */
    int getNumOfQueryProjectByName(@Param("projectName")String projectName);

    int updateProject(Project project);

    int addProject(Project project);

    int delProject(int id);

    /**
     *<p>功能描述：根据页码分页查询项目</p>
     *<ul>
     *<li>@param [start, pageSize]</li>
     *<li>@return java.util.List<com.merit.entity.Project></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 15:26</li>
     *</ul>
     */
    List<Map> queryProjectsByPage(@Param("start")int start, @Param("pageSize")int pageSize);

    int deleteProjectOfSector(int sectorId);
}
