package com.deyi.tag;


import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.deyi.util.Constants;

public class ButtonOut extends SimpleTagSupport {


	/***
	 * 松泽物料编码
	 */
	private String url;//按钮相关的请求地址

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void doTag() throws JspException, IOException {
		PageContext page = (PageContext) this.getJspContext();
		HttpSession session = page.getSession();
		@SuppressWarnings("unchecked")
		List<String> urls = (List<String>)session.getAttribute(Constants.SESSION_USER_URLS);
		//System.out.println(url+" \n "+urls.toString());
		if(urls.contains(url)){
			JspFragment bodyContent = getJspBody();
			StringWriter sw = new StringWriter();
			bodyContent.invoke(sw);
			String bodyContentStr = sw.toString();

			getJspContext().getOut().print(bodyContentStr);
		}

	}




}
