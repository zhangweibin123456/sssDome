package mail;

import java.util.Properties;

import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class TestSeadMail {

	@Test
	public void test01() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.qq.com");
		mailSender.setPort(465);
		mailSender.setUsername("1215402223@qq.com");
		mailSender.setPassword("villhdcjidlahijh");
		// 加认证机制
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.smtp.timeout", 5000);
		javaMailProperties.put("mail.transport.protocol", "smtps");
		javaMailProperties.put("mail.smtp.socketFactory.port", "465");
		javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailSender.setJavaMailProperties(javaMailProperties);
		// 创建邮件内容
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1215402223@qq.com");
		message.setTo("1842198552@qq.com");
		message.setSubject("主题");
		message.setText("文本");
		// 发送邮件
		mailSender.send(message);

	}

}
