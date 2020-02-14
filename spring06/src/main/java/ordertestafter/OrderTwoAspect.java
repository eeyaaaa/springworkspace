package ordertestafter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

@Aspect
public class OrderTwoAspect implements Ordered {
	@After("execution(void *.run())")
	public void printOrder(JoinPoint jp) {
		System.out.println("before:order 2");
	}

	@Override
	public int getOrder() {
		return 2;
	}

	
}
