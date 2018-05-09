package com.yc.q1.core.util;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.*;

/**  
* <p>Title: EmailUtils.java</p>  
* <p>Description: </p>  
* @author tongzy  
* @date 2018年5月9日  
* @version 1.0  
*/
public class EmailUtils {

	private String myEmailSMTPHost = ""; // smtp服务器
	private String myEmailAccount = ""; // 发件人地址
	private String myEmailPassword = ""; // 密码
	private String sendName = ""; // 用户名

	private String receiveMailAccount = ""; // 收件人地址
	private String affix = ""; // 附件地址
	private String affixName = ""; // 附件名称

	private String subject = ""; // 邮件标题
	private String content = ""; // 正文

	public void setAddress(String myEmailAccount, String receiveMailAccount, String subject) {
		this.myEmailAccount = myEmailAccount;
		this.receiveMailAccount = receiveMailAccount;
		this.subject = subject;
	}

	public void setAffix(String affix, String affixName) {
		this.affix = affix;
		this.affixName = affixName;
	}

	public void setContent( String content) {
		this.content = content;
	}

	public void send(String myEmailSMTPHost, String sendName, String myEmailPassword, boolean isFile) {
		this.myEmailSMTPHost = myEmailSMTPHost;
		this.sendName = sendName;
		this.myEmailPassword = myEmailPassword;

		Properties props = new Properties();

		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		props.put("mail.smtp.host", myEmailSMTPHost);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
		props.put("mail.smtp.auth", "true");

		// 用刚刚设置好的props对象构建一个session
		Session session = Session.getDefaultInstance(props);

		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
		session.setDebug(true);

		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(myEmailAccount));
			// 加载收件人地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMailAccount));
			// 加载标题
			message.setSubject(subject);

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setText(content);
			multipart.addBodyPart(contentPart);
			if (isFile) {
				// 添加附件
				BodyPart messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(affix);
				// 添加附件的内容
				messageBodyPart.setDataHandler(new DataHandler(source));
				// 添加附件的标题
				// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
				sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
				messageBodyPart.setFileName("=?GBK?B?" + enc.encode(affixName.getBytes()) + "?=");
				multipart.addBodyPart(messageBodyPart);
			}

			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			// 连接服务器的邮箱
			transport.connect(myEmailSMTPHost, sendName, myEmailPassword);
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EmailUtils cn = new EmailUtils();
		// 设置发件人地址、收件人地址和邮件标题
		cn.setAddress("*****@163.com", "*****@qq.com", "标题");
		
		//设置要发送附件的位置和标题
		cn.setAffix("f:/123.txt", "123.txt");
		
		//设置正文
		cn.setContent("最后一次");

		/**
		 * 设置smtp服务器以及邮箱的帐号和密码 用QQ 邮箱作为发生者不好使 （原因不明） 163 邮箱可以，但是必须开启 POP3/SMTP服务
		 * 和 IMAP/SMTP服务 因为程序属于第三方登录，所以登录密码必须使用163的授权码
		 */

		// 注意： [授权码和你平时登录的密码是不一样的]
		cn.send("smtp.163.com", "*****@163.com", "*****", true);

	}

}
