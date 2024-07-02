package su.boot.begin;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 

@Controller
public class HomeController {
	private static final Logger logger = LogManager.getLogger(HomeController.class);


	@GetMapping(value = "/home")
	public String home(Locale locale, Model model) {

		logger.info("Welcome home! The client locale is { }.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		return "./test/home";
	}
}