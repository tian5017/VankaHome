package com.tianyulin.vankahome.entity;

import javax.persistence.*;

/**
 * Created by tianyulin on 2017/7/25.
 */
@Entity
@Table(name = "vk_article")
public class Article{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "article_name")
    private String articleName;

    @Column(name = "article_author")
    private String articleAuthor;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "article_count")
    private String articleCount;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
