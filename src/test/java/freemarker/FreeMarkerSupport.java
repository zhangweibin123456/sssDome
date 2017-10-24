package freemarker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerSupport {
	
	@Autowired
	@Qualifier("freeMarkerConfig")
	private Configuration configuration;
	
	public Template getTemplate(String name) {
		try {
			Template template = configuration.getTemplate(name);
			return template;
		} catch (Exception e) {
			
		}
		return null;
	}

}
