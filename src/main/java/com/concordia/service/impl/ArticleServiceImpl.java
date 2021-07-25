package com.concordia.service.impl;

import com.concordia.common.exception.CustomException;
import com.concordia.common.util.UUIDUtils;
import com.concordia.dao.ArticleDao;
import com.concordia.pojo.Article;
import com.concordia.pojo.ArticleTag;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.common.ResultCode;
import com.concordia.rpcDomain.request.ArticleRequest;
import com.concordia.rpcDomain.response.ArticleResponse;
import com.concordia.service.ArticleService;
import com.concordia.service.ArticleTagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, String>
        implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    protected JpaRepository getJpaRepository() {
        return articleDao;
    }

    @Override
    public RespResult publish(String userId, ArticleRequest articleRequest) {
        Article article = new Article();
        articleRequest.setPublishedTime(new Date());
        BeanUtils.copyProperties(articleRequest, article);
        article.setId(UUIDUtils.getUUID());
        article.setPublishTime(new Timestamp(articleRequest.getPublishedTime().getTime()));
        article.setUserId(userId);

        List<ArticleTag> tagList = new ArrayList<>();

        for (String tag : articleRequest.getArticleTagList()) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setId(UUIDUtils.getUUID());
            articleTag.setTagName(tag);
            tagList.add(articleTag);
        }
        articleDao.save(article);
        articleTagService.saveAll(tagList);
        return new RespResult(ResultCode.SUCCESS, article.getId());
    }

    @Override
    public RespResult update(String userId, String articleId, ArticleRequest articleRequest) {
        if (StringUtils.isBlank(articleId)) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, "Article id cannot be empty");
        }
        Optional<Article> articleOptional = articleDao.findById(articleId);
        if (!articleOptional.isPresent()) {
            return new RespResult(ResultCode.RESULT_DATA_NONE, "No Article can be found");
        }
        Article article = articleOptional.get();
        if (!StringUtils.equals(article.getUserId(), userId)) {
            throw new CustomException(ResultCode.PERMISSION_DENIED);
        }
        articleRequest.setPublishedTime(article.getPublishTime());
        BeanUtils.copyProperties(articleRequest, article);
        articleTagService.deleteAllByArticleId(articleId);

        List<ArticleTag> tagList = new ArrayList<>();
        for (String tag : articleRequest.getArticleTagList()) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setId(UUIDUtils.getUUID());
            articleTag.setTagName(tag);
            tagList.add(articleTag);
        }
        articleDao.save(article);
        articleTagService.saveAll(tagList);
        return new RespResult(ResultCode.SUCCESS, article.getId());
    }

    @Override
    public RespResult deleteArticleById(String userId, String articleId) {
        if (StringUtils.isBlank(articleId)) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, "Article id cannot be empty");
        }
        Optional<Article> articleOptional = articleDao.findById(articleId);
        if (!articleOptional.isPresent()) {
            return new RespResult(ResultCode.RESULT_DATA_NONE, "No Article can be found");
        }
        Article article = articleOptional.get();
        if (!StringUtils.equals(article.getUserId(), userId)) {
            throw new CustomException(ResultCode.PERMISSION_DENIED);
        }
        articleDao.deleteById(articleId);
        articleTagService.deleteAllByArticleId(articleId);
        return new RespResult(ResultCode.SUCCESS);
    }

    @Override
    public RespResult showSingleArticleById(String articleId) {
        if (StringUtils.isBlank(articleId)) {
            return new RespResult(ResultCode.PARAM_IS_BLANK, "Article id cannot be empty");
        }
        Optional<Article> articleOptional = articleDao.findById(articleId);
        if (!articleOptional.isPresent()) {
            return new RespResult(ResultCode.RESULT_DATA_NONE, "No Article can be found");
        }
        Article article = articleOptional.get();
        List<String> tags = articleTagService.findTagNameByArticleId(articleId);
        ArticleResponse articleResponse = new ArticleResponse();
        BeanUtils.copyProperties(article, articleResponse);
        articleResponse.setArticleTagList(tags);
        articleResponse.setPublishedTime(new Date(article.getPublishTime().getTime()));
        return new RespResult(ResultCode.SUCCESS, articleResponse);
    }

}
