package com.shop.thrift.Controller.admin;

import com.shop.thrift.Services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @RequestMapping
    public String show(Model model){
        model.addAttribute("sizes", sizeService.findAll());
        return "admin-size";
    }


}
