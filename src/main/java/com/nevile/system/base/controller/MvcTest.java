package com.nevile.system.base.controller;

import com.nevile.system.base.entity.Result;
import com.nevile.system.base.vo.ValidData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/testmvc")
public class MvcTest {

    @RequestMapping(value = "/validdata" ,method = RequestMethod.POST)
    @ResponseBody
    public Result validate(ValidData validData){
        System.out.println(validData);
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }
}
