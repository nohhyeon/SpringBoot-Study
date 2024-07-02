package su.boot.begin.mybatis.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import su.boot.begin.mybatis.vo.EmpVO;

@Mapper
public interface EmpDAO {
	/**
	 * @param searchFilter : 검색 기준 필드의 이름을 나타내는 문자열이다.
	 * @param searchQuery  : 사용자가 검색하고자 하는 실제 검색어를 나타내는 문자열이다.
	 * @param limit        : 한 페이지에 표시할 최대 레코드 수를 나타내는 정수다.
	 * @param offset       : 페이징을 위한 시작 위치를 나타내는 정수로 건너뛸 레코드 수를 의미한다.
	 **/
// Provider 클래스로부터 동적 SQL 쿼리를 가져온다.
	@SelectProvider(type = EmpSQLProvider.class, method = "paging") // 주어진 검색 조건과 페이징 정보를 기반으로 EmpVO 객체 목록을 반환한다.
	List<EmpVO> selectPagination(@Param("searchFilter") String searchFilter, @Param("searchQuery") String searchQuery,
			@Param("limit") int limit, @Param("offset") int offset);

// Provider 클래스로부터 동적 SQL 쿼리를 가져온다.
	@SelectProvider(type = EmpSQLProvider.class, method = "searchCount") // 주어진 검색 조건에 따라 레코드 수를 반환한다.
	int searchCount(@Param("searchFilter") String searchFilter, @Param("searchQuery") String searchQuery);
}
