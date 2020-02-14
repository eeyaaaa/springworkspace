package ordertestbefore;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

@Aspect
public class OrderTwoAspect implements Ordered {
	@Before("execution(void *.run())")
	public void printOrder(JoinPoint jp) {
		System.out.println("before:order 2");
	}

	@Override
	public int getOrder() {
		return 2;
	}

	
}
