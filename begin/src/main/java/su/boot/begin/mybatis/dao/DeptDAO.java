package su.boot.begin.mybatis.dao;

import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import su.boot.begin.mybatis.dto.DeptDTO;

// 마이바티스의 매퍼 인터페이스임을 나타낸다.
@Mapper
// 트랜잭션을 관리한다.
@Transactional
public interface DeptDAO { // 모든 부서 정보를 조회한다.
	@Select("select deptno, dname, loc from dept")
	public List<DeptDTO> selectAll();

//특정 부서 번호에 해당하는 부서 정보를 조회한다.
	@Select("select deptno, dname, loc from dept where deptno = #{deptno}")
	public DeptDTO select(int deptno);

// 새로운 부서 정보를 삽입한다.
	@Insert("insert into dept (deptno, dname, loc) values(#{deptno}, #{dname}, #{loc})")
	public void insert(DeptDTO deptDTO);

// 기존 부서 정보를 수정한다.
	@Update("update dept set dname = #{dname}, loc = #{loc} where deptno = #{deptno}")
	public void update(DeptDTO deptDTO);

//특정 부서 번호에 해당하는 부서 정보를 삭제한다.
	@Delete("delete from dept where deptno = #{deptno}")
	public void delete(int deptno);
}