package ReceiveInstantMessageSample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xmpp/ReceiveInstantMessageSample-context.xml" })
public class ReceiveInstantMessageSample {

	@Test
	public void runDemo() throws Exception {
		Thread.sleep(20000 * 10);
	}
}
