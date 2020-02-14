package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect2;
import chap06.Calculator;
import chap06.ImplCalculator;
import chap06.RecCalculator;

@Configuration
@EnableAspectJAutoProxy
//@EnableAspectJAutoProxy(proxyTargetClass=true)
public class JavaConfig2 {
	
	@Bean
	public ExeTimeAspect2 exeTimeAspect2(){
		return new ExeTimeAspect2();
	}
	
//	@Bean
//	public CacheAspect2 cacheAspect2(){
//		return new CacheAspect2();
//	}
	
	@Bean
	public Calculator implCal(){
		return new ImplCalculator();
	}
	
	@Bean
	public Calculator recCal(){
		return new RecCalculator();
	}
}
