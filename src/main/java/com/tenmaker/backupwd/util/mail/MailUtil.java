package com.tenmaker.backupwd.util.mail;


import com.tenmaker.backupwd.components.CommonConst;
import com.tenmaker.backupwd.util.Base64Util;

public class MailUtil {
	public static void sendVerifyEmail(Long emailId, String verifyKey,
			String email) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();

		mailInfo.setUserName(CommonConst.mail_name);
		mailInfo.setPassword(CommonConst.mail_pwd);// 您的邮箱密码
		mailInfo.setFromAddress(CommonConst.mail_name);

		mailInfo.setMailServerHost("smtp.exmail.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);

		mailInfo.setToAddress(email);
		mailInfo.setSubject("来自红天果的邮箱验证");
		String siteURL = CommonConst.SITEURL
				+ "/passport/security/bindEmail?url=";
		String tempURL = "email/" + emailId;
		String temp_base64 = Base64Util.encodeStr(tempURL);
		siteURL = siteURL + temp_base64 + "&verify_key=" + verifyKey;

		StringBuilder builder = new StringBuilder();
		builder.append("<html><head>");
		builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		builder.append("</head><body>");
		builder.append("请点击邮箱地址验证：<br />");
		builder.append("\t如果打不开链接，请复制到浏览器的地址栏中按回车键<br />");
		builder.append("<a href=\"");
		builder.append(siteURL);
		builder.append("\">");
		builder.append(siteURL);
		builder.append("</a>");
		builder.append("</body></html>");
		String htmlContent = builder.toString();
		mailInfo.setContent(htmlContent);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);// 发送文体格式
		sms.sendHtmlMail(mailInfo);// 发送html格式
	}

	public static void sendPwdEmail(String email, String secKey, Long accountId) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();

		mailInfo.setUserName(CommonConst.mail_name);
		mailInfo.setPassword(CommonConst.mail_pwd);// 您的邮箱密码
		mailInfo.setFromAddress(CommonConst.mail_name);

		mailInfo.setMailServerHost("smtp.exmail.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);

		mailInfo.setToAddress(email);
		mailInfo.setSubject("来自红天果的密码修改");
		String siteURL = CommonConst.SITEURL + "/forget_new.html?securityId="
				+ accountId + "&secKey=" + secKey;

		mailInfo.setContent("如果您点下方链接无效，请将链接复制到浏览器地址栏中访问\n" + siteURL);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		// sms.sendHtmlMail(mailInfo);//发送html格式
	}

	public static void sendMail(String email, String subject, String content) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();

		mailInfo.setUserName(CommonConst.mail_name);
		mailInfo.setPassword(CommonConst.mail_pwd);// 您的邮箱密码
		mailInfo.setFromAddress(CommonConst.mail_name);

		mailInfo.setMailServerHost("smtp.exmail.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);

		mailInfo.setToAddress(email);
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		// sms.sendHtmlMail(mailInfo);//发送html格式
	}

	public static void main(String[] args) {
		// sendVerifyEmail(1L, "kevin@nutin.me");
	}

}
