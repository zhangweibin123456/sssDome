package rmi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:rmi/rmi-client.xml" })
public class RMITestClient{

	@Autowired
	private RmiProxyFactoryBean serviceProxy;
	
	@Test
	public void Test01() {
		HelloService helloService =	 (HelloService)serviceProxy.getObject();
		System.out.println(helloService.doHello("abc"));
	}

}
