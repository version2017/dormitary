package com.merit.service;

import com.merit.entity.Dormitary;
import com.merit.entity.Person;
import com.merit.utils.dataobject.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/6/24.
 */
public interface DormitaryService {

    List<String> getDormitaryPlaces();

    List<Map> getDormitaryListWithLeaderInfor(String place);

    List<Map> getSectorDormitaryListMap(int sectorManagerEmplId);

    Dormitary getDormitaryById(int id);

    Map getDormitaryAndProjectById(int id);

    Map getDormitaryAndManaByProjId(int projId);

    /**
     *<p>功能描述：获取宿舍入住人员名单并按职位排序</p>
     *<ul>
     *<li>@param [id]</li>
     *<li>@return com.merit.entity.Dormitary</li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/13 15:36</li>
     *</ul>
     */
    List<Person> getPersonsInTheDormitary(int id);

    List<String> getPersonNamesInTheDormitary(int id);

    /**
    *<p>功能描述：判断是否还有空床位</p>
    *<ul>
    *<li>@param [dormitaryId]</li>
    *<li>@return boolean</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/2 16:35</li>
    *</ul>
    */
    boolean isStillHaveBed(int dormitaryId);

    int getTotalNum();
    
    /**
    *<p>功能描述：分页查询宿舍</p>
    *<ul>
    *<li>@param [pageNum]</li>
    *<li>@return java.util.List<java.util.Map></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 11:16</li>
    *</ul>
    */
    PageInfo<Map> getDormitaryAndProjectByPage(int pageNum);

    /**
    *<p>功能描述：根据宿舍名模糊匹配来分页查询宿舍</p>
    *<ul>
    *<li>@param [dormitaryName, pageNum]</li>
    *<li>@return java.util.List<java.util.Map></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 15:29</li>
    *</ul>
    */
    PageInfo<Map> getDormitaryAndProjectByNameAndPage(String dormitaryName, int pageNum);

    /**
    *<p>功能描述：根据地区来分页查询宿舍</p>
    *<ul>
    *<li>@param [location, pageNum]</li>
    *<li>@return java.util.List<java.util.Map></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 15:19</li>
    *</ul>
    */
    PageInfo<Map> getDormitaryAndProjectByLocationAndPage(String location, int pageNum);

    /**
    *<p>功能描述：在选定区域内用宿舍名关键字去模糊匹配分页查询宿舍</p>
    *<ul>
    *<li>@param [dormitaryName, location, pageNum]</li>
    *<li>@return java.util.List<java.util.Map></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 15:25</li>
    *</ul>
    */
    PageInfo<Map> getDormitayAndProjectByNameAndLocationAndPage(String dormitaryName, String location, int pageNum);

    /**
    *<p>功能描述：更新宿舍信息</p>
    *<ul>
    *<li>@param [dormitary]</li>
    *<li>@return java.lang.Boolean</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/13 16:11</li>
    *</ul>
    */
    boolean updateDormitary(Dormitary dormitary);

    int deleteDormitary(int dormitaryId);

    boolean addDormitary(Dormitary dormitary);

    List<Dormitary> listAllDormitary();
}
