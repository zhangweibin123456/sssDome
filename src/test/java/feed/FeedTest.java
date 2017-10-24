package feed;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rometools.rome.feed.synd.SyndEntry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:feed/feed.xml" })
public class FeedTest {

	@Autowired
	@Qualifier("feedChannel")
	private  PollableChannel feedChannel;

	@Test
	@SuppressWarnings("unchecked")
	public void feedTest() {

		for (int i = 0; i < 10; i++) {
			
			Message<SyndEntry> message = (Message<SyndEntry>) feedChannel.receive(1000);
			if (message != null){
				SyndEntry entry = message.getPayload();
				System.out.println(entry.getPublishedDate() + " - " + entry.getTitle());
				System.out.println(entry.getUri() );
			}
			else {
				break;
			}
		}
		
		
	}


}
