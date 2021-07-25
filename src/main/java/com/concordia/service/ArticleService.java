package com.concordia.service;

import com.concordia.pojo.Article;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.ArticleRequest;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService extends BaseService<Article, String> {

    RespResult publish(String userId, ArticleRequest articleRequest);

    RespResult update(String userId, String articleId, ArticleRequest articleRequest);

    RespResult deleteArticleById(String userId, String articleId);

    RespResult showSingleArticleById(String articleId);
}
