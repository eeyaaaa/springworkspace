package ordertest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(0)
public class OrderZeroAspect {
	@Around("execution(void *.run())")
	public void printOrder(ProceedingJoinPoint jp) {
		try {
			jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("order 0");
	}
}
