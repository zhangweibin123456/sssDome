package xml;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class WarehouseDispatch {
	private static Logger logger = Logger.getLogger(WarehouseDispatch.class);

	public void dispatch(Document orderItem) {
		logger.info("Warehouse dispatching orderItem: \n" + XmlUtil.docAsString(orderItem));
	}

}
