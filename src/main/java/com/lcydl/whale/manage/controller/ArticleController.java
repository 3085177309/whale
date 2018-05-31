package com.lcydl.whale.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public String list(){
        return "article/list";
    }

    @GetMapping("/add")
    public String add(){
        return "article/add";
    }

}
