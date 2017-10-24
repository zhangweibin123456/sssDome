package util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book" )
@XmlType(propOrder = { "name","age" })
public class Book {

	private String name;
	
	private String age;

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="age")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
