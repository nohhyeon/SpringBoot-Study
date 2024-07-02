package su.boot.begin.servlet.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InterceptorLogin implements HandlerInterceptor {
	private static final Logger logger = LogManager.getLogger(InterceptorLogin.class); // 컨트롤러가 실행되기 전 실행한다.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("세션의 admin 속성을 검사하기 시작한다.");
		if (request.getSession().getAttribute("admin") == null) {
			logger.info("admin 속성이 세션에 없으며 사용자가 로그인 페이지로 리다이렉트된다."); // 루트 경로로 리다이렉트한다.
			response.sendRedirect("/");
			return false;
		}
		return true;
	}
}
