package xml;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class ExternalResupply {
	private static Logger logger = Logger.getLogger(ExternalResupply.class);

	public void orderResupply(Document resupplyOrder) {
		logger.info("Placing resupply order: \n" + XmlUtil.docAsString(resupplyOrder));
	}

}