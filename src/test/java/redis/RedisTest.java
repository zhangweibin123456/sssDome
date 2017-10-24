package redis;



import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis/redis.xml")
public class RedisTest {
	
	
	@Autowired
	private RedisTemplate<String, User> redisTemplate ;
	
	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
	
	@Resource(name="redisTemplate")
	private SetOperations<String, String> setOps;
	
	@Resource(name="redisTemplate")
	private HashOperations<String,String,String> mapOps;

	@Resource(name="redisTemplate")
	private ZSetOperations<String,String> zSetOps;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String,User> valueOps;
	
	
	@Test
	public void testList() {
		System.out.println("listOps "+listOps);
		
		//setOps.add("a", "b");
		listOps.leftPush("1", "2");
		//listOps.leftPush("2", "v3");
		
	}

	@Test
	public void testSet() {
		System.out.println("setOps "+setOps);
		setOps.add("a", "b");
	}
	
	@Test
	public void testMap() {
		System.out.println("mapOps "+mapOps);
		mapOps.put("v", "d", "f");
		
	}
	
	@Test
	public void testZSet() {
		System.out.println("zSetOps "+zSetOps);
		zSetOps.add("key1", "value1", 1);
		zSetOps.add("key1", "value2", 2);
		
	}
	
	/**
	 * String 
	 */
	@Test
	public void testValue() {
		System.out.println(redisTemplate);
		//保存
		//valueOps.append("a", "asasasas");
		//查询
		//System.out.println(valueOps.get("a"));
		//值字符串截取  
		//System.out.println(valueOps.get("a", 0, 2));
		//valueOps.set("b", "sdsadsadsa");
//		User user=new User();
//		user.setId("1");
//		user.setName("zhang");
		ValueOperations<String, User> valueops1 =redisTemplate.opsForValue();
		//valueops1.set(user.getId(), user);
		User user=valueops1.get("1");
		System.out.println(user.toString());
	}
	

	@Test
	public void testOther() {
		HashOperations<String, String, User> map=redisTemplate.opsForHash();
		
//		User user1=new User();
//		user1.setId("1");
//		user1.setName("张三");
//		
//		User user2=new User();
//		user2.setId("2");
//		user2.setName("李四");
//		
//		map.put("a", user1.getId(), user1);
//		map.put("a", user2.getId(), user2);
		User u=  map.get("a", "1");
		System.out.println(u);		
		
		
	}
	

	@Test
	public void testFuzzyQuery() {
//		zSetOps.add("abc", "张三", 0);
//		zSetOps.add("abcd", "张四", 0);
//		zSetOps.add("abcde", "张五", 0);
//		zSetOps.add("bcbc", "张六", 0);
		
		Set<String> set=	zSetOps.reverseRange("abc", 0, 3);
		for(String s:set){
			System.out.println(s);
		}
		System.out.println(set.size());
		
	}
	
	
	
}
