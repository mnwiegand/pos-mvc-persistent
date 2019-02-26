package com.example.posmvcpersistent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {
    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "POS System");
        return"home/index";
    }

}

