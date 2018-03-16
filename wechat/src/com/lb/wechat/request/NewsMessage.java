package com.lb.wechat.request;
import java.util.List;

public class NewsMessage
        extends BaseMessage
{
    private int ArticleCount;
    private List<Article> Articles;
    private int FuncFlag;

    public int getFuncFlag()
    {
        return this.FuncFlag;
    }

    public void setFuncFlag(int funcFlag)
    {
        this.FuncFlag = funcFlag;
    }

    public int getArticleCount()
    {
        return this.ArticleCount;
    }

    public void setArticleCount(int articleCount)
    {
        this.ArticleCount = articleCount;
    }

    public List<Article> getArticles()
    {
        return this.Articles;
    }

    public void setArticles(List<Article> articles)
    {
        this.Articles = articles;
    }
}