package com.lcydl.whale.manage.controller;

import com.lcydl.whale.common.pojo.ArticleCat;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.manage.pojo.ArticleCatMessage;
import com.lcydl.whale.manage.service.ArticleCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articleCat")
public class ArticleCatController {

    @Autowired
    private ArticleCatService articleCatService;

    @GetMapping("")
    public String article(){
        //查询所有文章类别
        return "content/article-cat";
    }

    @GetMapping("/list")
    @ResponseBody
    public ArticleCatMessage list(Page page){
        return articleCatService.list(page);
    }

    /**
     * 保存
     * @param cat
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public R save(ArticleCat cat){
        R save = articleCatService.save(cat);
        return save;
    }

    /**
     * 获取
     * @return
     */
    @GetMapping("/edit/{id}")
    @ResponseBody
    public ArticleCat edit(@PathVariable("id") Long id){
        return articleCatService.get(id);
    }

    /**
     * 编辑
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public R update(ArticleCat cat){
        return articleCatService.update(cat);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Long id){
        return articleCatService.delete(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
    public R deleteBatch(@RequestParam("ids[]") Long[] ids){
        return articleCatService.deleteBatch(ids);
    }
}
