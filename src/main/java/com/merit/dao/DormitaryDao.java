package com.merit.dao;

import com.merit.entity.Dormitary;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/6/13.
 */
public interface DormitaryDao {

    Dormitary queryDormitaryById(int id);

    Map queryDormitaryAndProjectById(int id);

    Map queryDormitaryAndManaByProjId(int projId);

    List<String> getDormitaryPlaces();

    List<Map> getDormitaryListWithLeaderInfor(String place);

    List<Map> getSectorDormitaryListMap(int sectorManagerEmplId);

    /**
    *<p>功能描述：增加入住人数</p>
    *<ul>
    *<li>@param [dormitaryId]</li>
    *<li>@return int</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/6/24 17:36</li>
    *</ul>
    */
    int increaseOccuNum(int dormitaryId);

    /**
     *<p>功能描述：减少入住人数</p>
     *<ul>
     *<li>@param [dormitaryId]</li>
     *<li>@return int</li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/6/24 17:36</li>
     *</ul>
     */
    int reduceOccuNum(int dormitaryId);

    /**
    *<p>功能描述：获取宿舍总个数</p>
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
    *<p>功能描述：根据页码分页查询宿舍</p>
    *<ul>
    *<li>@param [start, pageSize]</li>
    *<li>@return java.util.List<com.merit.entity.Dormitary></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 15:26</li>
    *</ul>
    */
    List<Dormitary> queryDormitaryByPage(@Param("start")int start, @Param("pageSize")int pageSize);


    List<Map> queryDormitaryAndProjectByPage(@Param("start")int start, @Param("pageSize")int pageSize);

    /**
    *<p>功能描述：根据宿舍名关键字来分页查询宿舍</p>
    *<ul>
    *<li>@param [dormitaryName]</li>
    *<li>@return java.util.List<java.util.Map></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 15:30</li>
    *</ul>
    */
    List<Map> queryDormitaryAndProjectByNameAndPage(@Param("dormitaryName")String dormitaryName,
                                                @Param("start")int start, @Param("pageSize")int pageSize);

    /**
    *<p>功能描述：根据地区来分页查询宿舍</p>
    *<ul>
    *<li>@param [location, start, pageSize]</li>
    *<li>@return java.util.List<java.util.Map></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 15:37</li>
    *</ul>
    */
    List<Map> queryDormitaryAndProjectByLocationAndPage(@Param("location")String location,
                                                    @Param("start")int start, @Param("pageSize")int pageSize);

    /**
    *<p>功能描述：在选定区域内根据宿舍名关键字来分页查询宿舍</p>
    *<ul>
    *<li>@param [dormitaryName, location, start, pageSize]</li>
    *<li>@return java.util.List<java.util.Map></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 15:41</li>
    *</ul>
    */
    List<Map> queryDormitayAndProjectByNameAndLocationAndPage(@Param("dormitaryName")String dormitaryName,
                                                          @Param("location")String location,
                                                          @Param("start")int start, @Param("pageSize")int pageSize);


    /**
    *<p>功能描述：获取结果总条数</p>
    *<ul>
    *<li>@param [dormitaryName]</li>
    *<li>@return int</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 16:48</li>
    *</ul>
    */
    int getNumOfQueryDormitaryByName(@Param("dormitaryName")String dormitaryName);

    /**
    *<p>功能描述：获取结果总条数</p>
    *<ul>
    *<li>@param [location]</li>
    *<li>@return int</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 16:48</li>
    *</ul>
    */
    int getNumOfQueryDormitaryByLocation(@Param("location")String location);

    /**
    *<p>功能描述：获取结果总条数</p>
    *<ul>
    *<li>@param [dormitaryName, location]</li>
    *<li>@return int</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/12 16:49</li>
    *</ul>
    */
    int getNumOfQueryDormitayByNameAndLocation(@Param("dormitaryName")String dormitaryName,
                                               @Param("location")String location);

    /**
    *<p>功能描述：更新宿舍信息</p>
    *<ul>
    *<li>@param [dormitary]</li>
    *<li>@return int</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/13 16:15</li>
    *</ul>
    */
    int updateDormitary(Dormitary dormitary);

    int deleteDormitary(int dormitaryId);

    int addDormitary(Dormitary dormitary);

    List<Dormitary> queryAllDormitary();
}
