package com.lcydl.whale.manage.service.impl;

import com.lcydl.whale.common.mapper.ArticleMapper;
import com.lcydl.whale.common.pojo.Article;
import com.lcydl.whale.common.pojo.ArticleExample;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.common.util.TimeUtil;
import com.lcydl.whale.manage.pojo.ArticleMessage;
import com.lcydl.whale.manage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public R save(Article article) {
        try {
            //补全信息
            article.setModified(TimeUtil.getFormatNowDate());
            //存入数据库
            articleMapper.insertSelective(article);
            return R.ok("添加成功");
        } catch (ParseException e) {
            e.printStackTrace();
            return R.error("添加文章失败!");
        }
    }

    @Override
    public ArticleMessage list(Page page) {
        List<Article> articles = articleMapper.list(page);
        ArticleExample example = new ArticleExample();
        Integer count = articleMapper.countByExample(example);
        ArticleMessage articleMessage = new ArticleMessage();
        articleMessage.setCode(0);
        articleMessage.setMsg("");
        articleMessage.setCount(count);
        articleMessage.setData(articles);
        return articleMessage;
    }

    @Override
    public Article get(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public R update(Article article) {
        try {
            //补全属性
            article.setModified(TimeUtil.getFormatNowDate());
            //修改数据库
            articleMapper.updateByPrimaryKeySelective(article);
            return R.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("修改文章失败!");
        }
    }

    @Override
    public R delete(Long id) {
        //删除文章
        articleMapper.deleteByPrimaryKey(id);
        return R.ok("删除成功");
    }

    @Override
    public R deleteBatch(Long[] ids) {
        //批量删除
        for (Long id : ids) {
            articleMapper.deleteByPrimaryKey(id);
        }
        return R.ok("批量删除成功");
    }
}
