package com.springboot.project.onlineShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="error")
public class ErrorController {

    @GetMapping(value="/soldout")
    @ResponseBody
    public String soldout(){
        return "We are sorry but the Product You Choose Already Sold Out";
    }
}
