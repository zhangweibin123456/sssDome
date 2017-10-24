package jms;

import javax.jms.JMSException;  
import javax.jms.TextMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.JmsException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:jms/subscribe/subscribe.xml" })
public class SimpleJMSReceiver {  
	
	@Test
	public void Test01() throws InterruptedException{
		Thread.sleep(1000000);
	}
      
    public void receive(TextMessage message) throws JmsException, JMSException {  
        System.out.println(message.getStringProperty("phrCode"));  
        System.out.println(message.getText());  
    }  
}