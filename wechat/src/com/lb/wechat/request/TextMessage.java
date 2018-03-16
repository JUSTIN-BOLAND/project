package com.lb.wechat.request;

public class TextMessage
        extends BaseMessage
{
    private String Content;
    private int FuncFlag;

    public String getContent()
    {
        return this.Content;
    }

    public void setContent(String content)
    {
        this.Content = content;
    }

    public int getFuncFlag()
    {
        return this.FuncFlag;
    }

    public void setFuncFlag(int funcFlag)
    {
        this.FuncFlag = funcFlag;
    }
}
