package ordertestbefore;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Aspect
@Order(0)
public class OrderZeroAspect {
	@Before("execution(void *.run())")
	public void printOrder(JoinPoint jp) {
		System.out.println("before:order 0");
		System.out.println("after:order 0");
	}
}
