package com.shop.thrift.Controller.admin;

import com.shop.thrift.Entity.Users;
import com.shop.thrift.dto.Filter.BasicFilter;
import com.shop.thrift.Services.UsersService;
import com.shop.thrift.Validator.UsersValidator;
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
@RequestMapping("/admin/users")
@SessionAttributes(names="users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @InitBinder("users")
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new UsersValidator(usersService));
    }

    @ModelAttribute("users")
    public Users getForm(){
        return new Users();
    }

    @ModelAttribute("filter")
    public BasicFilter getFilter(){
        return new BasicFilter();
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        model.addAttribute("page", usersService.findAll(filter, pageable));
        return "admin-users";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        usersService.delete(id);
        return "redirect:/admin/users"+getParams(pageable, filter);
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        model.addAttribute("page", usersService.findAll(filter, pageable));
        model.addAttribute("users", usersService.findOne(id));
        return "admin-users";
    }

@RequestMapping(method=POST)
    public String save(@ModelAttribute("users") @Valid Users users, SessionStatus status, BindingResult br,
                       Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter")BasicFilter filter){
        if(br.hasErrors()){
            model.addAttribute("page", usersService.findAll(filter, pageable));
            return "admin-users";
       }
        usersService.save(users);
        status.setComplete();
        return "redirect:/admin/users/"+getParams(pageable, filter);
    }
}
