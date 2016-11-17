package com.plateauu.jba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/marcin")
    public String marcin() {
        return "marcin";

    }


    @RequestMapping("/test")
    public String home() {
        return "home";
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
