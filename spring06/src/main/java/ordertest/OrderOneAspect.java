package ordertest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class OrderOneAspect {
	@Around("execution(void *.run())")
	public void printOrder(ProceedingJoinPoint jp) {
		try {
			System.out.println("order 1");
			jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
