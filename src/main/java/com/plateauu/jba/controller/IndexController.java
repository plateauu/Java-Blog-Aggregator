package com.plateauu.jba.controller;

import com.plateauu.jba.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @Autowired
    private ItemService itemService;

    @RequestMapping({"/index", "/"})
    public String index(Model model) {
            model.addAttribute("items", itemService.findFirstTen());
        return "index";
    }


}
