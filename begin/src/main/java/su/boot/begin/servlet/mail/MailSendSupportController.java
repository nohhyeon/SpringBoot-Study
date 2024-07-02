package su.boot.begin.servlet.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailSendSupportController {
	private static final Logger logger = LogManager.getLogger(MailSendSupportController.class);
	private final MailSendSupportService mailSendSupportService;

	public MailSendSupportController(MailSendSupportService mailSendSupportService) {
		this.mailSendSupportService = mailSendSupportService;
	}

//이메일 정보에 대한 웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@GetMapping("/sendMailSupport")
	public String send(Model model) throws Exception {
		mailSendSupportService.sendEmail();
		String receiverMail = "zzdnd6130@naver.com";
		logger.info(receiverMail);
		model.addAttribute("receiverMail", receiverMail);
		return "./mail/sendMail";
	}
}
