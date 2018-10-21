package com.merit.csv;

import com.merit.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by R on 2018/9/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class NumberOperateTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static int times = 0;

    @Autowired
    private TestService testService;


    class decreaseThread extends Thread{
        public void run(){
            System.out.println("减一");
            testService.decrease();
        }
    }
    class increaseThread extends Thread{
        public void run(){
            System.out.println("加一");
            testService.increase();
        }
    }
    class testThread extends Thread{
        public void run(){
            times++;
            System.out.println("测试方法" + times);
            testService.testService();
        }
    }

    @Test
    public void operateNum(){
        System.out.println("主线程开始");
        Thread th=Thread.currentThread();
        for(int i=0;i<100;i++){
            new testThread().start();
        }
        try{
            th.sleep(350);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
