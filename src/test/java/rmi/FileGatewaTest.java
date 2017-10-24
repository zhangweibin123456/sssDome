package rmi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:rmi/spring-rmi.xml"})
public class FileGatewaTest {

	@Autowired
	private HelloService helloService;

	@Test
	public void test01() {
	 String aa=	helloService.doHello("asas") ;
	 System.out.println(aa);
	}

}
