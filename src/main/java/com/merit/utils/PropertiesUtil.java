package com.merit.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by 10033 on 2017/3/23.
 */
@Component
public class PropertiesUtil {

    public Properties loadProperties(String fileName){
        Properties pro = new Properties();
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            System.out.println(Properties.class);
            is = this.getClass().getClassLoader().getResourceAsStream(fileName);
            isr = new InputStreamReader(is);
            pro.load(isr);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return pro;
    }

    public static String readProperty(Properties p,String key){
        if(TextUtils.isEmpty(p.getProperty(key)))
            return "";
        else
            return p.getProperty(key);
    }

}
