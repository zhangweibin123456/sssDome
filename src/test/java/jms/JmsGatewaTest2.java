package jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:jms/mq2.xml" })
public class JmsGatewaTest2 {

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(999999);
	}

}
