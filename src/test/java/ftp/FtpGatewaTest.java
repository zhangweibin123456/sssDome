package ftp;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ftp.session.FtpFileInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ftp/ftpGateway.xml" })
public class FtpGatewaTest {

	@Autowired
	private FtpGateway ftpGateway;

	@Test
	public void uploadTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("localDirectory", "/");
		map.put("remoteDirectory", "/");
		File file = new File("D:/1.jpg");
		String aa = ftpGateway.upload(map, file);
		System.out.println(aa);

	}

	@Test
	public void downloadTest() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("localDirectory", "D:/aa/aa");
		map.put("remoteDirectory", "/");
		String payload = "/1.jpg";
		File file = ftpGateway.download(map, payload);
		System.out.println(file.getName());

	}

	@Test
	public void fileListTest() {
		String payload = "/";
		List<FtpFileInfo> fileList = ftpGateway.fileList(payload);
		System.out.println(fileList.size());
		for (FtpFileInfo f : fileList) {
			System.out.println("f.getFilename()= " + f.getFilename());
			System.out.println("f.getFileInfo()= " + f.getFileInfo());
			System.out.println("f.getModified()= " + f.getModified());
			System.out.println("f.getPermissions()= " + f.getPermissions());
			System.out.println("f.getRemoteDirectory()= " + f.getRemoteDirectory());
			System.out.println("f.getSize()= " + f.getSize());
		}

	}

	@Test
	public void removeTest() {
		String payload = "/1.jpg";
		Boolean bool = ftpGateway.remove(payload);
		System.out.println(bool);
	}

	@Test
	public void RenameTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newFileName", "2.jpg");
		String payload = "/1.jpg";
		Boolean bool = ftpGateway.rename(map, payload);
		System.out.println(bool);
	}

}
