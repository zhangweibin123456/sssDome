package file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:file/file-copy.xml" })
public class FileGatewaTest {

	@Autowired
	private FileGateway fileGateway;

	@Test
	public void seatFileTest() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("directory", "D:/ftp/output");
		File sourceFile = new File("D:/ftp/input/a.txt");
		File targetFile = fileGateway.send(map, sourceFile);
		System.out.println("file name= "+targetFile.getName());
		System.out.println("file path= "+targetFile.getPath());
	}

}
