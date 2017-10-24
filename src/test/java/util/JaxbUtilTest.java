package util;


import java.io.File;

import org.junit.Test;

public class JaxbUtilTest {

	@Test
	public void testBeanToXml() {
		Book book=new Book();
		book.setAge("12");
		book.setName("sdsd");
		String xmlStr=	JaxbUtil.convertToXml(book);
		System.out.println(xmlStr);
	}
	
	@Test
	public void testXmlToBean() {
		Book book=new Book();
		book.setAge("12");
		book.setName("sdsd");
		String xmlStr=	JaxbUtil.convertToXml(book);
		System.out.println(xmlStr);
		Book bookxml=JaxbUtil.converyToJavaBean(xmlStr, Book.class);
		System.out.println(bookxml.getAge()+" "+bookxml.getName());
	}
	@Test
	public void testXmlToBean1() {
		File destFile = new File("D:/sftp/abc.txt");
	  System.out.println(destFile.getParentFile().mkdirs()); 
	
	}
	
	

}
