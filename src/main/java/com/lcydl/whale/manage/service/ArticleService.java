package com.lcydl.whale.manage.service;

import com.lcydl.whale.common.pojo.Article;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.manage.pojo.ArticleDTO;

public interface ArticleService {
    R save(Article article);

    ArticleDTO list(Page page);

    Article get(Long id);

    R update(Article article);

    R delete(Long id);

    R deleteBatch(Long[] ids);
}
