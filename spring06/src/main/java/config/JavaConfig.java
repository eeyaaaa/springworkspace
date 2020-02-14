package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect2;
import chap06.Calculator;
import chap06.ImplCalculator;
import chap06.RecCalculator;


//@Configuration 어노테이션을 이용한 의존 설정 
@Configuration
@EnableAspectJAutoProxy  
// <aop:aspectj-autoproxy/> : xml파일에 작성햇던거 대신에 어노테이션을 사용하겠다는 뜻!
/*@EnableAspectJAutoProxy(proxy-target-class="true") 를 추가하게되면 
 * 프록시 객체 생성시 Bean객체가 상속받은 인터테이스 타입이 아닌  해당 Bean객체의 타입으로 프록시 객체를 생성하게된다  */
public class JavaConfig {
	
	@Bean
	public ExeTimeAspect2 exeTimeAspect(){
		return new ExeTimeAspect2();
	}
	
	@Bean
	public Calculator implCal(){
		return new ImplCalculator();
	}
	
	@Bean
	public Calculator recCal(){
		return new RecCalculator();
	}
}
