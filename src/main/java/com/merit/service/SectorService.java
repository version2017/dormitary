package com.merit.service;

import com.merit.entity.Sector;
import com.merit.utils.dataobject.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/7/13.
 */
public interface SectorService {

    Map getSectorMapById(int id);

    Sector getSectorById(int id);

    Sector getSectorByName(String sectorName);

    Sector getSectorByManaEmplId(int emplId);

     /**
         *<p>功能描述：分页查询部门</p>
         *<ul>
         *<li>@param [pageNum]</li>
         *<li>@return java.util.List<com.merit.entity.Sector></li>
         *<li>@throws </li>
         *<li>@author R</li>
         *<li>@date 2018/7/12 11:16</li>
         *</ul>
         */
    PageInfo<Map> getSectorByPage(int pageNum);

    /**
     *<p>功能描述：根据部门名模糊匹配来分页查询部门</p>
     *<ul>
     *<li>@param [sectorName, pageNum]</li>
     *<li>@return java.util.List<com.merit.entity.Sector></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 15:29</li>
     *</ul>
     */
    PageInfo<Map> getSectorByNameAndPage(String sectorName, int pageNum);

    boolean updateSector(Sector sector);

    boolean addSector(Sector sector);

    boolean delSector(int sectorId);

    List<Map> getAllSectorsMap();
}
