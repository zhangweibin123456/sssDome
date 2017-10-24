package initializing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:initializing/applicationContext.xml" })
public class InitializingTest {
	
//	@Autowired
//	private Test01Interface test01Interface;
//	
//	@Autowired
//	private Person person;
	
	@Test
	public void test() {
	        ApplicationContext factory = new ClassPathXmlApplicationContext("initializing/applicationContext.xml");
	        System.out.println("容器初始化成功");    
	        //得到Preson，并使用
	        Person person = factory.getBean("person",Person.class);
	        System.out.println(person);
	        
	        System.out.println("现在开始关闭容器！");
	        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
		
	}

}
