package com.nevile.app.controller;

import com.nevile.app.entity.SysUser;
import com.nevile.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
@Api(value = "用户操作接口Controller",tags = {"用户操作接口"})
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查找全量用户信息",notes = "注意数据量大的情况",httpMethod="PUT")
    @RequestMapping("/findAll")
    public String findAll(@ApiIgnore Model model){
        List<SysUser> list = userService.findAll();
        model.addAttribute("list", list);
        return "user-list";
    }

    @ApiOperation(value = "保存用户信息",notes = "注意数据量大的情况",httpMethod="PUT")
    @RequestMapping("/save")
    public String save(@ApiParam(name = "user",value = "用户信息",required = true) SysUser user){
        userService.save(user);
        return "redirect:findAll";
    }

    @RequestMapping("/toAddRolePage")
    public String toAddRolePage(Model model, Integer id, boolean success){
        Map<String, Object> map = userService.toAddRolePage(id);
        model.addAttribute("uid", id);
        model.addAttribute("allRoles", map.get("allRoles"));
        model.addAttribute("myRoles", map.get("myRoles"));
        if(success){
            model.addAttribute("success", "修改成功");
        }
        return "user-role-add";
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(Integer[] ids, Integer userId){
        //从后台获取到当前认证通过后的用户名
       // SecurityContextHolder.getContext().getAuthentication().getName();
        //String username = ((SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        userService.addRoleToUser(userId, ids);
        return "redirect:toAddRolePage?success="+true+"&id="+userId;
    }


}
