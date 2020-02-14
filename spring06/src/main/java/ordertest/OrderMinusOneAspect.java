package ordertest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(4)
public class OrderMinusOneAspect {
	@Around("execution(void *.run())")
	public void printOrder(ProceedingJoinPoint jp) {
		System.out.println("order -1");
		long val = System.nanoTime();
		try {
			
			jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
