package freemarker;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import freemarker.template.Template;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:freemarker/applicationContext-freemarker.xml")
public class FreeMarkerFactoryBeanTest {
	
	@Autowired
	@Qualifier("freeMarkerSupport")
	private FreeMarkerSupport freeMarkerSupport;
	

	@Test
	public void test() throws Exception {
		/* Create a data-model */
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("user", "Big Joe");
        Product latest = new Product();
        latest.setUrl("products/greenmouse.html");
        latest.setName("green mouse");
        root.put("latestProduct", latest);
		Template template = freeMarkerSupport.getTemplate("example.ftlh");
//        Writer out = new OutputStreamWriter(System.out);
		 StringWriter writer = new StringWriter();    
          template.process(root, writer);
         System.out.println(writer.toString());
	}

}
