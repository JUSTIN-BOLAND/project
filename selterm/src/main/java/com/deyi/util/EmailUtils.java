package com.deyi.util;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


public class EmailUtils {

	
	/**
	 * 发送邮件类
	 * @param to 收件人
	 * @param title 邮件标题
	 * @param context   邮件内容
	 * void 
	 * @author Enzo
	 * @date 2016年10月24日
	 */
	public static void sendMail(String to, String title,String context) {
		try {
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

			
			// 设定mail server
			senderImpl.setHost("smtp.exmail.qq.com");

			// 建立邮件消息,发送简单邮件和html邮件的区别
			MimeMessage mailMessage = senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "UTF-8");
			String nick=javax.mail.internet.MimeUtility.encodeText("得壹开发邮箱"); 
			// 设置收件人，寄件人
			messageHelper.setTo(to);
			messageHelper.setFrom(nick+" <dev@5deyi.com>") ;
			messageHelper.setSubject(title);
			
			// true 表示启动HTML格式的邮件
			messageHelper.setText(context, true);

			senderImpl.setUsername("dev@5deyi.com"); // 根据自己的情况,设置username
			senderImpl.setPassword("Dev123"); // 根据自己的情况, 设置password
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", "false"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
			prop.put("mail.smtp.timeout", "25000");
			prop.put("mail.smtp.port", "25");
			senderImpl.setJavaMailProperties(prop);
			// 发送邮件

			senderImpl.send(mailMessage);

			System.out.println("邮件发送成功..");
		} catch (Exception ex) {
			System.out.println("邮件发送失败..");
			ex.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		sendMail("xiongqq@5deyi.com","这是一个测试邮件,请不要回复","这是个测试的账号的内容，如果显示则表示邮件发送成功");
	}
}
