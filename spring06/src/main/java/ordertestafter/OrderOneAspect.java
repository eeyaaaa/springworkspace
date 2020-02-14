package ordertestafter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class OrderOneAspect {
	@After("execution(void *.run())")
	public void printOrder(JoinPoint jp) {
		System.out.println("before:order 1");
	}
}
