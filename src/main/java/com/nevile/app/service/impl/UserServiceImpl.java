package com.nevile.app.service.impl;


import com.nevile.app.dao.SysUserDao;
import com.nevile.app.entity.SysRole;
import com.nevile.app.entity.SysUser;
import com.nevile.app.security.NevilePasswordEncoder;
import com.nevile.app.service.RoleService;
import com.nevile.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void save(SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sysUserDao.save(user);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserDao.findAll();
    }

    @Override
    public Map<String, Object> toAddRolePage(Integer id) {
        List<SysRole> allRoles = roleService.findAll();
        List<Integer> myRoles = sysUserDao.findRolesByUid(id);
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles", allRoles);
        map.put("myRoles", myRoles);
        return map;
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        sysUserDao.removeRoles(userId);
        if (ids.length > 0) {
            for (Integer roleId : ids) {
                sysUserDao.addRole(userId, roleId);
            }
        }
    }

    /**
     * 认证业务
     *
     * @param username 用户在浏览器输入的用户名
     * @return UserDetails 是springsecurity自己的用户对象
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            //根据用户名做查询
            SysUser sysUser = sysUserDao.findByName(username);
            if (sysUser == null) {
                return null;
            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<SysRole> roles = sysUser.getRoles();
            for (SysRole role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            //{noop}后面的密码，springsecurity会认为是原文。
            UserDetails userDetails = new User(sysUser.getUsername(),
                    sysUser.getPassword(),
                    sysUser.getStatus() == 1,
                    true,
                    true,
                    true,
                    authorities);
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            //认证失败！
            return null;
        }

    }
}
