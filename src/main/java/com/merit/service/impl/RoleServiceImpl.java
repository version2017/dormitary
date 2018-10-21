package com.merit.service.impl;

import com.merit.dao.RoleDao;
import com.merit.entity.Role;
import com.merit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by R on 2018/8/9.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> getRolesByEmplId(int emplId) {
        return roleDao.queryRolesByEmplId(emplId);
    }

    public List<Role> listAllRoles() {
        return roleDao.queryAllRoles();
    }

    public boolean addRole(Role role) {
        return roleDao.addRole(role) == 1;
    }

    public boolean updateRole(Role role) {
        return roleDao.updateRole(role) == 1;
    }

    public boolean setPersonRole(int emplId, String roleName) {
        Role role = new Role();
        role.setEmplId(emplId);
        role.setRoleName(roleName);
        return addRole(role);
    }

    public boolean deleteRoleByEmplId(int emplId) {
        return roleDao.deleteRoleByEmplId(emplId) == 1;
    }
}
