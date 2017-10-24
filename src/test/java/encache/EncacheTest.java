package encache;


import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EncacheTest {

	private static CacheManager cacheManager;
	static String cacheName = "cache1";
	@Before
	public void testBefore()  {
		
		URL url = getClass().getResource("/ehcache.xml");
		//String ehcache="src/test/resources/ehcache.xml";
		cacheManager = CacheManager.create(url);
		cacheManager.addCache(cacheName);
		
	}

	@Test
	public void test() {
		Cache cache1 = cacheManager.getCache(cacheName);
		String key = "key1";
		String value = "value1";
		writeSomeData(cache1, key, value);
		Element element = readSomeData(cache1, key, value);
		System.out.println(element);

	}

	private static void writeSomeData(Cache cache, String key, String value) {
		cache.put(new Element(key, value));
	}

	private static Element readSomeData(Cache cache, String key, String value) {
		return cache.get(key);
	}

}
