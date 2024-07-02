package su.boot.begin.mybatis.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import su.boot.begin.mybatis.dto.DeptDTO;
import su.boot.begin.mybatis.service.DeptService;
import lombok.RequiredArgsConstructor;

@Controller
// final 필드만 포함된 생성자를 자동 생성한다.
@RequiredArgsConstructor
public class DeptController {
	private static final Logger logger = LogManager.getLogger(DeptController.class);
	@Autowired
	private final DeptService deptService;

//전체 부서 정보에 대한 웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@GetMapping("/DeptSelect")
	public String list(Model model) {
		model.addAttribute("list", deptService.deptSelectAll());
		logger.info("list", model);
		return "./dept/dept_select_view";
	}

//상세 부서 정보에 대한 웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@GetMapping("/DeptSelectDetail")
	public String detail(Model model, DeptDTO deptDTO) {
		model.addAttribute("deptDTO", deptService.deptSelect(deptDTO.getDeptno()));
		return "./dept/dept_select_detail_view";
	}

//부서 정보 등록에 대한 웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@GetMapping("/DeptInsert")
	public String insert() {
		return "./dept/dept_insert";
	}

//부서 정보 등록에 대한 웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@PostMapping("/DeptInsert")
	public String insert(Model model, DeptDTO deptDTO) {
		model.addAttribute("list", deptService.deptSelectAll());
		deptService.deptInsert(deptDTO);
		return "./dept/dept_insert_view";
	}

//부서 정보 수정에 대한 웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@GetMapping("/DeptUpdate")
//뷰에 전달할 객체 정보를 저장하고 객체를 반환하는 메서드를 선언한다.
	public String update(Model model, DeptDTO deptDTO) {
		model.addAttribute("deptDTO", deptService.deptSelect(deptDTO.getDeptno()));
		return "./dept/dept_update";
	}

//부서 정보 수정에 대한 웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@PostMapping("/DeptUpdate")
	public String update(DeptDTO deptDTO) {
		deptService.deptUpdate(deptDTO);
		return "./dept/dept_update_view";
	}

//부서 정보 삭제에 대한 웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@GetMapping("/DeptDelete")
	public String delete() {
		return "./dept/dept_delete";
	}

//부서 정보 삭제에 대한 웹 브라우저의 요청을 처리할 URL 매핑명과 처리할 임의의 서블릿 메서드를 설정한다.
	@PostMapping("/DeptDelete")
	public String delete(DeptDTO deptDTO) {
		deptService.deptDelete(deptDTO.getDeptno());
		return "./dept/dept_delete_view";
	}

//@RestController 어노테이션에서는 <a> 태그로 매핑명에 접근할 수 없으므로 @Controller 어노테이션에서 설정한다. 
	@GetMapping("/DeptSelectView")
	public String selectAllView() {
//@RestController 어노테이션에서에서 실행하는 뷰 페이지로 이동한다.
		return "./dept/dept_ajax";
	}
}