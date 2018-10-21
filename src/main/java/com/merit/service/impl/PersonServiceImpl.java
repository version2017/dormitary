package com.merit.service.impl;

import com.merit.dao.PersonDao;
import com.merit.entity.Person;
import com.merit.service.PersonService;
import com.merit.utils.dataobject.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/7/14.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    public List<Person> getAllPerson() {
        return personDao.queryAllPerson();
    }

    public Person getPersonByEmplId(int employId) {
        return personDao.queryPersonByEmplid(employId);
    }

    public Person getPersonByWechatId(String wechat) {
        return personDao.queryPersonByOpenid(wechat);
    }

    public List<Person> getPersonsByNameKeyWord(String keyWord){
        return personDao.queryPersonsByNameKeyWord(keyWord);
    }

    public List<Person> getAllProjectManagers() {
        return personDao.queryAllProjectManagers();
    }

    public List<Map> getAllDormMana() {
        return personDao.queryAllDormMana();
    }

    public List<Map> getDormManasByName(String name) {
        return personDao.queryDormManaByName(name);
    }

    public List<Person> getAllSectorManagers() {
        return personDao.queryAllSectorManagers();
    }

    public PageInfo<Map> queryPersonMapByPage(int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        pageInfo.setCurrentPage(pageNum);
        int total = personDao.getTotalNum();
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        //计算起始条数和末尾条数
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> persons = personDao.queryPersonMapsByPage(start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(persons);
        return pageInfo;
    }

    public PageInfo<Map> queryPersonMapByParams(Map params) throws NullPointerException{
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        int pageNum = (Integer)params.get("pageNum");//页码
        int totalCount;//总条数
        pageInfo.setCurrentPage(pageNum);
        if(null != params.get("emplId")){
            int emplId = (Integer)params.get("emplId");
            Map person = personDao.queryPersonMapByEmplid(emplId).get(0);
            List persons = new ArrayList();
            if(null != person){
                persons.add(person);
            }
            pageInfo.setTotalCount(1);
            pageInfo.setTotalPage(1);
            pageInfo.setRecords(persons);
            return pageInfo;
        }
        if(null != params.get("name")){
            String name = (String)params.get("name");
            List<Map> persons = personDao.queryPersonMapsByName(name);
            //大于1说明该员工在系统中不止一个职位，将职位名称合并
            if(persons.size()>1){
                String roles = "";
                for(Map person : persons){
                    roles += (String)person.get("ROLE_NAME");
                    roles += "、";
                }
                roles = roles.substring(0, (roles.length()-1));
                Map person = persons.get(0);
                person.put("ROLE_NAME", roles);
                persons = new ArrayList<Map>();
                persons.add(person);
            }
            pageInfo.setTotalCount(1);
            pageInfo.setTotalPage(1);
            pageInfo.setRecords(persons);
            return pageInfo;
        }
        if(null != params.get("role")){
            if("普通员工".equals(params.get("role"))){
                return queryOrdinaryPersonMapByParams(params);
            }
            String role = (String)params.get("role");
            totalCount = personDao.getTotalNumByRole(role);
            int start = (int)pageInfo.getPageSize()*(pageNum-1);
            List<Map> persons = personDao.queryPersonMapsByRole(start, (int)pageInfo.getPageSize(), role);
            pageInfo.setTotalCount(totalCount);
            long pageSize = pageInfo.getPageSize();
            int totalPage = (int)(totalCount/pageSize);
            if(0 != (totalCount%pageSize)){
                totalPage++;
            }
            pageInfo.setTotalPage(totalPage);
            pageInfo.setRecords(persons);
            return pageInfo;
        }

        //接口传来的参数中没有emplId和role，所以只按页码来查询
        totalCount = getAllPerson().size();
        pageInfo.setTotalCount(totalCount);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(totalCount/pageSize);
        if(0 != (totalCount%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        //计算起始条数和末尾条数
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> persons = personDao.queryPersonMapsByPage(start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(persons);
        return pageInfo;
    }

    private PageInfo<Map> queryOrdinaryPersonMapByParams(Map params){
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        int pageNum = (Integer)params.get("pageNum");//页码
        String role = (String)params.get("role");
        int totalCount = personDao.queryOrdinaryPersonNumByRole();
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> persons = personDao.queryOrdinaryPersonMapsByRole(start, (int)pageInfo.getPageSize());
        pageInfo.setTotalCount(totalCount);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(totalCount/pageSize);
        if(0 != (totalCount%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        pageInfo.setRecords(persons);
        return pageInfo;
    }

    public boolean addPerson(Person person) {
        return personDao.addPerson(person) == 1;
    }

    public Map getPersonMapByEmplId(int employId) {
        return personDao.queryPersonMapByEmplid(employId).get(0);
    }

    public boolean updatePerson(Person person){
        return personDao.updatePerson(person) == 1;
    }

    public boolean deletePerson(int emplId) {
        return personDao.deletePerson(emplId) == 1;
    }
}
