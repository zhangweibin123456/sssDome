package webservice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import util.EnCnTwoWayTranslator;
import util.JaxbUtil;
import util.ResponseObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:wsGateway.xml" })
public class WsGatewaTest {

	@Autowired
	private WsGateway wsGateway;
	
	@Test
	public void test() {
//		String word = "hello";
//		String soap = "<getEnCnTwoWayTranslator xmlns=\"http://WebXml.com.cn/\">" +
//                "<Word>" + word + "</Word>" +
//                "</getEnCnTwoWayTranslator>" ;
//		Object obj=	wsGateway.sendMessage(soap);
//		ResponseObject r= JaxbUtil.converyToJavaBean(obj.toString(), ResponseObject.class);
//		System.out.println("-------------------------------------");
//		System.out.println(r.getEnCnTwoWayTranslator().getValue());
//		System.out.println("-------------------------------------");
		
		ResponseObject ro=new ResponseObject();
		
		EnCnTwoWayTranslator e=new EnCnTwoWayTranslator();
		e.setValue("liming");
		ro.setEnCnTwoWayTranslator(e);
		
		String str=JaxbUtil.convertToXml(ro);
		System.out.println(str);
		
	}
}
