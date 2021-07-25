package com.concordia.service.impl;

import com.concordia.dao.ArticleTagDao;
import com.concordia.pojo.ArticleTag;
import com.concordia.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTagServiceImpl extends BaseServiceImpl<ArticleTag, String>
        implements ArticleTagService {

    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return articleTagDao;
    }

    @Override
    public void deleteAllByArticleId(String articleId) {
        articleTagDao.deleteAllByArticleId(articleId);
    }

    @Override
    public List<String> findTagNameByArticleId(String articleId) {
        return articleTagDao.findTagNameByArticleId(articleId);
    }
}
