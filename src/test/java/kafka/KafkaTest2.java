package kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:kafka/kafka-consumer.xml" })
public class KafkaTest2 {

	@Test
	public void testTemplateSend() throws InterruptedException {
		Thread.sleep(300000000);

	}

}
