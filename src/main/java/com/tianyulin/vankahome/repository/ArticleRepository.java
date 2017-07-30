package com.tianyulin.vankahome.repository;

import com.tianyulin.vankahome.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by tianyulin on 2017/7/25.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select * from vk_article where article_id=?1", nativeQuery = true)
    Article findById(Long articleId);

}
