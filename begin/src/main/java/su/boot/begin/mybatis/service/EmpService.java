package su.boot.begin.mybatis.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.boot.begin.mybatis.dao.EmpDAO;
import su.boot.begin.mybatis.vo.EmpVO;

@Service
public class EmpService {
	@Autowired
	private EmpDAO empDAO;

    //주어진 검색 조건과 페이지 번호에 따라 직원 목록을 검색한다.
	public List<EmpVO> searchEmployee(String searchFilter, String searchQuery, int page) { 
		// 한 페이지에 표시할 최대 레코드 수를 5로 설정한다.
		int limit = 5;

		//페이지 번호에 따라 시작 위치를 첫 번째 페이지는 0, 두 번째 페이지는 5, 세 번째 페이지는 10 등으로 계산한다.
		int offset = (page - 1) * limit;

		//검색 조건과 페이징 정보를 기반으로 EmpVO 객체 목록을 반환한다.
		return empDAO.selectPagination(searchFilter, searchQuery, limit, offset);
	}


	//주어진 검색 조건에 따라 직원 수를 계산한다.
	public int countEmployee(String searchFilter, String searchQuery) { // 검색 조건에 따라 직원 수를 계산하여 반환한다.
		return empDAO.searchCount(searchFilter, searchQuery);
	}


	//주어진 검색 조건에 따라 총 페이지 수를 계산한다.
	public int totalPage(String searchFilter, String searchQuery) {
		int limit = 5;

		//검색 조건에 따라 전체 직원 수를 계산한다.
		int totalEmployees = countEmployee(searchFilter, searchQuery);

		//ceil 메서드를 사용하여 전체 직원 수를 페이지당 직원 수로 나눈 후 올림 처리한다.
		return (int) Math.ceil((double) totalEmployees / limit);
	}
}