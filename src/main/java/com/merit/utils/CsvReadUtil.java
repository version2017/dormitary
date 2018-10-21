package com.merit.utils;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by R on 2018/8/1.
 */
public class CsvReadUtil {

    public static void main(String[] args){
        String filePath = "C:\\Users\\R\\Desktop\\部门信息.csv";
        try{
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            while (csvReader.readRecord()){
                // 读一整行
                System.out.println(csvReader.getRawRecord());
                // 读这行的某一列
//                System.out.println(csvReader.get(1));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
