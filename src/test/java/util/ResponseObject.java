package util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getEnCnTwoWayTranslatorResponse" )
@XmlType(propOrder = { "enCnTwoWayTranslator" })
public class ResponseObject  {

	private EnCnTwoWayTranslator enCnTwoWayTranslator;

	@XmlElement(name = "getEnCnTwoWayTranslatorResult")
	public EnCnTwoWayTranslator getEnCnTwoWayTranslator() {
		return enCnTwoWayTranslator;
	}

	public void setEnCnTwoWayTranslator(EnCnTwoWayTranslator enCnTwoWayTranslator) {
		this.enCnTwoWayTranslator = enCnTwoWayTranslator;
	}
	
}

