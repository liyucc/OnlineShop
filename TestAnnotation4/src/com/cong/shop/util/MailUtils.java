package com.cong.shop.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Administrator
 * 邮件发送的工具类
 */
public class MailUtils {
	
	/**
	 * 发送邮件的方法
	 * @param to :收件人
	 * @param code :激活码
	 */
	public static void sendMail(String to, String code){
		/**
		 * 1.获得一个session对象
		 * 2.创建一个代理邮件的对象Message
		 * 3.发送邮件Transport
		 */
		//1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@eyou.com", "111");
			}
			
		});
		
		//创建一个邮件的对象
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("service@eyou.com"));
			//设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//设置标题
			message.setSubject("来自购物网站的官方激活邮件");
			//设置邮件的正文
			message.setContent("<h1>购物网站的官方激活邮件！点击下方链接完成账号激活操作</h1><h3><a href='http://localhost:8080/TestAnnotation4/user_active.action?code="+code+"'>http://localhost:8080/TestAnnotation4/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//发送邮件
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		sendMail("aaa@eyou.com", "11111111111111");
	}
}
