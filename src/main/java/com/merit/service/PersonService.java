package com.merit.service;

import com.merit.entity.Person;
import com.merit.utils.dataobject.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/7/14.
 */
public interface PersonService {

    List<Person> getAllPerson();

    Person getPersonByEmplId(int employId);

    Map getPersonMapByEmplId(int employId);

    Person getPersonByWechatId(String wechat);

    List<Person> getPersonsByNameKeyWord(String keyWord);

    List<Map> getAllDormMana();

    List<Map> getDormManasByName(String name);

    /**
    *<p>功能描述：获取所有项目经理名单</p>
    *<ul>
    *<li>@param []</li>
    *<li>@return java.util.List<com.merit.entity.Person></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/14 16:30</li>
    *</ul>
    */
    List<Person> getAllProjectManagers();
    
    /**
    *<p>功能描述：获取所有部门经理名单</p>
    *<ul>
    *<li>@param []</li>
    *<li>@return java.util.List<com.merit.entity.Person></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/14 16:35</li>
    *</ul>
    */
    List<Person> getAllSectorManagers();

    /**
     *<p>功能描述：分页查询人员信息</p>
     *<ul>
     *<li>@param [pageNum]</li>
     *<li>@return java.util.List<com.merit.entity.Person></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 11:16</li>
     *</ul>
     */
    PageInfo<Map> queryPersonMapByPage(int pageNum);


    PageInfo<Map> queryPersonMapByParams(Map params);

    boolean addPerson(Person person);

    boolean updatePerson(Person person);

    boolean deletePerson(int emplId);
}
