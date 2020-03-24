package com.nevile.app.controller;

import com.nevile.app.jms.MqQueueProductcer;
import com.nevile.app.jms.MqTopicPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private MqQueueProductcer mqQueueProductcer;

    @Autowired
    private MqTopicPublisher mqTopicPublisher;

    //@Secured({"ROLE_PRODUCT","ROLE_ADMIN"})//springSecurity内部制定的注解
    //@RolesAllowed({"ROLE_PRODUCT","ROLE_ADMIN"})//jsr250注解
    @PreAuthorize("hasAnyAuthority('ROLE_PRODUCT','ROLE_ADMIN')")//spring的el表达式注解
    @RequestMapping("/findAll")
    public String findAll(){
        for (int i = 1 ; i <= 100 ; i++){
            mqQueueProductcer.send("test1","我发送Amessage:" + i);
            //在本地打印消息
            System.out.println("我现在发的消息是：" + "我发送Amessage:" + i);
        }
        for (int i = 1 ; i <= 100 ; i++){
            mqQueueProductcer.send("test2","我发送Bmessage:" + i);
            //在本地打印消息
            System.out.println("我现在发的消息是：" + "我发送Bmessage:" + i);
        }
        for (int i = 1 ; i <= 100 ; i++){
            mqTopicPublisher.send("Topictest1","我发送Bmessage:" + i);
            //在本地打印消息
            System.out.println("我现在发的消息是：" + "我发送TopicPublisher:" + i);
        }
        return "product-list";
    }

}
