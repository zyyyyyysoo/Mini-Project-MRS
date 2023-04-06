package config;

public interface ServerInfo {
// 필드가 없고 무조건 상수로 인식, public static final 무조건 붙어있으므로 안써도 됨
	String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	String USER = "kb_db";
	String PASS = "1234";
}
