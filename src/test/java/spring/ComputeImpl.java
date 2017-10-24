package spring;

import org.springframework.stereotype.Component;

@Component("compute")
public class ComputeImpl implements Compute {

	@Override
	public int add(int a, int b) {
		System.out.println("a= "+a +" b= "+b);
		return a+b;
	}

	@Override
	public int div(int a, int b) {
		System.out.println("a= "+a +" b= "+b);
		return a/b;
	}

}
