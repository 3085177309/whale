package com.lcydl.whale.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/{page}")
    public String method(@PathVariable("page") String page){
        System.out.println("您所访问的页面是: " + page);
        return page;
    }

}
