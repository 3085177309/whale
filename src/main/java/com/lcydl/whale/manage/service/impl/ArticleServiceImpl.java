package com.lcydl.whale.manage.service.impl;

import com.lcydl.whale.common.mapper.ArticleMapper;
import com.lcydl.whale.common.pojo.Article;
import com.lcydl.whale.common.pojo.ArticleExample;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.manage.pojo.ArticleDTO;
import com.lcydl.whale.manage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public R save(Article article) {
        try {
            //补全信息
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = format.format(new Date());
            article.setModified(format.parse(dateStr));
            //存入数据库
            articleMapper.insertSelective(article);
            return R.ok("添加成功");
        } catch (ParseException e) {
            e.printStackTrace();
            return R.error("添加文章失败!");
        }
    }

    @Override
    public ArticleDTO list(Page page) {
        List<Article> articles = articleMapper.list(page);
        ArticleExample example = new ArticleExample();
        Integer count = articleMapper.countByExample(example);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setCode(0);
        articleDTO.setMsg("");
        articleDTO.setCount(count);
        articleDTO.setData(articles);
        return articleDTO;
    }

    @Override
    public Article get(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public R update(Article article) {
        try {
            //补全属性
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = format.format(new Date());
            article.setModified(format.parse(dateStr));
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
        try {
            //删除文章
            articleMapper.deleteByPrimaryKey(id);
            return R.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("删除失败!");
        }
    }

    @Override
    public R deleteBatch(Long[] ids) {
        try {
            //批量删除
            for (Long id : ids) {
                articleMapper.deleteByPrimaryKey(id);
            }
            return R.ok("批量删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("批量删除失败!");
        }
    }
}
