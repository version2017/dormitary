package com.merit.dao;

import com.merit.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/6/15.
 */
public interface PersonDao {

    List<Person> queryAllPerson();

    Person queryPersonByOpenid(String openid);

    Person queryPersonByEmplid(int emplId);

    List<Person> queryPersonsByNameKeyWord(String keyWord);

    List<Map> queryPersonMapByEmplid(int emplId);

    List<Map> queryAllDormMana();

    List<Map> queryDormManaByName(String name);

    int savePerson(Person person);

    List<Person> getPersonsInTheDormitary(int id);

    int checkIn(@Param("dormitaryId")int dormitaryId, @Param("workNumber")int workNumber);

    int leave(@Param("emplId")int emplId);

    /**
    *<p>功能描述：获取所有项目经理名单</p>
    *<ul>
    *<li>@param []</li>
    *<li>@return java.util.List<com.merit.entity.Person></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/14 16:28</li>
    *</ul>
    */
    List<Person> queryAllProjectManagers();

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
    List<Person> queryAllSectorManagers();

    /**
     *<p>功能描述：获取人员总数</p>
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
    *<p>功能描述：根据角色获取总人数。上个数据库版本遗留问题：数字代表职位</p>
    *<ul>
    *<li>@param [role]</li>
    *<li>@return int</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/22 14:54</li>
    *</ul>
    */
    int getTotalNumByRole(String role);

    /**
    *<p>功能描述：获取普通员工人数</p>
    *<ul>
    *<li>@param []</li>
    *<li>@return int</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/8/13 15:27</li>
    *</ul>
    */
    int queryOrdinaryPersonNumByRole();

    /**
     *<p>功能描述：根据页码分页查询人员信息</p>
     *<ul>
     *<li>@param [start, pageSize]</li>
     *<li>@return java.util.List<com.merit.entity.Person></li>
     *<li>@throws </li>
     *<li>@author R</li>
     *<li>@date 2018/7/12 15:26</li>
     *</ul>
     */
    List<Map> queryPersonMapsByPage(@Param("start")int start, @Param("pageSize")int pageSize);

    List<Map> queryPersonMapsByName(String name);

    List<Map> queryPersonMapsByRole(@Param("start")int start, @Param("pageSize")int pageSize, @Param("role")String role);

    /**
    *<p>功能描述：获取普通员工的信息Map</p>
    *<ul>
    *<li>@param []</li>
    *<li>@return java.util.List<java.util.Map></li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/8/13 15:29</li>
    *</ul>
    */
    List<Map> queryOrdinaryPersonMapsByRole(@Param("start")int start, @Param("pageSize")int pageSize);

    int addPerson(Person person);

    int updatePerson(Person person);

    int deletePerson(int emplId);
}
