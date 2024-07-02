package su.boot.begin.servlet.fileupload;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import lombok.RequiredArgsConstructor;

// 빈(Bean)으로 컨트롤러 스캔을 구성하고 컨트롤러를 구현한다.
@Controller
@RequiredArgsConstructor
public class FileUpDownController {
	private static final Logger logger = LogManager.getLogger(FileUpDownController.class);
//Spring 4.3 이후로는 파라미터 생성자가 하나만 있는 경우에는 @RequestMapping 어노테이션을 사용하면 @Autowired
// 어노테이션이 생략할 수 있다.
	private final FileUpDownService fileUpDownService;

//파일 업로드 정보에 대한 클라이언트의 요청을 처리할 URL 맵핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@GetMapping("/upload") // 파일 업로드 뷰로 반환한다.
	public String form() {
		logger.info("컨트롤러 확인");
		return "./fileupdown/uploadform";
	}

//파일 업로드 정보에 대한 클라이언트의 요청을 처리할 URL 맵핑명과 처리할 임의의 서블릿 메서드를 설정한다
	@PostMapping("/upload")
	public String upload(Model model, FileUpDownDTO fileUpDownDTO,
			MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		return fileUpDownService.uploadFile(model, fileUpDownDTO, multipartHttpServletRequest);
	}

//파일 업로드 정보에 대한 클라이언트의 요청을 처리할 URL 맵핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@GetMapping("/down")
	public String down(@RequestParam(value = "fileName", required = true) String fileName, HttpServletResponse response)
			throws Exception {
		return fileUpDownService.downloadFile(fileName, response);
	}
}