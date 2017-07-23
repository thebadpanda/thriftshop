package com.shop.thrift.Controller.admin;


import com.shop.thrift.Entity.Color;
import com.shop.thrift.Services.ColorService;
import com.shop.thrift.Filter.BasicFilter;
import com.shop.thrift.Validator.ColorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

import static com.shop.thrift.Util.ParamBuilder.getParams;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/color")
@SessionAttributes(names="color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @ModelAttribute("color")
    public Color getForm(){
        return new Color();
    }

    @ModelAttribute("filter")
    public BasicFilter getFilter(){
        return new BasicFilter();
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        model.addAttribute("page", colorService.findAll(filter, pageable));
        return "admin-color";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id,Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
        model.addAttribute("color", colorService.findOne(id));
        model.addAttribute("page", colorService.findAll(filter, pageable));
        return "admin-color";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        colorService.delete(id);
        return "redirect:/admin/color"+getParams(pageable, filter);
    }

    @RequestMapping(method = POST)
    public String save(@ModelAttribute("color") @Valid Color color, BindingResult br, SessionStatus status, Model model, @PageableDefault Pageable pageable, BasicFilter filter) {
        if (br.hasErrors()) {
            model.addAttribute("page", colorService.findAll(filter, pageable));
            return "admin-color";
        }
        colorService.save(color);
        status.setComplete();
        return "redirect:/admin/color"+getParams(pageable, filter);
    }



}