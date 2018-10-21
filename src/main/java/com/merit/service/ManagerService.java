package com.merit.service;

/**
 * Created by R on 2018/7/10.
 */
public interface ManagerService {

    boolean isUsernameExist(String username);

    boolean isPasswordCorrect(String username, String password);

    boolean alterPassword(String username, String password);

}
