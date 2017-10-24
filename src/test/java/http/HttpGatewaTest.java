package http;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:http/httpGateway.xml" })
public class HttpGatewaTest {

	@Autowired
	private RequestGateway requestGateway;
	
	@Test
	public void test() {
		
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("urlPath", "http://localhost:8080/sssDome/testHttp/getStr");
		map.put("httpMethod", "POST");
		map.put("responseType", "java.lang.String");
		String response= requestGateway.echo(map,"456");
		
		System.out.println("什么玩意："+response);
		
//		map.put("urlPath", "http://localhost:8080/sssDome/testHttp/getFile");
//		map.put("httpMethod", "POST");
//		map.put("responseType", "java.io.File");
//		//String response= requestGateway.echo(map,"456");
//		File file= requestGateway.getFile(map, "456");
//		System.out.println(file);
	}

}
