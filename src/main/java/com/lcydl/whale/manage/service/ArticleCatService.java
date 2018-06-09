package com.lcydl.whale.manage.service;

import com.lcydl.whale.common.pojo.ArticleCat;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.manage.pojo.ArticleCatMessage;

import java.util.List;

public interface ArticleCatService {
    List<ArticleCat> list();
    ArticleCatMessage list(Page page);
    R save(ArticleCat cat);
    ArticleCat get(Long id);
    R update(ArticleCat cat);
    R delete(Long id);
    R deleteBatch(Long[] ids);
}
