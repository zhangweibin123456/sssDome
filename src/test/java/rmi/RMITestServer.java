package rmi;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:rmi/rmi-server.xml" })
public class RMITestServer {

	@Test
	public void Test01() throws InterruptedException {
		
		Thread.sleep(10000000);
	}

}
