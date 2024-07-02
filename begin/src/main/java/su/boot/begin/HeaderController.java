package su.boot.begin;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; // 빈으로 컨트롤러 스캔을 구성하고 컨트롤러를 구현한다.

@Controller
public class HeaderController {
	private static final Logger logger = LogManager.getLogger(HomeController.class);

//웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다..
	@GetMapping(value = "/header")
	public String home(Locale locale, Model model) {
// 로그를 출력한다.
		logger.info("Welcome home! The client locale is { }.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
// 뷰에서 호출할 데이터의 속성을 추가한다.
		model.addAttribute("serverTime", formattedDate);
		return "header";
	}
}