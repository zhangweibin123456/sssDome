package tcp;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.util.TestingUtilities;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:tcp/tcpClientServerDemo-context.xml" })
public class TtpGatewaTest {

	@Autowired
	private SimpleGateway simpleGateway;


	@Autowired
	private AbstractServerConnectionFactory crLfServer;
	
	
	@Before
	public void setup() {
		TestingUtilities.waitListening(this.crLfServer, 10000L);
	}
	
	@Test
	public void Test() {
		System.out.println("*********************start**************************");
		String result =simpleGateway.send("hashaishai");
		System.out.println(result);
		System.out.println("*********************end**************************");
	}

	
}
