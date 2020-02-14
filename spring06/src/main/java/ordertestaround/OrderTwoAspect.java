package ordertestaround;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

@Aspect
public class OrderTwoAspect implements Ordered {
	@Around("execution(void *.run())")
	public void printOrder(ProceedingJoinPoint jp) {
		try {
			System.out.println("before:order 2");
			jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("after:order 2");
	}

	@Override
	public int getOrder() {
		return 2;
	}

	
}
