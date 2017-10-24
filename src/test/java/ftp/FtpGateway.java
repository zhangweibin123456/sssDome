package ftp;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.integration.ftp.session.FtpFileInfo;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Component
public interface FtpGateway {
	
	public String upload(@Headers Map<String, Object> map,File file);
	
	public File download(@Headers Map<String, Object> map,String payload);
	
	public List<FtpFileInfo> fileList(String payload);
	
	public Boolean remove(String payload);
	
	public Boolean rename(@Headers Map<String, Object> map,String payload);
	
	
}
