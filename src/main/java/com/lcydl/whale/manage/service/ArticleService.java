package com.lcydl.whale.manage.service;

import com.lcydl.whale.common.pojo.Article;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.manage.pojo.ArticleMessage;

public interface ArticleService {
    R save(Article article);

    ArticleMessage list(Page page);

    Article get(Long id);

    R update(Article article);

    R delete(Long id);

    R deleteBatch(Long[] ids);
}
