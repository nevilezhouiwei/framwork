package com.nevile.app.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Secured({"ROLE_ORDER","ROLE_ADMIN"})//springSecurity内部制定的注解
    @RequestMapping("/findAll")
    public String findAll(){
        return "order-list";
    }
}
