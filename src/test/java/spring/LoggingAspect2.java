package spring;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class LoggingAspect2 {
	
	
	/**
	 * 前置通知
	 */
	@Before("LoggingAspect.declareJointExpression()")
	public void beforeMethod2(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		Object[] arry = joinPoint.getArgs();
		System.out.println("beforeMethod2 name = " + name + " arry = " + Arrays.asList(arry));
	}

	
}
