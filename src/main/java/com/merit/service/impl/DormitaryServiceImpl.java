package com.merit.service.impl;

import com.merit.dao.DormitaryDao;
import com.merit.dao.PersonDao;
import com.merit.entity.Dormitary;
import com.merit.entity.Person;
import com.merit.service.DormitaryService;
import com.merit.utils.dataobject.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/6/24.
 */
@Service
public class DormitaryServiceImpl implements DormitaryService {

    @Autowired
    private DormitaryDao dormitaryDao;
    @Autowired
    private PersonDao personDao;

    public List<String> getDormitaryPlaces() {
        return dormitaryDao.getDormitaryPlaces();
    }

    public List<Map> getDormitaryListWithLeaderInfor(String place) {
        List<Map> dormitaries = dormitaryDao.getDormitaryListWithLeaderInfor(place);
        return dormitaries;
    }

    public List<Map> getSectorDormitaryListMap(int sectorManagerEmplId) {
        return dormitaryDao.getSectorDormitaryListMap(sectorManagerEmplId);
    }

    public Dormitary getDormitaryById(int id) {
        return dormitaryDao.queryDormitaryById(id);
    }


    public Map getDormitaryAndProjectById(int id) {
        return dormitaryDao.queryDormitaryAndProjectById(id);
    }

    public Map getDormitaryAndManaByProjId(int projId) {
        return dormitaryDao.queryDormitaryAndManaByProjId(projId);
    }

    public List<Person> getPersonsInTheDormitary(int id) {
        return personDao.getPersonsInTheDormitary(id);
    }

    public List<String> getPersonNamesInTheDormitary(int id) {
        List<Person> persons = getPersonsInTheDormitary(id);
        List<String> names = new ArrayList<String>();
        for(Person p : persons){
            names.add(p.getName());
        }
        return names;
    }

    public boolean isStillHaveBed(int dormitaryId) {
        Dormitary dormitary = dormitaryDao.queryDormitaryById(dormitaryId);
        String bedNumStr = dormitary.getBedNum();
        String occuNumStr = dormitary.getOccuNum();
        int bedNum = Integer.valueOf(bedNumStr);
        int occuNum = Integer.valueOf(occuNumStr);
        if((bedNum - occuNum) > 0){
            return true;
        }
        return false;
    }

    public int getTotalNum(){
        return dormitaryDao.getTotalNum();
    }

    public PageInfo<Map> getDormitaryAndProjectByPage(int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        pageInfo.setCurrentPage(pageNum);
        int total = dormitaryDao.getTotalNum();//总条数
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        //计算起始条数和末尾条数
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> dormitaries = dormitaryDao.queryDormitaryAndProjectByPage(start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(dormitaries);
        return pageInfo;
    }

    public PageInfo<Map> getDormitaryAndProjectByNameAndPage(String dormitaryName, int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        int total = dormitaryDao.getNumOfQueryDormitaryByName(dormitaryName);
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> dormitaries = dormitaryDao.queryDormitaryAndProjectByNameAndPage(dormitaryName, start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(dormitaries);
        return pageInfo;
    }

    public PageInfo<Map> getDormitaryAndProjectByLocationAndPage(String location, int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        int total = dormitaryDao.getNumOfQueryDormitaryByLocation(location);
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> dormitaries = dormitaryDao.queryDormitaryAndProjectByLocationAndPage(location, start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(dormitaries);
        return pageInfo;
    }

    public PageInfo<Map> getDormitayAndProjectByNameAndLocationAndPage(String dormitaryName, String location, int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        int total = dormitaryDao.getNumOfQueryDormitayByNameAndLocation(dormitaryName, location);
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> dormitaries = dormitaryDao.queryDormitayAndProjectByNameAndLocationAndPage(dormitaryName, location, start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(dormitaries);
        return pageInfo;
    }

    public boolean updateDormitary(Dormitary dormitary) {
        return dormitaryDao.updateDormitary(dormitary) == 1;
    }

    public int deleteDormitary(int dormitaryId) {
        return dormitaryDao.deleteDormitary(dormitaryId);
    }

    public boolean addDormitary(Dormitary dormitary) {
        return dormitaryDao.addDormitary(dormitary) == 1;
    }

    public List<Dormitary> listAllDormitary() {
        return dormitaryDao.queryAllDormitary();
    }
}
