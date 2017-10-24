package mail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mail/mail-context.xml" })
public class TemplateEngineServiceImplTest {

	@Autowired
	private MailService mailService;
	
	
	@Test
	public void simpleTextMail() throws Exception {
		MimeMessageHelper helper = mailService.createMessageHelper();
		Map<String,Object> headers = new HashMap<String,Object>();
		headers.put(MailHeaders.FROM,"1215402223@qq.com");
		headers.put(MailHeaders.TO,"1842198552@qq.com");
		headers.put(MailHeaders.SUBJECT,"主题");
		String text = "文本";
		helper.setText(text, true);
		mailService.sendMail(headers, helper);
	}
}