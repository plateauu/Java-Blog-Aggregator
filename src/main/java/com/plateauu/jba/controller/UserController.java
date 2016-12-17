package com.plateauu.jba.controller;


import com.plateauu.jba.entity.Blog;
import com.plateauu.jba.entity.User;
import com.plateauu.jba.service.BlogService;
import com.plateauu.jba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @ModelAttribute("user")
    private User constructUser() {
        return new User();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/register.html?success=true";
    }


    @ModelAttribute("blog")
    private Blog constructBlog() {
        return new Blog();
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String doAddBlog(@ModelAttribute("blog") Blog blog, Principal principal){
        String userName = principal.getName();
        blogService.save(blog, userName);
        return "redirect:/account.html";
    }

    @RequestMapping("/register")
    public String showRegister() {
        return "user-register";
    }

    @RequestMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping("/users/{id}")
    public String detail(Model model, @PathVariable int id){
        model.addAttribute("user", userService.findOneWithBlogs(id));
        return "user-detail";
    }

    @RequestMapping("/account")
    public String account(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("user", userService.findOneWithBlogs(name));
        return "user-detail";

    }



}
