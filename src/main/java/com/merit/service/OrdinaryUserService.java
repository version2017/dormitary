package com.merit.service;

import com.merit.entity.Person;

/**
 * Created by R on 2018/6/15.
 */
public interface OrdinaryUserService {

    /**
    *<p>功能描述：完善用户信息</p>
    *<ul>
    *<li>@param 用户微信[openid]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/6/15 9:44</li>
    *</ul>
    */
    boolean isInformationFilled(String openid);

    /**
    *<p>功能描述：判断当前微信账户是否已经入住该宿舍</p>
    *<ul>
    *<li>@param [dormitaryId]</li>
    *<li>@return boolean</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/6/24 16:10</li>
    *</ul>
    */
    boolean isCheckIn(int dormitaryId, String openid);
    boolean isCheckIn(int dormitaryId, int emplId);

    /**
    *<p>功能描述：入住</p>
    *<ul>
    *<li>@param [dormitaryId, openid]</li>
    *<li>@return boolean</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/6/24 17:00</li>
    *</ul>
    */
    boolean checkIn(int dormitaryId, int workNumber);

    /**
    *<p>功能描述：判断该用户是否已经离开上一个宿舍</p>
    *<ul>
    *<li>@param [workNumber]</li>
    *<li>@return boolean</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/2 10:59</li>
    *</ul>
    */
    boolean isLeave(int workNumber);

    /**
    *<p>功能描述：离开</p>
    *<ul>
    *<li>@param [emplId]</li>
    *<li>@return boolean</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/6/24 17:19</li>
    *</ul>
    */
    boolean leave(int emplId);

    /***
    *<p>功能描述：邀请</p>
    *<ul>
    *<li>@param [dormId, emplId]</li>
    *<li>@return boolean</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/9/5 19:38</li>
    *</ul>
    */
    boolean invite(int dormId, int emplId);

    /**
    *<p>功能描述：保存用户信息</p>
    *<ul>
    *<li>@param [person]</li>
    *<li>@return int</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/6/24 17:12</li>
    *</ul>
    */
    int saveUserInfor(Person person);

    /**
    *<p>功能描述：判断此工号用户是否第一次登陆</p>
    *<ul>
    *<li>@param [workNumber]</li>
    *<li>@return boolean</li>
    *<li>@throws </li>
    *<li>@author R</li>
    *<li>@date 2018/7/1 22:07</li>
    *</ul>
    */
    boolean isFirstTimeLogin(int workNumber);
}
