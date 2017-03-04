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
 * �ʼ����͵Ĺ�����
 */
public class MailUtils {
	
	/**
	 * �����ʼ��ķ���
	 * @param to :�ռ���
	 * @param code :������
	 */
	public static void sendMail(String to, String code){
		/**
		 * 1.���һ��session����
		 * 2.����һ�������ʼ��Ķ���Message
		 * 3.�����ʼ�Transport
		 */
		//1.������Ӷ���
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@eyou.com", "111");
			}
			
		});
		
		//����һ���ʼ��Ķ���
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("service@eyou.com"));
			//�����ռ���
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//���ñ���
			message.setSubject("���Թ�����վ�Ĺٷ������ʼ�");
			//�����ʼ�������
			message.setContent("<h1>������վ�Ĺٷ������ʼ�������·���������˺ż������</h1><h3><a href='http://localhost:8080/TestAnnotation4/user_active.action?code="+code+"'>http://localhost:8080/TestAnnotation4/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//�����ʼ�
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
