package ReceiveInstantMessageSample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xmpp/SendInstantMessageSample-context.xml" })
public class SendInstantMessageSample {

	@Autowired
	private MessageChannel toUserChannel;
	
	@Test
	public void runDemo() throws Exception{
		Message<String> message = new GenericMessage<String>("Hello from Spring Integration XMPP");
		toUserChannel.send(message);
	}
}