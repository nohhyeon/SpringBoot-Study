package su.boot.begin.servlet.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 빈(Bean)으로 컨트롤러 스캔을 구성하고 컨트롤러를 구현한다.
@Controller
public class LoginController {
//인터셉터의 요청을 처리할 URL 맵핑명과 처리할 임의의 서블릿 메소드를 설정한다..
	@GetMapping("/login")
	public String admin_login() {
		return "./login/login";
	}

//인터셉터의 요청을 처리할 URL 맵핑명과 처리할 임의의 서블릿 메소드를 설정한다..
	@PostMapping("/login_admin")
	public String login(HttpServletRequest request, HttpSession httpSession) {
		String returnURL = "";
// 아이디와 패스워드가 일치하면 세션을 생성한다.
		if (request.getParameter("id").equals("admin") & request.getParameter("password").equals("1234")) {
			httpSession.setAttribute("admin_id", "admin");
			httpSession.setAttribute("admin_name", "관리자");
			returnURL = "redirect:/admin_main";
		} else {
			returnURL = "redirect:/login";
		}
		return returnURL;
	}

//인터셉터의 요청을 처리할 URL 맵핑명과 처리할 임의의 서블릿 메소드를 설정한다.
	@GetMapping("/admin_main")
	public String admin_main() {
		return "./admin/admin_main";
	}
}