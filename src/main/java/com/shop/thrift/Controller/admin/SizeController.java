package com.shop.thrift.Controller.admin;

import com.shop.thrift.Entity.Size;
import com.shop.thrift.Filter.BasicFilter;
import com.shop.thrift.Services.SizeService;
import com.shop.thrift.Validator.SizeValidator;
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
@RequestMapping("/admin/size")
@SessionAttributes(names="size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @InitBinder("size")
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new SizeValidator(sizeService));
    }

    @ModelAttribute("size")
    public Size getForm(){
        return new Size();
    }

    @ModelAttribute("filter")
    public BasicFilter getFilter(){
        return new BasicFilter();
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
        model.addAttribute("page", sizeService.findAll(filter, pageable));
        return "admin-size";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id,Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
        model.addAttribute("size", sizeService.findOne(id));
        model.addAttribute("page", sizeService.findAll(filter, pageable));
        return "admin-size";
    }


    @RequestMapping("/delete/{id}")
       public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        sizeService.delete(id);
        return "redirect:/admin/size"+getParams(pageable, filter);
    }

    @RequestMapping(method = POST)
    public String save(@ModelAttribute("size") @Valid Size size, BindingResult br, SessionStatus status, Model model, @PageableDefault Pageable pageable, BasicFilter filter) {
        if (br.hasErrors()) {
            model.addAttribute("page", sizeService.findAll(filter, pageable));
            return "admin-size";
        }
        sizeService.save(size);
        status.setComplete();
        return "redirect:/admin/size"+getParams(pageable, filter);
    }


}
