package com.secray.unit;

import com.secray.utils.common.RegUtil;
import com.secray.utils.common.SysUtil;

import java.net.UnknownHostException;

public class IPCTest {

	public static void testIPC()
	{
		String src="<div class=\"zTlrwrap ml10 fl\">\n" + "                 <ul class=\"zTlrCont\"><li class=\"fz16 bg-blue08 LI7 clearfix ptb5\"><p class=\"YaHei col-gray03 fl pl15 ptb5\">域名 163.com 查询结果如下 <a href=\"http://www.juming.com/?tt=126777\" target=\"_blank\" rel=\"nofollow\" class=\"col-red\">域名抢注</a></p></li>" +
				"<li class=\"bor-b1s clearfix\"><div class=\"fl zTContleft\">域名年龄</div><div class=\"fr zTContrig\"><span>19年268天 (近似值)</span></div></li>" +
				"<li class=\"bor-b1s bg-list clearfix\"><div class=\"fl zTContleft\">域名状态</div><div class=\"fr zTContrig clearfix\"><p class=\"lh30 pr tip-sh\"><span>客户端设置禁止删除(<a href=\"http://www.icann.org/epp#clientDeleteProhibited\" rel=\"nofollow\" target=\"_blank\">clientDeleteProhibited</a>)</span><i class=\"QhintCent autohide\">保护域名的一种状态，域名不能被删除</i></p><p class=\"lh30 pr tip-sh\"><span>客户端设置禁止转移(<a href=\"http://www.icann.org/epp#clientTransferProhibited\" rel=\"nofollow\" target=\"_blank\">clientTransferProhibited</a>)</span><i class=\"QhintCent autohide\">保护域名的一种状态，域名不能转移注册商</i></p><p class=\"lh30 pr tip-sh\"><span>客户端设置禁止更新(<a href=\"http://www.icann.org/epp#clientUpdateProhibited\" rel=\"nofollow\" target=\"_blank\">clientUpdateProhibited</a>)</span><i class=\"QhintCent autohide\">域名信息，包括注册人/管理联系人/技术联系人/付费联系人/DNS等不能被修改，但可设置或修改解析记录</i></p><p class=\"lh30 pr tip-sh\"><span>域名服务商设置禁止删除(<a href=\"http://www.icann.org/epp#serverDeleteProhibited\" rel=\"nofollow\" target=\"_blank\">serverDeleteProhibited</a>)</span><i class=\"QhintCent autohide\">保护域名的一种状态，域名不能被删除</i></p><p class=\"lh30 pr tip-sh\"><span>域名服务商设置禁止转移(<a href=\"http://www.icann.org/epp#serverTransferProhibited\" rel=\"nofollow\" target=\"_blank\">serverTransferProhibited</a>)</span><i class=\"QhintCent autohide\">保护域名的一种状态，域名不能转移注册商；有的域名新注册及转移注册商60天内会被注册局设置该状态，60天后自动解除，有的则为域名涉及仲裁或诉讼案被注册局设置，仲裁或诉讼案结束会被解除</i></p><p class=\"lh30 pr tip-sh\"><span>域名服务商设置禁止更新(<a href=\"http://www.icann.org/epp#serverUpdateProhibited\" rel=\"nofollow\" target=\"_blank\">serverUpdateProhibited</a>)</span><i class=\"QhintCent autohide\">域名信息，包括注册人/管理联系人/技术联系人/付费联系人/DNS等不能被修改，但可设置或修改解析记录</i></p></div></li>" +
				"<li class=\"bor-b1s clearfix\"><div class=\"fl zTContleft\">域名创建时间</div><div class=\"fr zTContrig\"><span>1997-09-15</span></div></li><li class=\"bor-b1s bg-list clearfix\"><div class=\"fl zTContleft\">域名到期时间</div><div class=\"fr zTContrig\"><span>2018-09-14</span></div></li><li class=\"bor-b1s clearfix\"><div class=\"fl zTContleft\">域名删除时间</div><div class=\"fr zTContrig\"><span>2018-11-28</span></div></li><li class=\"bg-list clearfix\"><div class=\"fl zTContleft\"><span class=\"col-hint\">删除倒计时</span></div><div class=\"fr zTContrig\"><span class=\"col-hint\">还剩：540天 7小时 10分 42秒 （删除倒计时仅供参考）</span></div></li></ul> \n" + "             </div>";
		String expireReg = "<ul\\s+class=\"zTlrCont\">([\\s\\S]*?)</ul>";
		String[] st = RegUtil.getRegContent(src, expireReg,1,"@");
		for(String s :st){
			expireReg = "<li[^>]*><div\\s+class=\"fl\\s+zTContleft\">([\\s\\S]*?)</div>" +
					"<div\\s+class=\"fr\\s+zTContrig\"><span>([\\s\\S]*?)</span></div></li>";





		    System.out.println("lb : "+s);
			String lb=   "<li class=\"bor-b1s bg-list clearfix\"><div class=\"fl zTContleft\">域名状态</div><div class=\"fr zTContrig clearfix\"><p class=\"lh30 pr tip-sh\"><span>客户端设置禁止删除(<a href=\"http://www.icann.org/epp#clientDeleteProhibited\" rel=\"nofollow\" target=\"_blank\">clientDeleteProhibited</a>)</span><i class=\"QhintCent autohide\">保护域名的一种状态，域名不能被删除</i></p><p class=\"lh30 pr tip-sh\"><span>客户端设置禁止转移(<a href=\"http://www.icann.org/epp#clientTransferProhibited\" rel=\"nofollow\" target=\"_blank\">clientTransferProhibited</a>)</span><i class=\"QhintCent autohide\">保护域名的一种状态，域名不能转移注册商</i></p><p class=\"lh30 pr tip-sh\"><span>客户端设置禁止更新(<a href=\"http://www.icann.org/epp#clientUpdateProhibited\" rel=\"nofollow\" target=\"_blank\">clientUpdateProhibited</a>)</span><i class=\"QhintCent autohide\">域名信息，包括注册人/管理联系人/技术联系人/付费联系人/DNS等不能被修改，但可设置或修改解析记录</i></p><p class=\"lh30 pr tip-sh\"><span>域名服务商设置禁止删除(<a href=\"http://www.icann.org/epp#serverDeleteProhibited\" rel=\"nofollow\" target=\"_blank\">serverDeleteProhibited</a>)</span><i class=\"QhintCent autohide\">保护域名的一种状态，域名不能被删除</i></p><p class=\"lh30 pr tip-sh\"><span>域名服务商设置禁止转移(<a href=\"http://www.icann.org/epp#serverTransferProhibited\" rel=\"nofollow\" target=\"_blank\">serverTransferProhibited</a>)</span><i class=\"QhintCent autohide\">保护域名的一种状态，域名不能转移注册商；有的域名新注册及转移注册商60天内会被注册局设置该状态，60天后自动解除，有的则为域名涉及仲裁或诉讼案被注册局设置，仲裁或诉讼案结束会被解除</i></p><p class=\"lh30 pr tip-sh\"><span>域名服务商设置禁止更新(<a href=\"http://www.icann.org/epp#serverUpdateProhibited\" rel=\"nofollow\" target=\"_blank\">serverUpdateProhibited</a>)</span><i class=\"QhintCent autohide\">域名信息，包括注册人/管理联系人/技术联系人/付费联系人/DNS等不能被修改，但可设置或修改解析记录</i></p></div></li>" +
					"<li class=\"bor-b1s clearfix\"><div class=\"fl zTContleft\">域名创建时间</div><div class=\"fr zTContrig\"><span>1997-09-15</span></div></li>"+
					"<li class=\"bor-b1s clearfix\"><div class=\"fl zTContleft\">域名创建时间</div><div class=\"fr zTContrig\"><span>1997-09-15</span></div></li><li class=\"bor-b1s bg-list clearfix\"><div class=\"fl zTContleft\">域名到期时间</div><div class=\"fr zTContrig\"><span>2018-09-14</span></div></li><li class=\"bor-b1s clearfix\"><div class=\"fl zTContleft\">域名删除时间</div><div class=\"fr zTContrig\"><span>2018-11-28</span></div></li><li class=\"bg-list clearfix\"><div class=\"fl zTContleft\"><span class=\"col-hint\">删除倒计时</span></div><div class=\"fr zTContrig\"><span class=\"col-hint\">还剩：540天 7小时 10分 42秒 （删除倒计时仅供参考）</span></div></li></ul> \n" + "             </div>";
			String[] sts = RegUtil.getRegContent(s, expireReg,2,"@");
			for(String ss :sts){
				System.out.println("lb1 : "+ss);
			}
		}
	}

	public static void main(String[] args) {
		IPCTest.testIPC();
		//  int i = (int)(Math.random()*10);
	//	int number = new Random().nextInt(10) + 1;
	//	 System.out.println(i);

		try {
			System.out.println(SysUtil.getIpAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
