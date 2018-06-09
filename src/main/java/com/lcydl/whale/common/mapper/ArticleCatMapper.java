package com.lcydl.whale.common.mapper;

import com.lcydl.whale.common.pojo.ArticleCat;
import com.lcydl.whale.common.pojo.ArticleCatExample;
import com.lcydl.whale.common.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCatMapper {
    int countByExample(ArticleCatExample example);

    int deleteByExample(ArticleCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticleCat record);

    int insertSelective(ArticleCat record);

    List<ArticleCat> selectByExample(ArticleCatExample example);

    ArticleCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ArticleCat record, @Param("example") ArticleCatExample example);

    int updateByExample(@Param("record") ArticleCat record, @Param("example") ArticleCatExample example);

    int updateByPrimaryKeySelective(ArticleCat record);

    int updateByPrimaryKey(ArticleCat record);


    /**
     * 自己写的接口
     * @param page
     * @return
     */
    List<ArticleCat> list(Page page);
}