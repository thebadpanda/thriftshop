package com.shop.thrift.Controller.user;

import java.math.BigDecimal;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.shop.thrift.Entity.Item;
import com.shop.thrift.Entity.Users;
import com.shop.thrift.Services.*;
import com.shop.thrift.Validator.UsersValidator;
import com.shop.thrift.dto.Filter.ItemFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
@Controller
public class IndexController {
//
//    @Autowired
//    private UsersService usersService;
//
//    @RequestMapping("/")
//    public String index(Principal principal){
////		principal.getName();
//        return "user-index";
//    }
//
//    @RequestMapping("/admin")
//    public String admin(){
//        return "admin-admin";
//    }
//
//    @RequestMapping("/login")
//    public String login(){
//        return "user-login";
//    }
//
//    @RequestMapping("/registration")
//    public String registration(Model model){
//        model.addAttribute("userForm", new Users());
//        return "user-registration";
//    }
//
//    @RequestMapping(value="/registration", method=POST)
//    public String registration(@ModelAttribute Users users){
//        usersService.save(users);
//        return "redirect:/login";
//    }


    @Autowired
    private UsersService usersService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeService sizeService;

//    @Autowired
//    private BrandService brandService;
//
//    @Autowired
//    private DigitalUnitService digitalUnitService;
//
//    @Autowired
//    private FeatureStringService featureStringService;

    // @RequestMapping("/category/{id}")
    // public String category(@PathVariable int id, Model model) {
    // model.addAttribute("category", categoryService.findOne(id));
    // model.addAttribute("items", itemService.findByCategoryId(id));
    // return "user-category";
    // }

    // @RequestMapping("/")
    // public String index(Principal principal){
    // if(principal!=null){
    // System.out.println(principal.getName());
    // }
    // return "user-index";
    // }

    @RequestMapping("/")
    public String index(Model model,
                        @CookieValue(defaultValue = "0", name = "usersId") int id,
                        HttpServletResponse response, @PageableDefault Pageable pageable,
                        @ModelAttribute("filter") ItemFilter filter) {
        if (id == 0) {
            id = usersService.createNewUser();
            response.addCookie(new Cookie("userId", String.valueOf(id)));
        }
        model.addAttribute("page", itemService.findAll(filter, pageable));
        model.addAttribute("color", colorService.findAll());
        model.addAttribute("size", sizeService.findAll());
        model.addAttribute("subcategory", subcategoryService.findAll());
        return "user-index";
    }

    @ModelAttribute("filter")
    public ItemFilter getFilter() {
        return new ItemFilter();
    }

    @InitBinder("user")
    protected void bind(WebDataBinder binder) {
        binder.setValidator(new UsersValidator(usersService));
    }

    @GetMapping("/shopping")
    public String shopping(Model model,
                           @CookieValue(defaultValue = "0", name = "usersId") int usersId) {
        model.addAttribute("items", itemService.findByUsersId(usersId));
        // model.addAttribute("totalPrice", itemService.findAllPrice(itemId));
        // if (cartItemService.findAllPrice(cartId) != 0) {
        // model.addAttribute("cartItemPrice",
        // cartItemRepository.findAllPrice(cartId));
        // }
        BigDecimal totalPrice = new BigDecimal(0);
        for (Item item : itemService.findAll()) {
            totalPrice = totalPrice.add(item.getPrice());
        }
        model.addAttribute("totalPrice", totalPrice);
        return "user-shopping";
    }

    @GetMapping("/del/{itemId}")
    public String remove(
            @CookieValue(defaultValue = "0", name = "usersId") int usersId,
            @PathVariable int itemId) {
        usersService.removeToBasket(usersId, itemId);
        return "redirect:/shopping";
    }

    @GetMapping("/buy/{itemId}")
    public String buy(
            @CookieValue(defaultValue = "0", name = "usersId") int usersId,
            @PathVariable int itemId,Model model) {
        usersService.addToBasket(usersId, itemId);
//		BigDecimal totalPrice = new BigDecimal(0);
//		for (Item  item : itemService.findAll()) {
//			 totalPrice = totalPrice.add(item.getPrice());
//		}
//   model.addAttribute("totalPrice", totalPrice);
        return "redirect:/";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin-admin";
    }

    @RequestMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("users", new Users());
        return "user-registration";
    }

    @PostMapping("/registration")
    public String save(@Valid Users users, BindingResult br) {
        if (br.hasErrors())
            return "user-registration";
        usersService.save(users);
        return "redirect:/login";
    }

    @RequestMapping("/cancel")
    public String cancel(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/registration";
    }

    @RequestMapping("/cancelF")
    public String cancelF(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "user-login";
    }

    @GetMapping("/admin/login")
    public String adminlogin() {
        return "admin-login";
    }

    @RequestMapping("/item/{id}")
    public String hotelInCity(@PathVariable int id, Model model) {
        model.addAttribute("item", itemService.findOne(id));
        model.addAttribute("subcategory", subcategoryService.findOneByItem(id));
        model.addAttribute("color", colorService.findOneByItem(id));
        model.addAttribute("size", sizeService.findOneByItem(id));
        // model.addAttribute("digitalUnits",
        // digitalUnitService.findAllByItem(id));
        // model.addAttribute("featureStrings"
        // ,aditionalServiceService.findByHotelNameId(id));
        return "user-item";
    }

    @GetMapping("/wanted")
    public String wanted(
            @CookieValue(defaultValue = "0", name = "usersId") int usersId,
            Principal principal) {
        usersService
                .sendMail("thriftshop", principal.getName(), "Кожанка за восімдисєть рублів, видів?!");
        usersService.removeAllToBasket(usersId);
        return "user-success";
    }

}

