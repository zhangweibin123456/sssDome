package sftp;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.sftp.session.SftpFileInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:sftp/spring-sftp.xml" })
public class SftpGatewaTest {

	@Autowired
	private SftpGateway sftpGateway;

	@Test
	public void testUpload() {
		Map<String, Object> map = new HashMap<String, Object>();
		File file = new File("D:/file.xml");
		map.put("directory", "/root/aaa/bbb");
		String filePath = sftpGateway.upload(map, file);
		System.out.println(filePath);

	}

	@Test
	public void testDownload() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("localDirectory", "D:/ftp");
		File file = sftpGateway.download(map, "/root/aaa/bbb/file.xml");
		System.out.println(file.getPath());

	}

	@Test
	public void testRemove() {
		String payload = "/root/aaa/bbb/file.xml";
		Boolean bool = sftpGateway.remove(payload);
		System.out.println(bool);
	}

	@Test
	public void testList() {
		String payload = "/root/aaa/bbb";
		List<SftpFileInfo> fileList = sftpGateway.fileList(payload);
		System.out.println(fileList.size());
		for (SftpFileInfo sftp : fileList) {
			System.out.println(sftp.getFilename());
			System.out.println(sftp.getModified());
			System.out.println(sftp.getPermissions());
			System.out.println(sftp.getRemoteDirectory());
			System.out.println(sftp.getSize());
		}
	}

	@Test
	public void testRename() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newFileName", "/root/aaa/bbb/2.xml");
		String payload = "/root/aaa/bbb/file.xml";
		Boolean bool = sftpGateway.rename(map, payload);
		System.out.println(bool);
	}

}
