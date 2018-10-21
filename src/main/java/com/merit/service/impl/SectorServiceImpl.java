package com.merit.service.impl;

import com.merit.dao.SectorDao;
import com.merit.entity.Sector;
import com.merit.service.ProjectService;
import com.merit.service.SectorService;
import com.merit.utils.dataobject.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/7/13.
 */
@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    private SectorDao sectorDao;

    @Autowired
    private ProjectService projectService;

    public Map getSectorMapById(int id) {
        return sectorDao.querySectorMapById(id);
    }

    public Sector getSectorById(int id) {
        return sectorDao.querySectorById(id);
    }

    public Sector getSectorByName(String sectorName) {
        return sectorDao.querySectorByName(sectorName);
    }

    public Sector getSectorByManaEmplId(int emplId) {
        return sectorDao.querySectorByManaEmplId(emplId);
    }

    public PageInfo<Map> getSectorByPage(int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        pageInfo.setCurrentPage(pageNum);
        int total = sectorDao.getTotalNum();//总条数
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        //计算起始条数和末尾条数
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> sectors = sectorDao.querySectorByPage(start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(sectors);
        return pageInfo;
    }

    public PageInfo<Map> getSectorByNameAndPage(String sectorName, int pageNum) {
        PageInfo<Map> pageInfo= new PageInfo<Map>();
        int total = sectorDao.getNumOfQuerySectorByName(sectorName);
        pageInfo.setTotalCount(total);
        long pageSize = pageInfo.getPageSize();
        int totalPage = (int)(total/pageSize);
        if(0 != (total%pageSize)){
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        pageInfo.setCurrentPage(pageNum);
        int start = (int)pageInfo.getPageSize()*(pageNum-1);
        List<Map> sectors = sectorDao.querySectorByNameAndPage(sectorName, start, (int)pageInfo.getPageSize());
        pageInfo.setRecords(sectors);
        return pageInfo;
    }

    public boolean updateSector(Sector sector) {
        int res = sectorDao.updateSector(sector);
        if(res == 1)
            return true;
        return false;
    }

    public boolean addSector(Sector sector) {
        int res = sectorDao.addSector(sector);
        if(res == 1){
            return true;
        }
        return false;
    }

    public boolean delSector(int sectorId) {
        int res = sectorDao.delSector(sectorId);
        if(res == 1){
            return true;
        }
        return false;
    }

    public List<Map> getAllSectorsMap() {
        return sectorDao.queryAllSectorsMap();
    }
}
