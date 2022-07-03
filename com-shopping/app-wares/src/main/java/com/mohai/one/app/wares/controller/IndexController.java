package com.mohai.one.app.wares.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: moerhai@qq.com
 * @Date: 2020/11/20 21:21
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "Hello World!";
    }


}