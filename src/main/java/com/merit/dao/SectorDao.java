package com.merit.dao;

import com.merit.entity.Sector;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/7/13.
 */
public interface SectorDao {

    Map querySectorMapById(int id);

    Sector querySectorById(int id);

    Sector querySectorByName(String sectorName);

    Sector querySectorByManaEmplId(int emplId);

    /**
     *<p>功能描述：获取部门总个数</p>
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
     *<p>功能描述：根据页码分页查询部门</p>
     *<ul>
     *<li>@param [start, pageSize]</li>
     *<li>@return java.util.List<com.merit.entity.Sector></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 15:26</li>
     *</ul>
     */
    List<Map> querySectorByPage(@Param("start")int start, @Param("pageSize")int pageSize);

    /**
     *<p>功能描述：根据部门名关键字来分页查询部门</p>
     *<ul>
     *<li>@param [sectorName]</li>
     *<li>@return java.util.List<com.merit.entity.Sector></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 15:30</li>
     *</ul>
     */
    List<Map> querySectorByNameAndPage(@Param("sectorName")String sectorName,
                                                @Param("start")int start, @Param("pageSize")int pageSize);

    /**
     *<p>功能描述：获取结果总条数</p>
     *<ul>
     *<li>@param [sectorName]</li>
     *<li>@return int</li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 16:48</li>
     *</ul>
     */
    int getNumOfQuerySectorByName(@Param("sectorName")String sectorName);

    int updateSector(Sector sector);

    int addSector(Sector sector);

    int delSector(int id);

    List<Map> queryAllSectorsMap();
}
