package com.secray.unit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.secray.utils.common.RegUtil;

/** 
* ��  ��: RegUnitTest 
* ��  ��: TODO(������һ�仰��������������) 
* ������: root
* ��   ˾: ֣���Ŵ�������Ϣ���޹�˾
* ����ʱ��: 2017��3��22�� ����4:47:58 
*  
*/
public class RegUnitTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = " ��Ȩ���У�����ʡ������   ������ţ�ԥICP��08005136��<br />";
		a=">ԥICP��11012831</a>&nbsp;֣����";
		String[] st = RegUtil.getRegContent(a, RegUtil.GOV_HN_IPC,1,"@");
		for(String s :st){
			System.out.println("lb : "+s);
		}
		
	/*	a="  <a href=\"./index/dblm/wzsm/201306/t20130626_107529.html\">��վ����</a> "+
	     "| <a href=\"./index/dblm/wzdt/201307/t20130703_109561.html\">��վ��ͼ</a>"+
         "| <a href=\"./index/dblm/lxwm/201306/t20130626_107530.html\">��ϵ����</a>"+
         "| <a href=\"./index/dblm/xgxz/\" target=\"_blank\">�������</a>"+
         "| <a href=\"./index/dblm/dsj/\" target=\"_blank\">���¼�</a>";
		a="    <p align=\"justify\">&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ַ��<a href=\"http://www.jiyuan.gov.cn/\">http://www.jiyuan.gov.cn</a>&nbsp;&nbsp;</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ַ����Դ�лƺ�·����԰·�������200��·���ۺ�������������1��¥</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; �ࣺ459000</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ����0391-6633495</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; �棺0391-6633400</p>"+
            "<p align=\"justify\">&nbsp;&nbsp;&nbsp; �ල�绰��0391-6633418</p>";
		a = a.replaceAll("&nbsp;", "").replaceAll("\\s+", "");
		 st = RegUtil.getRegContent(a, RegUtil.GOV_HN_LINK_ADDRESS,1,"@");
		for(String s :st){
			System.out.println("lb : "+s);
		}
		
		a = "���죺�й���Դ��ί&nbsp;&nbsp;��Դ����������&nbsp;&nbsp;��Ȩ���н�ֹ˽��ת��<br /> "+
   	       "�а죺��Դ�е�������칫��&nbsp;&nbsp;��Դ��������������&nbsp;&nbsp;ԥICP��05001062 ";
		 st = RegUtil.getRegContent(a, RegUtil.GOV_HN_CREATOR,1,"@");
		for(String s :st){
			System.out.println("lbs : "+s);
		}*/
		
		a="a-b|cdf";
		st = a.split("[-|/|]");
		for(String s :st){
			System.out.println("lbs : "+s);
		}
	}

}
