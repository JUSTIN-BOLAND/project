<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/deyi.tld"%>

<section class="navigation">
           <style>
			        .home-cont{ width:48%;}
			        </style>
			        <span>您当前所在的位置：</span><i>首页</i>
			   </section>
			   <section class="main-cont" style="padding-top:0;">
			        <div class="home-info">
			            <div class="home-info-box">
			             <div class="row">
			                <p class="col-md-4"><span>登录账号：</span> <em>${userInfo.id }</em></p>
			                <p class="col-md-4"><span>用户名：</span> <em>${userInfo.name }</em></p>
			                <p class="col-md-4"><span>角色名称：</span> <em>${userInfo.rolename }</em></p>
			              </div>
			          
			              <div>
			              </div>
			            </div>
			         </div>
		            <div id="carousel-example-generic" class="carousel slide carousel-wp" data-ride="carousel">
			            <ol class="carousel-indicators">
			             <c:forEach items="${list}" var="advertise" varStatus="advertises">
			             <c:if test="${advertises.index  eq 0 }">
			             <li data-target="#carousel-example-generic" data-slide-to="${advertises.index }" class="active" width="750px" height="185px"></li>
			             </c:if>
			             <c:if test="${advertises.index  ne 0 }">
			             <li data-target="#carousel-example-generic" data-slide-to="${advertises.index}" width="750px" height="185px"></li>
			             </c:if>
			             </c:forEach>
			            </ol>
			            <div class="carousel-inner " role="listbox">
			             <c:forEach items="${list}" var="advertise" varStatus="advertises">
			             	<c:if test="${advertises.index  eq 0 }">
				             	<div class="item active">
				                <img src="${advertise }" style="width:100%; max-height:300px;height:auto; "/>
				               </div>
			             	</c:if>
			             	<c:if test="${advertises.index  ne 0 }">
				             	<div class="item">
				                <img src="${advertise }" style="width:100%; max-height:300px;height:auto;"/>
				               </div>
			             	</c:if>
			               
			              </c:forEach>
			            </div>
		          	</div>
		          	
			        <div class="clear">
			        <%-- <div class="home-cont fl">
				        <h3 class="home-cont-tit">系统公告</h3>
				        <ul class="home-list">
				        <c:forEach items="${page.results}" var="bulletin">
				        <c:if test="${bulletin.day le 3}">
				       
				        <li class="cred">
				        <span>${bulletin.title }</span><em><fmt:formatDate value="${bulletin.createtime }" pattern="yyyy-MM-dd HH:mm:ss" /></em>
				         <a  onclick="changeRightMenu('<%=request.getContextPath()%>/bulletin/bulletinDetails.html?id=${bulletin.id }');" >详情</a> 
				         
				         </li>
				        </c:if>
				         
				       <c:if test="${bulletin.day gt  3}">
				        <li><span>${bulletin.title }</span><em><fmt:formatDate value="${bulletin.createtime }" pattern="yyyy-MM-dd HH:mm:ss" /></em>
				         <a  onclick="changeRightMenu('<%=request.getContextPath()%>/bulletin/bulletinDetails.html?id=${bulletin.id }');" >详情</a>  </li>
				        </c:if>
				        
				        
				        
				        </c:forEach>
				        </ul>
				        <div class="main-pading clear">
						   <%@ include file="/WEB-INF/views/page.jsp"%>
						</div>
			        </div> --%>
			       
				        <div class="home-cont fl">
					        <h3 class="home-cont-tit">交易数据</h3>
					        <ul class="home-list">
					        <li class="cred">
					        <span>当日微信交易笔数：</span><em><c:if test="${not empty wxPaySum }">${wxPaySum }</c:if><c:if test="${empty wxPaySum }">0</c:if> </em>         
					         </li>
					         <li class="cred">
					        <span>当日支付宝交易笔数：</span> <em><c:if test="${not empty aliPaySum }">${aliPaySum }</c:if><c:if test="${empty aliPaySum }">0</c:if></em>        
					         </li>
					         <li class="cred">
					        <span>当日银联交易笔数：</span> <em><c:if test="${not empty unionSum }">${unionSum }</c:if><c:if test="${empty unionSum }">0</c:if></em>        
					         </li>
					         <li class="cred">
					        <span>交易总笔数：</span>  <em><c:if test="${not empty sumNumber }">${sumNumber }</c:if><c:if test="${empty sumNumber }">0</c:if></em>        
					         </li>
					          <li class="cred">
					        <span>当日微信交易金额：</span><em>￥<c:if test="${not empty weixmoney }">${weixmoney }</c:if><c:if test="${empty weixmoney }">0</c:if></em>      
					         </li>
					          <li class="cred">
					        <span>当日支付宝交易金额：</span><em>￥<c:if test="${not empty zhifbaomoney }">${zhifbaomoney }</c:if><c:if test="${empty zhifbaomoney }">0</c:if></em>       
					         </li>
					          <li class="cred">
					        <span>当日银联交易金额：</span><em>￥<c:if test="${not empty unionmoney }">${unionmoney }</c:if><c:if test="${empty unionmoney }">0</c:if></em>       
					         </li>
					          <li class="cred">
					        <span>总金额：</span><em>￥<c:if test="${not empty summoney }">${summoney }</c:if><c:if test="${empty summoney }">0</c:if></em>
					         </li>

								<li class="cred">
									<span>总退款金额：</span><em>￥<c:if test="${not empty refundMoney }">${refundMoney }</c:if><c:if test="${empty refundMoney }">0</c:if></em>
								</li>
								<li class="cred">
									<span>总费率金额：</span><em>￥<c:if test="${not empty needRateMoney }">${needRateMoney }</c:if><c:if test="${empty needRateMoney }">0</c:if></em>
								</li>
								<li class="cred">
									<span>实际收入：</span><em>￥${summoney - refundMoney - needRateMoney }></em>
								</li>
					        </ul>
	
				        </div> 
			        </div>
			          
			   	 </section>
