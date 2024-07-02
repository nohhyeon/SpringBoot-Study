package su.boot.begin.servlet.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailSendSupportService {
	private static final Logger logger = LogManager.getLogger(MailSendSupportService.class);
	private final JavaMailSender javaMailSender;

	public MailSendSupportService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail() throws Exception {
		String subject = "테스트 메일";
		String content = "메일 테스트 내용입니다.";
		String senderMail = "zzdnd6130@naver.com";
		String receiverMail = "zzdnd6130@naver.com";
//기본 JavaMail의 세션에 대한 새 JavaMail의 MimeMessage를 생성한다.
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//요청된 경우 멀티파트 모드에서 지정된 MimeMessage에 대한 새 MimeMessageHelper를 생성한다.
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // 보낼 이메일 주소를 저장한다.
		mimeMessageHelper.setFrom(senderMail); // 받을 이메일 주소를 저장한다.
		mimeMessageHelper.setTo(receiverMail); // 보낼 메시지 제목을 저장한다.
		mimeMessageHelper.setSubject(subject);
//보낼 내용의 지정된 텍스트를 비 멀티파트 모드의 콘텐츠로 직접 설정하거나 멀티파트 모드의 기본 본문 부분으로 설정한다.
		mimeMessageHelper.setText(content, true); // 메시지를 전송한다.
		javaMailSender.send(mimeMessage);
		logger.info("메일 전송 확인 - " + mimeMessage);
	}
}