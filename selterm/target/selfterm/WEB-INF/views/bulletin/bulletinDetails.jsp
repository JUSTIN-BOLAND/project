<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
   <section class="navigation">
          <span>您当前所在的位置：</span>
          <i>广告详情</i>
        </section>
        <section class="main-cont">
          <div class="adv-cont">
          <h3 class="adv-tit">${bulletin.title }</h3>
          <div class="adv-desc">
           ${bulletin.content }
          </div>
          </div>
        </section>

	
 
	 
</body>
</html>