package com.merit.service.impl;

import com.merit.dao.TestDao;
import com.merit.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by R on 2018/9/6.
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestDao testDao;

    public void decrease() {
        testDao.decrease();
    }

    public void increase() {
        testDao.increase();
    }

    public void testService() {
        increase();
        decrease();
    }
}
