package com.lb.wechat.request;

public class Music
{
    private String Title;
    private String Description;
    private String MusicUrl;
    private String HQMusicUrl;
    private String ThumbMediaId;

    public String getDescription()
    {
        return this.Description;
    }

    public void setDescription(String description)
    {
        this.Description = description;
    }

    public String getHQMusicUrl()
    {
        return this.HQMusicUrl;
    }

    public void setHQMusicUrl(String musicUrl)
    {
        this.HQMusicUrl = musicUrl;
    }

    public String getMusicUrl()
    {
        return this.MusicUrl;
    }

    public void setMusicUrl(String musicUrl)
    {
        this.MusicUrl = musicUrl;
    }

    public String getThumbMediaId()
    {
        return this.ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId)
    {
        this.ThumbMediaId = thumbMediaId;
    }

    public String getTitle()
    {
        return this.Title;
    }

    public void setTitle(String title)
    {
        this.Title = title;
    }
}