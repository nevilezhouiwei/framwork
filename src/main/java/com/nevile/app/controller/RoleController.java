package com.nevile.app.controller;


import com.nevile.app.entity.SysRole;
import com.nevile.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<SysRole> list = roleService.findAll();
        model.addAttribute("list", list);
        return "role-list";
    }

    @RequestMapping("/save")
    public String save(SysRole role){
        roleService.save(role);
        return "redirect:findAll";
    }
    
}
