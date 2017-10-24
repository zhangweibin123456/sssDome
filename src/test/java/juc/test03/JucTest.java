package juc.test03;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class JucTest {

	private CountDownLatch latch = new CountDownLatch(1000);

	private class ExecuteThread implements Runnable {
		public void run() {
			try {
				latch.await();
			} catch (Exception e) {
			}
		}
	}

	@Test
	public void test01() {
		for (int i = 0; i < 1000; i++) {
			Thread t = new Thread(new ExecuteThread());
			t.start();
			System.out.println(t.getName() + " " + i);
		}
		latch.countDown();
	}

}
