package ordertestbefore;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class AspectOrderTest {
	public static void main(String[] args) throws Exception {
		GenericApplicationContext context =
				new AnnotationConfigApplicationContext(
						OrderZeroAspect.class,
						OrderMinusOneAspect.class, 
						OrderTwoAspect.class, 
						OrderOneAspect.class, 
						MainBeans.class);
		Runnable printer = context.getBean(Runnable.class);
		printer.run();
		System.out.println("=============================");
		Runnable printer2 = context.getBean(Runnable.class);
		printer2.run();
	}
}
