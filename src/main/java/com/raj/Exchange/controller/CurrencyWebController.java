package com.raj.Exchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyWebController {
    @GetMapping("/currency")
    public String getCurrency(ModelMap modelMap){
        return "currency";
    }

}
