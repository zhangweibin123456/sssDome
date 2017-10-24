package util;

import javax.xml.bind.annotation.XmlElement;

public class EnCnTwoWayTranslator {

	private String value;

	@XmlElement(name = "string")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
}
