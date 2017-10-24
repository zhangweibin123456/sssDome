package mail;

import java.util.Map;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Headers;

public interface MailService {
	
	MimeMessageHelper createMessageHelper();

	void sendMail(@Headers Map<String, Object> headers, MimeMessageHelper helper);
}
