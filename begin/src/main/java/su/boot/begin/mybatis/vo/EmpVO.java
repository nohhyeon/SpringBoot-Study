package su.boot.begin.mybatis.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class EmpVO {
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private String hiredate;
	private BigDecimal sal;
	private BigDecimal comm;
	private Integer deptno;

}
