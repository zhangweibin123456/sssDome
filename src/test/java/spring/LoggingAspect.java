package spring;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Order(3)
@Aspect
@Component
public class LoggingAspect {
	
	/**
	 * 定义一个方法用于声明表达式(重用)
	 */
	@Pointcut("execution(public int spring.Compute.*(..))")
	public void declareJointExpression(){}
	
	
	
	/**
	 * 前置通知
	 */
	@Before("declareJointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		Object[] arry = joinPoint.getArgs();
		System.out.println("beforeMethod name = " + name + " arry = " + Arrays.asList(arry));
	}

	/**
	 * 后置通知
	 */
	@After("declareJointExpression()")
	public void afterMethod(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		Object[] arry = joinPoint.getArgs();
		System.out.println("afterMethod name = " + name + " arry = " + Arrays.asList(arry));
	}

	/**
	 * 返回通知
	 */
	@AfterReturning(value = "declareJointExpression()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String name = joinPoint.getSignature().getName();
		System.out.println("afterReturning name = " + name + " result = " + result);

	}

	/**
	 * 異常通知
	 */
	@AfterThrowing(value = "declareJointExpression()", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String name = joinPoint.getSignature().getName();
		System.out.println("afterThrowing name = " + name + " Exception = " + ex);
	}

	/**
	 * 环绕通知
	 */
	@Around("declareJointExpression()")
	public Object aroundMethod(ProceedingJoinPoint pjp) {

		Object obj = null;
		String name = pjp.getSignature().getName();
		try {
			System.out.println("前置通知 aroundMethod name= " + name);
			obj = pjp.proceed();
			System.out.println("返回通知 aroundMethod name= " + name);
		} catch (Throwable e) {
			System.out.println("异常通知 aroundMethod name= " + name);
			e.printStackTrace();
			throw new RuntimeException();
		}
		System.out.println("后置通知 aroundMethod name= " + name);
		return obj;
	}

}
