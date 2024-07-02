package su.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import su.boot.begin.servlet.login.InterceptorLogin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
//스프링 프레임워크의 웹 MVC 설정을 지원한다.
@EnableWebMvc
//특정 패키지를 스캔하며 컴포넌트, 서비스, 리포지토리 등을 자동으로 찾아 스프링 빈으로 등록한다. @ComponentScan(basePackages = {"min.boot.begin", "min.boot.servlet"})
public class ServletConfig implements WebMvcConfigurer {
// 특정 경로에 대해 인터셉터를 적용한다.
	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(new InterceptorLogin()).addPathPatterns("/admin/**");
	}
}