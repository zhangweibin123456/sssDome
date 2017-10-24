package initializing;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

public class Test01Impl implements Test01Interface, InitializingBean {

	private Map<String, String> map = new HashMap<String, String>();

	@Override
	public void afterPropertiesSet() throws Exception {
		map.put("key1", "value1");
		map.put("key2", "value2");
	}

	@Override
	public String getStr(String name) {
		System.out.println("map.size()" + map.size());
		System.out.println("name " + name);
		return name;
	}

}
