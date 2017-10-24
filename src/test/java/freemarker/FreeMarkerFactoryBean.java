package freemarker;

import java.io.File;

import org.springframework.beans.factory.FactoryBean;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerFactoryBean implements FactoryBean<Configuration> {

	@Override
	public Configuration getObject() throws Exception {
		String filePath="D:/workspace/sssDome/src/test/java/freemarker";
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File(filePath));
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		return configuration;
	}

	@Override
	public Class<Configuration> getObjectType() {
		return Configuration.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
