package com.atguigu.controller;

import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //组合@Controller和@ResponseBody
public class UserController {

    @Autowired
    UserService userService; //JDK动态代理。

    //@RequestMapping(value = "/findAll",method = RequestMethod.GET)
    //@RequestMapping(value = "/findAll",method = RequestMethod.POST)
    @GetMapping("/user/findAll")
    //@PostMapping("/findAll")
    //@DeleteMapping
    //@PutMapping
    public List<User> findAll(){

        //com.sun.proxy.$Proxy【67】  数字任意
        System.out.println("userService.getClass() = " + userService.getClass());
        
        return userService.findAll();
    }
}
