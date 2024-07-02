package su.boot.begin.servlet.fileupload.thumbnail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import su.boot.begin.servlet.fileupload.FileUpDownDTO;
import org.springframework.web.bind.annotation.*;

@Controller
public class ImageThumbnailController {
	private static final Logger logger = LogManager.getLogger(ImageThumbnailController.class);
	private final ImageThumbnaillatorService imageThumbnaillatorService;

//하나의 컨트롤러 내에서 다수의 서비스를 사용하며 @Autowired 어노테이션은 생략할 수 있다.
	public ImageThumbnailController(ImageThumbnaillatorService imageThumbnaillatorService) {
		this.imageThumbnaillatorService = imageThumbnaillatorService;
	}

	@GetMapping("/thumbnail")
	public String form() {
		logger.info("컨트롤러 확인");
		return "./thumbnail/image_thumbnail";
	}

	@PostMapping("/thumbnail")
	public String upload(Model model, FileUpDownDTO fileUpDownDTO,
			MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		String fileName = imageThumbnaillatorService.processImage(fileUpDownDTO, multipartHttpServletRequest);
		model.addAttribute("fileName", fileName);
		return "./thumbnail/image_thumbnail_download";
	}

	@GetMapping("/thumbnailator")
	public String formA() {
		return "./thumbnail/image_thumbnail";
	}

	@PostMapping("/thumbnailator")
	public String uploadB(Model model, FileUpDownDTO fileUpDownDTO,
			MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		String fileName = imageThumbnaillatorService.processImage(fileUpDownDTO, multipartHttpServletRequest);
		logger.info(fileName);
		model.addAttribute("fileName", fileName);
		return "./thumbnail/image_thumbnail_download";
	}
}