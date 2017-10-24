package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-Aop.xml" })
public class AopTest {

	@Autowired
	private Compute compute;

	@Test
	public void test() {
		compute.add(1, 2);
		System.out.println("-----------------");
		compute.div(2, 1);
	}

}
