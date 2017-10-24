package jms;


import org.springframework.stereotype.Component;

@Component
public interface JmsGateway {

	public String transport(String payload);//有返回值
	
	public void sead(String payload);//无返回值
}
