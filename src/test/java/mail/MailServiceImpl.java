package mail;

import java.util.Map;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSessionSender;

	@Autowired
	@Qualifier("outboundMail")
	private MessageChannel messageChannel;
	
	
	@Override
	public MimeMessageHelper createMessageHelper(){
		return new MimeMessageHelper(mailSessionSender.createMimeMessage());
	}
	
	@Override
	public void sendMail(Map<String, Object> headers, MimeMessageHelper helper) {
		MessagingTemplate mt = new MessagingTemplate(messageChannel);
		MessageBuilder<MimeMessage> mb = MessageBuilder.withPayload(helper.getMimeMessage()).copyHeaders(headers);
		Message<MimeMessage> s = mb.build();
		mt.send(s);
	}

}
