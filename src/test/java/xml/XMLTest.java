package xml;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/orderProcessingSample.xml" })
public class XMLTest {

	@Autowired
	private MessageChannel ordersChannel;
	
	@Test
	public void test() throws Exception {
		
		
//		Resource orderRes = new ClassPathResource("xml/order.xml");
//		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//		builderFactory.setNamespaceAware(true);
//		DocumentBuilder builder = builderFactory.newDocumentBuilder();
//		Document orderDoc = builder.parse(orderRes.getInputStream());
//		GenericMessage<Document> orderMessage=new GenericMessage<Document>(orderDoc);
//		ordersChannel.send(orderMessage);
	}
	
}
