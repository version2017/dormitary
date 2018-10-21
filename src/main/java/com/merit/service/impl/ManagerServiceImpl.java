package com.merit.service.impl;

import com.merit.dao.ManagerDao;
import com.merit.entity.Manager;
import com.merit.service.ManagerService;
import com.merit.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by R on 2018/7/10.
 */
@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private ManagerDao managerDao;

    public boolean isUsernameExist(String username) {
        Manager manager = managerDao.queryManagerByUsername(username);
        if(null == manager){
            return false;
        }
        return true;
    }

    public boolean isPasswordCorrect(String username, String password) {
        Manager manager = managerDao.queryManagerByUsername(username);
        if(MD5Util.MD5Encode(password, "UTF-8").equals(manager.getManaPassword())){
            return true;
        }
        return false;
    }

    public boolean alterPassword(String username, String password) {
        int res = managerDao.alterPassword(username, MD5Util.MD5Encode(password, "UTF-8"));
        if(res == 1)
            return true;
        return false;
    }

}
