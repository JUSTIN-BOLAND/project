package com.lb.wechat.request;

public class MusicMessage
        extends BaseMessage
{
    private Music Music;

    public Music getMusic()
    {
        return this.Music;
    }

    public void setMusic(Music music)
    {
        this.Music = music;
    }
}
