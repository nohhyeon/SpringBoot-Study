package su.boot.begin.servlet.fileupload;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileUpDownService {
	private static final Logger logger = LogManager.getLogger(FileUpDownService.class);

	public String uploadFile(Model model, FileUpDownDTO fileUpDownDTO,
			MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		String name = fileUpDownDTO.getName();
		String subject = fileUpDownDTO.getSubject();
//웹 클라이언트의 파일 시스템에 있는 원래 파일 이름을 반환한다.
		String realFileName = fileUpDownDTO.getFile().getOriginalFilename(); // 파라미터 이름을 키로 파라미터에 해당하는 파일 정보를 값으로 하는

		// Map을 가져온다.
		Map<String, MultipartFile> map = multipartHttpServletRequest.getFileMap();
//맵 요소로 반복하는 동안에만 유효하며 맵 요소의 키와 값을 Set 인터페이스로 반복하여 호출한다.
		Iterator<Map.Entry<String, MultipartFile>> iterator = map.entrySet().iterator();
//FileUpDownDTO 클래스를 사용하여 업로드한 파일을 전달받으며 임시로 저장된 파일에 접근하여 파일을 가져온다.
		MultipartFile multipartFile = fileUpDownDTO.getFile();
//업로드한 파일에 대한 존재 여부를 확인하여 비어있으면 반환한다.
		if (multipartFile.isEmpty()) {
			return "redirect:/upload";
		}
//저장할 파일의 이름을 저장한다.
		String fileName = multipartFile.getOriginalFilename(); // 실제 파일이 저장될 경로를 설정한다.
		String savePath = multipartHttpServletRequest.getSession().getServletContext()
				.getRealPath("./resources/upload"); // 업로드 시에 사용할 실제 경로를 설정한다.
		String filePath = savePath + "//";
//파일명이 중복되었을 경우, 사용할 객체를 선언한다.
		String saveFileName = "";
		String saveFilePath = "";
//반복에 더 많은 요소가 있으면 true를 반환한다.
		while (iterator.hasNext()) {
//반복에서 다음 요소를 반환한다.
			Map.Entry<String, MultipartFile> entry = iterator.next(); // 반복하여 값을 호출한다.
			multipartFile = entry.getValue(); // 확장자를 제외한 파일명을 추출한다.
			String extensionExclude = fileName.substring(0, fileName.lastIndexOf(".")); // 파일의 확장자만 추출한다.
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
//문자열로 표시되는 시스템 종속 기본 이름 구분 문자로 시스템 경로의 구분자를 반환한다.
			saveFilePath = filePath + File.separator + fileName;
			File saveFile = new File(saveFilePath);
//추상 경로명으로 표시된 파일이 일반 파일인지 여부를 판단한다.
			if (saveFile.isFile()) {
				boolean fileExist = true;
				int index = 0;
//같은 파일명이 존재하지 않을 때까지 반복한다.
				while (fileExist) {
					index++;
//파일명이 중복일 경우 덮어씌우지 않고 파일명1.확장자, 파일명2.확장자와 같은 형태로 생성한다.
					saveFileName = extensionExclude + index + "." + extension;
//문자열로 표시되는 시스템 종속 기본 이름 구분 문자로 시스템 경로의 구분자를 반환한다.
					String dictFile = filePath + File.separator + saveFileName;
//추상 경로명으로 표시된 파일이 일반 파일인지 여부를 판단한다.
					fileExist = new File(dictFile).isFile();
					if (!fileExist) {
						// 디렉터리에 저장된 파일을 실제 파일로 저장한다.
						saveFilePath = dictFile;
					}
				}
				// 수신된 파일을 설정된 대상 파일로 전송하며 임시로 저장된 파일을 실제 파일로 전송할 수 있다.
				multipartFile.transferTo(new File(saveFilePath));
				model.addAttribute("name", name);
				model.addAttribute("subject", subject);
				model.addAttribute("realFileName", realFileName);
				model.addAttribute("fileName", saveFileName);
				return "./fileupdown/uploadview";
			} else {
				// 수신된 파일을 설정된 대상 파일로 전송하며 임시로 저장된 파일을 실제 파일로 전송할 수 있다.
				multipartFile.transferTo(saveFile);
				model.addAttribute("name", name);
				model.addAttribute("subject", subject);
				model.addAttribute("realFileName", realFileName);
				model.addAttribute("fileName", fileName);
				return "./fileupdown/uploadview";
			}
		}
		return saveFilePath;
	}

	public String downloadFile(String fileName, HttpServletResponse response) throws Exception {
		fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("/", "%20");
		logger.info("데이터 확인 - " + fileName);
		response.setContentType("application/octet-stream");
		return "redirect:/resources/upload/" + fileName;
	}
}