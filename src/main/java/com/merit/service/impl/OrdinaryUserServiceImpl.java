package com.merit.service.impl;

import com.merit.dao.DormitaryDao;
import com.merit.dao.PersonDao;
import com.merit.entity.Person;
import com.merit.service.DormitaryService;
import com.merit.service.OrdinaryUserService;
import com.merit.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by R on 2018/6/15.
 */
@Service
public class OrdinaryUserServiceImpl implements OrdinaryUserService {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private DormitaryDao dormitaryDao;
    @Autowired
    private DormitaryService dormitaryService;

    public boolean isInformationFilled(String openid) {
        Person person = personDao.queryPersonByOpenid(openid);
        if(null != person)
            return true;
        return false;
    }

    public int saveUserInfor(Person person) {
        return personDao.savePerson(person);
    }

    public boolean isCheckIn(int dormitaryId, String openid) {
        Person person = personDao.queryPersonByOpenid(openid);
        if(dormitaryId == person.getDormId()){
            return true;
        }
        return false;
    }
    public boolean isCheckIn(int dormitaryId, int emplId) {
        Person person = personDao.queryPersonByEmplid(emplId);
        if(dormitaryId == person.getDormId()){
            return true;
        }
        return false;
    }

    @Transactional//声明式事务，防止宿舍出现入住人数异常
    public boolean checkIn(int dormitaryId, int workNumber) {
        //如上次入住后还未退房先退房
        boolean isLeave = isLeave(workNumber);
        if(!isLeave){
            leave(workNumber);
        }
        //判断是否还有空床位
        boolean isStillHaveBed = dormitaryService.isStillHaveBed(dormitaryId);
        if(!isStillHaveBed){
            return false;
        }

        int res1 = personDao.checkIn(dormitaryId, workNumber);
        int res2 = dormitaryDao.increaseOccuNum(dormitaryId);
        if(res1==1 && res2==1){
            return true;
        }
        return false;
    }

    public boolean isLeave(int workNumber){
        Person person = personDao.queryPersonByEmplid(workNumber);
        if(person.getDormId() == 0)
            return true;
        return false;
    }

    @Transactional//声明式事务，防止宿舍出现入住人数异常
    public boolean leave(int workNumber) {
        Person person = personDao.queryPersonByEmplid(workNumber);
        int dormitaryId = person.getDormId();
        int res1 = personDao.leave(workNumber);
        int res2 = dormitaryDao.reduceOccuNum(dormitaryId);
        if(res1==1 && res2==1){
              return true;
        }
        return false;
    }

    public boolean invite(int dormId, int emplId) {
        return checkIn(dormId, emplId);
    }

    public boolean isFirstTimeLogin(int workNumber) {
        Person person = personDao.queryPersonByEmplid(workNumber);
        if(person == null || TextUtils.isEmpty(person.getPhonNum())){
            return true;
        }
        return false;
    }
}
