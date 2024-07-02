package su.boot.begin.mybatis.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import su.boot.begin.mybatis.dto.DeptDTO;
import su.boot.begin.mybatis.service.DeptService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeptRestController {
	private static final Logger logger = LogManager.getLogger(DeptRestController.class);
	@Autowired
	private final DeptService deptService;

//전체 부서 정보에 대한 요청을 처리하고 JSON 데이터를 반환한다.
	@GetMapping("/selectAll")
	public ResponseEntity<List<DeptDTO>> list() {
		List<DeptDTO> list = deptService.deptSelectAll();
		logger.info("list", list);
//HTTP 200 상태 코드와 함께 리스트를 반환한다.
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

//상세 부서 정보에 대한 요청을 처리하고 JSON 데이터를 반환한다.
	@GetMapping("/select/{deptno}")
	public ResponseEntity<?> detail(@PathVariable("deptno") int deptno) {
		DeptDTO deptDTO = deptService.deptSelect(deptno);
		if (deptDTO == null) {
//부서 번호가 존재하지 않을 경우에 404 상태 코드를 반환한다.
			return new ResponseEntity<>("부서 번호 미존재 : ", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(deptDTO, HttpStatus.OK);
	}

//부서 정보를 입력에 대한 요청을 처리하고 JSON 데이터를 반환한다.
	@PostMapping("/insert")
	public ResponseEntity<String> insert(@RequestBody DeptDTO deptDTO) {
		if (deptService.deptSelect(deptDTO.getDeptno()) != null) { // 부서 번호가 이미 존재할 경우에 400 상태 코드를 반환한다.
			return new ResponseEntity<>("부서 번호 존재 : ", HttpStatus.BAD_REQUEST);
		}
		deptService.deptInsert(deptDTO);
		return new ResponseEntity<>("입력 성공 : ", HttpStatus.OK);
	}

//부서 정보를 수정에 대한 요청을 처리하고 JSON 데이터를 반환한다.
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody DeptDTO deptDTO) {
		if (deptService.deptSelect(deptDTO.getDeptno()) == null) {
			return new ResponseEntity<String>("부서 번호 미존재 : ", HttpStatus.NOT_FOUND);
		}
		deptService.deptUpdate(deptDTO);
		return new ResponseEntity<String>("수정 성공 : ", HttpStatus.OK);
	}

//부서 정보를 삭제에 대한 요청을 처리하고 JSON 데이터를 반환한다.
	@DeleteMapping("/delete/{deptno}")
	public ResponseEntity<String> delete(@PathVariable("deptno") int deptno) {
		if (deptService.deptSelect(deptno) == null) {
			return new ResponseEntity<String>("부서 번호 미존재 : ", HttpStatus.NOT_FOUND);
		}
		deptService.deptDelete(deptno);
		return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
	}
}