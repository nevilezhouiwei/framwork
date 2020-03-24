package com.nevile.app.service.impl;


import com.nevile.app.dao.SysRoleDao;
import com.nevile.app.entity.SysRole;
import com.nevile.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Override
    public void save(SysRole role) {
        sysRoleDao.save(role);
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleDao.findAll();
    }
}
