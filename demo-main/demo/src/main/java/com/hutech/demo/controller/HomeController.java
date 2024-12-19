package com.hutech.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "home/index";
    }

    @GetMapping("/about")
    public String about() {
        return "home/about";
    }

    @GetMapping("/booking")
    public String booking() {
        return "home/booking";
    }

    @GetMapping("/contact")
    public String contact() {
        return "home/contact";
    }

    @GetMapping("/service")
    public String service() {
        return "home/service";
    }

    @GetMapping("/team")
    public String team() {
        return "home/team";
    }

    @GetMapping("/testimonial")
    public String testimonial() {
        return "home/testimonial";
    }

}
