package tcp;

import org.springframework.stereotype.Component;

@Component
public interface SimpleGateway {

	public String send(String text);

}
