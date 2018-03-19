package com.deyi.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class DefaultExceptionResolver implements HandlerExceptionResolver{
	private final Logger LOG = LoggerFactory.getLogger(DefaultExceptionResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView view = new ModelAndView();
		view.setViewName("error");
		ex.printStackTrace();
		LOG.error("DefaultExceptionResolver ：",ex);
		return view;
	}

}
