package com.shop.thrift.Controller.admin;


import com.shop.thrift.Entity.Users;
import com.shop.thrift.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/admin/users")
@SessionAttributes(names="users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ModelAttribute("users")
    public Users getUsers(){
        return new Users();
    }

    @RequestMapping("admin/users/show")
    public String show(Model model){
        model.addAttribute("users-show", usersService.findAll());
        return "admin-users";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        usersService.delete(id);
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/users/save")
    public String save(@ModelAttribute("users-save") Users users, SessionStatus status){
        usersService.save(users);
        status.setComplete();
        return "redirect:/admin/users/";
    }
}
