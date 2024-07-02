package su.boot.begin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//스프링 부트 테스트 애노테이션으로 스프링 애플리케이션 컨텍스트를 로드하여 통합 테스트를 실행한다.
@SpringBootTest
public class ConnectionDAOTest {
// Log4j 로거 인스턴스를 생성하여 로그를 기록한다.
	private static final Logger logger = LogManager.getLogger(ConnectionDAOTest.class); // 스프링 컨테이너에서 DataSource 빈을 자동으로
																						// 주입합니다.
	@Autowired
	private DataSource dataSource; // 테스트 작성과 실행을 지원한다.

	@Test
// 데이터베이스 연결을 테스트한다.
	public void testConnection() {
//try-with-resources 문을 사용하여 Connection 객체를 생성하고 try 블록이 끝날 때 자동으로 리소스를 닫는다.
		try (Connection connection = dataSource.getConnection()) {
			logger.info("커넥션 확인 - ", connection);
		} catch (SQLException e) {
			logger.error("커넥션 예외 확인 - ", e);
		}
	}
}