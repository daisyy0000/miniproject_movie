package theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import dbconn.DBConnect;
//TODO 1. 상영관 정보(추가/수정/삭제)
// 수정에서 총 좌석수, 잔여좌석수 실시간 연계필요한지? 아니면 단순히 숫자수정으로 구현
import seat.SeatVo;

public class TheaterDao {
	private DBConnect dbconn; // DB연결 객체
	public SeatVo vo;

	public TheaterDao() {
		dbconn = DBConnect.getInstance();

	}

	// 상영관 추가
	public void insertTheater(TheaterVo vo) {
		String sql = "insert into theater values(seq_theater.nextval,?,?,?)";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setInt(2, vo.getTotalSeat());
			pstmt.setInt(3, vo.getRemainSeat());
			pstmt.executeUpdate();
			System.out.println("신규 상영관이 추가되었습니다.");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("중복된 상영관 다시 입력하시오");
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

	// 상영관 정보 삭제
	public void deleteTheater(int num) {
		String sql = "delete from theater where theaterN=?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
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

	//
	public void getRow(String row, int num) { // 열 전 받아오
		String sql = "select" + "?" + "from seats where theaterN= ?";
		Connection conn = dbconn.conn();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, row);
			pstmt.setInt(2, num);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<String> list = new ArrayList<>();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			for (int i = 0; i < list.size(); i++) {
				list.get(i);
				System.out.println(i);
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
	}

	// 전체좌석 검색
	public int totalSeat(int num) {
		String sql = "select * from theater where theaterN = ?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(3); //실행문을 돌렸을때의 3번째 값인 총 좌석수
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
		return 0;
	}

	// 상영관 이름으로 예약된 좌석수 검색
	public String remainSeat(int num) {
		String sql = "select a, b, c, d, e from seats where theaterN = ?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5);
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

	public ArrayList<TheaterVo> selectAllTheater() {
		ArrayList<TheaterVo> list = new ArrayList<>();
		String sql = "select * from theater";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new TheaterVo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
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
		return list;
	}

}
