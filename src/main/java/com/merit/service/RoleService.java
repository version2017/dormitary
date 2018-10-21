package com.merit.service;

import com.merit.entity.Role;

import java.util.List;

/**
 * Created by R on 2018/8/9.
 */
public interface RoleService {

    List<Role> getRolesByEmplId(int emplId);

    List<Role> listAllRoles();

    boolean addRole(Role role);

    boolean updateRole(Role role);

    boolean setPersonRole(int emplId, String roleName);

    boolean deleteRoleByEmplId(int emplId);
}
