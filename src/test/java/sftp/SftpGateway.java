package sftp;


import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.integration.sftp.session.SftpFileInfo;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Component
public interface SftpGateway {

	public String upload(@Headers Map<String, Object> map,File file);

	public File download(@Headers Map<String, Object> map,String payload);
	
	public List<SftpFileInfo> fileList(String payload);
	
	public Boolean remove(String payload);
	
	public Boolean rename(@Headers Map<String, Object> map,String payload);
	
}
