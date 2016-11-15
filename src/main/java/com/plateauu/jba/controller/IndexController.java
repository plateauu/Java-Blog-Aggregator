package com.plateauu.jba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping("/ddd")
    public String home() {
        return "home";
    }

}
