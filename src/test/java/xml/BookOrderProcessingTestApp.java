package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

public class BookOrderProcessingTestApp {

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/META-INF/spring/integration/orderProcessingSample.xml", BookOrderProcessingTestApp.class);
		MessageChannel messageChannel = (MessageChannel) applicationContext.getBean("ordersChannel");
		GenericMessage<Document> orderMessage = createXmlMessageFromResource("META-INF/spring/integration/order.xml");
		messageChannel.send(orderMessage);
		applicationContext.close();
	}

	private static GenericMessage<Document> createXmlMessageFromResource(String path) throws Exception {
		Resource orderRes = new ClassPathResource(path);

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = builderFactory.newDocumentBuilder();

		Document orderDoc = builder.parse(orderRes.getInputStream());
		return new GenericMessage<Document>(orderDoc);
	}

}