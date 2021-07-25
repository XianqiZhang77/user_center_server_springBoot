package com.concordia.dao;

import com.concordia.pojo.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ArticleTagDao extends JpaRepository<ArticleTag, String> {

    @Query("delete from ArticleTag where articleId = ?1")
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    void deleteAllByArticleId(String articleId);

    @Query("select tagName from ArticleTag where articleId = ?1")
    List<String> findTagNameByArticleId(String articleId);
}
