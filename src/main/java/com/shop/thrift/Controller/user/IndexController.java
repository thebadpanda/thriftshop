package com.shop.thrift.Controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "user-index";
    }

    @RequestMapping("/admin")
    public String admin(){

        return "admin-admin";
    }

}
