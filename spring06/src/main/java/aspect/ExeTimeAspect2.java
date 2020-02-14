package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect 어노테이션을 이용한 공통기능을 제공하는 클래스 
@Aspect
public class ExeTimeAspect2 {
	
	
	//Around Advice의 Pointcut걸정으로 PublicTarget()메서드의 정의한 값을 사용한다
	@Pointcut("execution(public * chap06..*(..))")
	private void publicTarget() {
		
	}
	
	//Pointcut에 적용할 공통 기능으로 @Around를 적용한 measure()메서드를 사용한다
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();
			//Pointcut시점의 메서드가 호출되면 @Aroud애터테이션정의된 measure메서드가 호출되고
			//joinPoint.proceed()가 호출됬을때 호출햇던 메서드로 다시 돌아가게된다 implCal.factorial(5)
			return result;  //implCal.factorial(5)에서 return된 값이 다시 return된다.
		}
		finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s,%s(%s)실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(),Arrays.toString(joinPoint.getArgs()),
					(finish - start));
		}
	}

}
