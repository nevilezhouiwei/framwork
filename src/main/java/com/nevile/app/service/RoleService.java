package com.nevile.app.service;

import com.nevile.app.entity.SysRole;

import java.util.List;

public interface RoleService {

    void save(SysRole role);

    List<SysRole> findAll();

}
