package jms;


import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MutableMessage;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

//@Component  
public class MqMessageHandler {  
   // @ServiceActivator  
    public Message<String> handleMqMessage(Message<String> message) {  

    	Message<String> message1=new MutableMessage<String>(message.getPayload(),message.getHeaders());
    	System.out.println("Message="+message);
    	return message1;
    }  
}
