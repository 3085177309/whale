package com.lcydl.whale.manage.controller;

import com.lcydl.whale.common.pojo.Article;
import com.lcydl.whale.common.util.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id){
        return "article/edit";
    }

    @PostMapping("/save")
    public R save(Article article){
        /*article.*/
        return R.ok();
    }

    @PostMapping("/update")
    public R update(){
        return R.ok();
    }

    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id){
        return R.ok();
    }

    @PostMapping("/batchRemove")
    public R batchRemove(){
        return R.ok();
    }

}
