package jmx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:jmx/JmxAdapterDemo-context.xml" })
public class JmxGatewaTest {

	@Autowired
	private StopWatch stopWatch;
	
	@Test
	public void test() throws InterruptedException {
		System.out.println(stopWatch); 
		Thread.sleep(200000);
	
	}

}
