package su.boot.begin.servlet.fileupload.thumbnail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import su.boot.begin.servlet.fileupload.FileUpDownDTO;
import net.coobird.thumbnailator.Thumbnailator;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import org.springframework.util.FileCopyUtils;

@Component
public class ImageThumbnaillatorService {
	private static final Logger logger = LogManager.getLogger(ImageThumbnaillatorService.class);

	public String processImage(FileUpDownDTO fileUpDownDTO, MultipartHttpServletRequest multipartHttpServletRequest)
			throws Exception {
//FileUpDownDTO 클래스를 사용하여 업로드한 파일을 전달받으며 임시로 저장된 파일에 접근하여 파일을 가져온다.
		MultipartFile multipartFile = fileUpDownDTO.getFile(); // 업로드한 파일에 대한 존재 여부를 확인하여 비어있으면 반환한다.
		if (multipartFile.isEmpty()) {
			return "redirect:/thumbnail";
		}
// 현재 시각으로 파일 중복을 제거한다.
		Long datetime = System.currentTimeMillis();
		String fileName = datetime + "_" + fileUpDownDTO.getFile().getOriginalFilename();
		String savePath = multipartHttpServletRequest.getSession().getServletContext().getRealPath("./resources/img");
		String filePath = savePath + "//" + fileName;
		File file = new File(filePath);
		FileCopyUtils.copy(multipartFile.getBytes(), file);
//설정된 경로에 파일이 없으면 파일을 생성하고 파일로 바이트 단위의 데이터를 추가한다.
		FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath, "sm_" + fileName));
		logger.info(savePath);
//원본 이미지에서 섬네일을 만들고 섬네일을 대상 파일에 쓴다.
		Thumbnailator.createThumbnail(multipartFile.getInputStream(), fileOutputStream, 100, 100); // 수신된 파일을 설정된 대상 파일로
																									// 전송하며 임시로 저장된 파일을
																									// 실제 파일로 전송할 수 있다.
		multipartFile.transferTo(file);
		fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("//+", "%20");
		return fileName;
	}
}