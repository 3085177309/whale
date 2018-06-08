package com.lcydl.whale.manage.controller;

import com.lcydl.whale.common.pojo.Article;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.manage.pojo.ArticleDTO;
import com.lcydl.whale.manage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("")
    public String article(){
        return "article/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public ArticleDTO list(Page page){
        return articleService.list(page);
    }

    /**
     * 保存文章
     * @param article
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public R save(Article article){
        return articleService.save(article);
    }

    /**
     * 获取编辑文章
     * @return
     */
    @GetMapping("/edit/{id}")
    @ResponseBody
    public Article edit(@PathVariable("id") Long id){
        return articleService.get(id);
    }

    /**
     * 编辑文章
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public R update(Article article){
        return articleService.update(article);
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Long id){
        return articleService.delete(id);
    }

    @PostMapping("/batchRemove")
    public R batchRemove(){
        return R.ok();
    }

}
