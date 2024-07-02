package su.boot.begin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Spring Boot 애플리케이션을 설정하고 자동 구성 기능을 활성화하며 패키지를 스캔하여 Spring 컨텍스트에 빈을 등록한다.
@SpringBootApplication


public class BeginApplication {
	public static void main(String[] args) { // 애플리케이션을 시작한다.
		SpringApplication.run(BeginApplication.class, args);
	}
}
