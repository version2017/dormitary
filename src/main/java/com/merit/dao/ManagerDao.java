package com.merit.dao;

import com.merit.entity.Manager;
import org.apache.ibatis.annotations.Param;

/**
 * Created by R on 2018/7/10.
 */
public interface ManagerDao {

    Manager queryManagerByUsername(String username);

    int alterPassword(@Param("username")String username, @Param("password")String password);
}
