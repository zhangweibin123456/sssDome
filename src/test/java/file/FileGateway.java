package file;

import java.io.File;
import java.util.Map;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Component
public interface FileGateway {

	public File send(@Headers Map<String, Object> map,File file);
	
}
