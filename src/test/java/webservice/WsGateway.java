package webservice;


import org.springframework.stereotype.Component;

@Component
public interface WsGateway {
	public Object sendMessage(String payload);
}
