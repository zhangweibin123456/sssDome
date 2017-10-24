package jms;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * ActiveMQ工具类
 * @author D.H. Sue
 *
 */
public class MQUtil {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;  
		// Connection ：JMS 客户端到JMS Provider 的连接  
		  Connection connection = null;  
		// Session： 一个发送或接收消息的线程  
		Session session;  
		// Destination ：消息的目的地;消息发送给谁.  
		Destination destination1;
		Destination destination2;
		// 消费者，消息接收者  
		MessageConsumer consumer = null; 
		MessageProducer producer =null;
		connectionFactory = new ActiveMQConnectionFactory(  
		        "admin","admin","tcp://localhost:61616");  
		try {  
		    // 构造从工厂得到连接对象  
		    connection = connectionFactory.createConnection();  
		    // 启动  
		    connection.start();  
		    // 获取操作连接  
		    session = connection.createSession(Boolean.FALSE,  
		            Session.AUTO_ACKNOWLEDGE);  
		    // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
		    destination1 = session.createQueue("queue.demo");  
		    consumer = session.createConsumer(destination1); 
		    
		    destination2=session.createQueue("queue.reply");
		    producer=session.createProducer(destination2);
		    
		    while (true) {  
		        TextMessage message = (TextMessage) consumer.receive();  
		        if (null != message) {  
		        	TextMessage newmessage = session.createTextMessage(message.getText()+"response");
		            System.out.println("----------服务端  start----------");  
		            System.out.println(message.getText()); 
		            System.out.println("----------服务端  end----------"); 
		            
		            newmessage.setJMSReplyTo(destination2);
		            newmessage.setJMSCorrelationID(message.getJMSMessageID());
		            producer.send(newmessage);
		   } else {  
		            break;  
		        }  
		    }  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}   
	}
	
}