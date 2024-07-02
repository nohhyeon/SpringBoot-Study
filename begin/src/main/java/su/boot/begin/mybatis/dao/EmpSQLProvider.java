package su.boot.begin.mybatis.dao;

import java.util.Map;

public class EmpSQLProvider {
//페이징 처리와 검색을 지원하는 SQL 쿼리를 생성한다.
	public String paging(Map<String, Object> map) { // 비동기식으로 쿼리를 추가한다.
		StringBuilder stringBuilder = new StringBuilder(); // SQL 쿼리를 시작한다.
		stringBuilder.append("select * from ( ");
//row_number 함수 각 행에 고유한 번호를 부여하고 over 함수는 데이터를 분석하고 집계하여 결과를 empno를 기준으로 정렬한다.
		stringBuilder.append(
				"select empno, ename, job, mgr, to_char(hiredate, 'yyyy-mm-dd') as hiredate, sal, comm, deptno, row_number( ) over (order by empno) as rowNumber ");
//항상 참인 조건인 where 1=1을 추가하여 이후에 조건을 추가할 때 SQL 구문이 간단해진다.
		stringBuilder.append("from emp where 1=1 ");
//맵에서 searchFilter 값을 추출하며 searchFilter는 검색 기준 필드의 이름을 나타낸다.
		String searchFilter = (String) map.get("searchFilter");
//맵에서 earchQuery 값을 추출하며 earchQuery는 사용자가 검색하고자 하는 실제 검색어 값을 나타낸다.
		String searchQuery = (String) map.get("searchQuery"); // 사원 번호가 정확히 검색어와 일치하는 레코드를 검색한다.
		if ("empno".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and empno = #{searchQuery}");
		}
// 사원 이름에 검색어가 포함된 레코드를 찾는다.
		if ("ename".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and ename like '%' || #{searchQuery} || '%'");
		}
//부서 번호가 정확히 검색어와 일치하는 레코드를 찾는다.
		if ("deptno".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and deptno = #{searchQuery}");
		}
// 직급에 검색어가 포함된 레코드를 찾는다.
		if ("job".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and job like '%' || #{searchQuery} || '%'");
		}
// 관리자에 검색어가 포함된 레코드를 찾는다.
		if ("mgr".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and mgr like '%' || #{searchQuery} || '%'");
		}
//페이징을 설정하는 조건이다.
		stringBuilder.append(") where rowNumber >= #{offset} + 1 and rowNumber <= #{offset} + #{limit}"); // 최종 SQL 쿼리
																											// 문자열을
																											// 반환한다.
		return stringBuilder.toString();
	}

//검색 조건에 따라 레코드 수를 반환하는 SQL 쿼리를 생성한다.
	public String searchCount(Map<String, Object> map) { // 비동기식으로 쿼리를 추가한다.
		StringBuilder stringBuilder = new StringBuilder();
//SQL 쿼리를 시작하고 전체 레코드 수를 계산하기 위해 count 함수를 적용한다.
		stringBuilder.append("select count(*) from emp where 1=1 ");
//맵에서 searchFilter 값을 추출하며 searchFilter는 검색 기준 필드의 이름을 나타낸다.
		String searchFilter = (String) map.get("searchFilter");
//맵에서 earchQuery 값을 추출하며 earchQuery는 사용자가 검색하고자 하는 실제 검색어 값을 나타낸다.
		String searchQuery = (String) map.get("searchQuery");
//필터 조건에 따라 SQL 쿼리에 조건 추가하며 사원 번호가 정확히 검색어와 일치하는 레코드 수를 계산한다.
		if ("empno".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and empno = #{searchQuery}");
		}
//사원 이름에 검색어가 포함된 레코드 수를 계산한다.
		if ("ename".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and ename like '%' || #{searchQuery} || '%'");
		}
//부서 번호가 정확히 검색어와 일치하는 레코드 수를 계산한다.
		if ("deptno".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and deptno = #{searchQuery}");
		}
//직급에 검색어가 포함된 레코드 수를 계산한다.
		if ("job".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and job like '%' || #{searchQuery} || '%'");
		}
//관리자에 검색어가 포함된 레코드 수를 계산한다.
		if ("mgr".equals(searchFilter) && searchQuery != null && !searchQuery.isEmpty()) {
			stringBuilder.append(" and mgr like '%' || #{searchQuery} || '%'");
		}
//최종 SQL 쿼리 문자열을 반환한다.
		return stringBuilder.toString();
	}
}