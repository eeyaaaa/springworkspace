package ordertestafter;

import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.aop.framework.ProxyConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class MainBeans {
	@Bean
	public ProxyConfig proxyCreator() {
		return new AspectJAwareAdvisorAutoProxyCreator();
	}

	@Bean
	public Runnable main() {
		return new Runnable() {
			public void run() {
				System.out.println("target executed");
			}
		};
	}
}
