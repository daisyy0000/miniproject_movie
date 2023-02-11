package seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbconn.DBConnect;

public class SeatDao {

	private DBConnect dbconn;// db연결 객체
	
	public SeatDao() {
		dbconn = DBConnect.getInstance();
	}
	// 상영중인 영화정보

	// 예매할 때 열 선택     ==같은말vo.getRowN(), vo.getTheaterN(), runD, vo.getStartT
	public String rowValue(String row, int theaterN, String rDate, String rTime) {
		// row : 열 ("a" 열!)
		// screen : 상영관
		// rDate : 상영날짜
		// rTime : 상영시간
		String sql = "select " + row + " from seats where theaterN=? and to_char(r_date,'yyyymmdd')=? and r_time=?";
		Connection conn = dbconn.conn();// db연결
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, theaterN);
			pstmt.setString(2, rDate.replaceAll("-", ""));
			pstmt.setString(3, rTime);
			// ResultSet: 검색 결과 테이블
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1); // ex) '0000000000'
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// 좌석 예약 업데이트           rowN, str, runD, startT, theaterN
	public void update(String row, String val, String rDate, String rTime, int theaterN) {
		String sql = "update seats set " + row + "=?, r_date=?, r_time=? where theaterN=?";
		
//		String sql = "update seats set " + row + "=? where theaterN=?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, val);
//			pstmt.setString(3, rDate.replaceAll("-", ""));
			pstmt.setString(2, rDate);
			pstmt.setString(3, rTime);
			pstmt.setInt(4, theaterN);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void insert(int theaterN, String r_date, String r_time) {
		String sql = "insert into seats(theaterN , r_date, r_time) values(seq_seat.nextval, ?, ?)";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			String[] runDate = r_date.split(" ", 0);
			String rd = runDate[0].replaceAll("-", "");
//			pstmt.setInt(1, theaterN);
			pstmt.setString(1, rd);
			pstmt.setString(2, r_time);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
