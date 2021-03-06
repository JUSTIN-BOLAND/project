package com.lb.wechat.servlet;

import com.lb.wechat.util.SignUtil;
import com.lb.wechat.util.WechatUtil;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WechatServlet   extends HttpServlet
{
    private static final long serialVersionUID = 4440739483644821986L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String token = this.getInitParameter("token");
        String signature = request.getParameter("signature");

        String timestamp = request.getParameter("timestamp");

        String nonce = request.getParameter("nonce");

        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if (SignUtil.checkSignature(token,signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        String respMessage = WechatUtil.processRequest(request, response);


        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
}
