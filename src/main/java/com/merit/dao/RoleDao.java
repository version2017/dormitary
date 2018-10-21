package com.merit.dao;

import com.merit.entity.Role;

import java.util.List;

/**
 * Created by R on 2018/8/9.
 */
public interface RoleDao {

    List<Role> queryRolesByEmplId(int emplId);

    List<Role> queryAllRoles();

    int addRole(Role role);

    int updateRole(Role role);

    int deleteRoleByEmplId(int emplId);
}
