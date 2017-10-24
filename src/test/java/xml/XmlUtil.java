package xml;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;

import org.springframework.xml.transform.StringResult;

public class XmlUtil {

	public static String docAsString(Document doc) {
		try {
			StringResult res = new StringResult();
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(doc), res);
			return res.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
