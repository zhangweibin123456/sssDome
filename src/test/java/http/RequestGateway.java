package http;

import java.io.File;
import java.util.Map;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Component
public interface RequestGateway {

	public String echo(@Headers Map<String, Object> map,String request);
	public File getFile(@Headers Map<String, Object> map,String request);
}
