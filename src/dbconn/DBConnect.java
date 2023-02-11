package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//11.8 화
public class DBConnect {
	//싱글톤 타입(db호출)
	private static DBConnect dbconn = new DBConnect();
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
							//오라클 접속 세부정보 주소
							//버전은 컨트롤 쉬프트 딜리트 - 작업관리자 - 서비스- oracleservicexe(버전확인할수있음)
	//디폴트 생성자
	private DBConnect() {}
	
	public static DBConnect getInstance() {
		return dbconn;
	}

	public Connection conn() {
		try {
			//드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver"); //오타 주의 //예외처리
			//class.forName은 문자열로 주어진 jdbc driver 클래스를 build path에서 찾고 메모리 로딩
			//드라이버매니저에 jdbc드라이버가 등록되면 getConnection 메소드로 db와 연결 가능
			//세션 수립
			return DriverManager.getConnection(url, "hr" , "hr"); //교재 909페이지 드라이버로드 주소 확인 오타금지
		    //DriverManager jdbc driver를 관리하며 db와 연결해서 Connection 구현 객체 생성
			                                  // 본인 오라클 db 아이디랑 비번입력
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return null;
	}
}
