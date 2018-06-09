package com.lcydl.whale.manage.service.impl;

import com.lcydl.whale.common.mapper.ArticleCatMapper;
import com.lcydl.whale.common.pojo.ArticleCat;
import com.lcydl.whale.common.pojo.ArticleCatExample;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.common.util.TimeUtil;
import com.lcydl.whale.manage.pojo.ArticleCatMessage;
import com.lcydl.whale.manage.service.ArticleCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ArticleCatServiceImpl implements ArticleCatService {

    @Autowired
    private ArticleCatMapper articleCatMapper;

    @Override
    public List<ArticleCat> list() {
        ArticleCatExample example = new ArticleCatExample();
        return articleCatMapper.selectByExample(example);
    }

    @Override
    public ArticleCatMessage list(Page page) {
        List<ArticleCat> list = articleCatMapper.list(page);
        ArticleCatExample example = new ArticleCatExample();
        Integer count = articleCatMapper.countByExample(example);
        ArticleCatMessage message = new ArticleCatMessage();
        message.setCode(0);
        message.setMsg("");
        message.setCount(count);
        message.setData(list);
        return message;
    }

    @Override
    public R save(ArticleCat cat) {
        try {
            //补全信息
            cat.setCreated(TimeUtil.getFormatNowDate());
            cat.setModified(TimeUtil.getFormatNowDate());
            //存入数据库
            articleCatMapper.insertSelective(cat);
            return R.ok("添加成功");
        } catch (ParseException e) {
            e.printStackTrace();
            return R.error("添加文章类别失败!");
        }
    }

    @Override
    public ArticleCat get(Long id) {
        return articleCatMapper.selectByPrimaryKey(id);
    }

    @Override
    public R update(ArticleCat cat) {
        try {
            //补全属性
            cat.setModified(TimeUtil.getFormatNowDate());
            //修改数据库
            articleCatMapper.updateByPrimaryKeySelective(cat);
            return R.ok("修改成功");
        } catch (ParseException e) {
            e.printStackTrace();
            return R.error("修改文章类别失败!");
        }
    }

    @Override
    public R delete(Long id) {
        articleCatMapper.deleteByPrimaryKey(id);
        return R.ok("删除成功");
    }

    @Override
    public R deleteBatch(Long[] ids) {
        //批量删除
        for (Long id : ids) {
            articleCatMapper.deleteByPrimaryKey(id);
        }
        return R.ok("批量删除成功");
    }
}
