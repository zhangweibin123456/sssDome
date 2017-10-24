package jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:jms/jms.xml" })
public class JmsGatewaTest {

	@Autowired
	private JmsGateway jmsGateway;
	
	@Test
	public void test01() {
		System.out.println("--------------start------------------");
		String value= jmsGateway.transport("12121");
		System.out.println(value);
		System.out.println("---------------end-----------------");
	}

	
	@Test
	public void test02() {
		System.out.println("--------------start------------------");
		 jmsGateway.sead("12121");
		System.out.println("---------------end-----------------");
	}
	
}
