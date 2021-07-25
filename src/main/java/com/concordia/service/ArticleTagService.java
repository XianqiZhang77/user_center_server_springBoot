package com.concordia.service;

import com.concordia.pojo.ArticleTag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleTagService extends BaseService<ArticleTag, String> {

    void deleteAllByArticleId(String articleId);

    List<String> findTagNameByArticleId(String articleId);
}
