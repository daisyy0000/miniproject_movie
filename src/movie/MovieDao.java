package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dbconn.DBConnect;

public class MovieDao {
	private DBConnect dbconn;
	
	public MovieDao () {
		dbconn = DBConnect.getInstance();
	}
	
	//TODO: 1.insert - admin 계정이 movie 등록
	//TODO: 2.selectByTheater - 상영관으로 영화검색
	//TODO: 3.selectByTitle - 영화로 상영관 검색
	//TODO: 4.pickTime - 영화 시간대 선택
	//TODO: 5.pickSeat - 영화 좌석 선택
	//TODO: .update - 영화 상영 정보 수정
	//TODO: .delete - 영화 삭제
	
	// 영화 등록
	public void insert(MovieVo vo) {
		String sql = "insert into movie values(seq_movie.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = dbconn.conn();
		//TheaterId, title, runtime, releaseD, endD, startT, finishT 순
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getManaging());
			pstmt.setInt(2, vo.getTheaterN());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getRunTime());
			pstmt.setString(5, vo.getReleaseD());
			pstmt.setString(6, vo.getEndD());
			pstmt.setString(7, vo.getRunD());
			pstmt.setString(8, vo.getStartT());
			pstmt.setString(9, vo.getFinishT());
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
	
	//번호로 검색: 검색한 값이 객체 1set가 나와야되니 반환타입은 movieVo!
	public MovieVo selectByNum(int movieId) {
		ResultSet rs = null; //결과 저장될 값 null로 초기화
		String sql = "select movieId, managing, theaterN, title, runTime,"
				+ "to_char(releaseD, 'yyyy-mm-dd'), to_char(endD, 'yyyy-mm-dd'),"
				+ "to_char(runD, 'yyyy-mm-dd'), startT, finishT from movie where movieId = ?";
		//to_char: 날짜, 숫자등의 값을 문자열로 변환!
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieId);
			rs = pstmt.executeQuery(); //내가 놓친거 2 -> rs 타입의 변수에 넣어준다.
			if(rs.next()) {
				return new MovieVo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10));
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
	
	//상영관으로 영화 찾기: 반환타입이 값이 여러개니깐 어레이리스트
	public ArrayList<MovieVo> selectByTheater(int theaterN) {
		ArrayList<MovieVo> list = new ArrayList<>();
		String sql = "select movieId, managing, theaterN, title, runTime,"
				+ "to_char(releaseD, 'yyyy-mm-dd'), to_char(endD, 'yyyy-mm-dd'),"
				+ "to_char(runD, 'yyyy-mm-dd'), startT, finishT from movie where theaterN = ?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, theaterN);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new MovieVo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10)));
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
	
	// 영화 키워드로 정보 찾기
	public ArrayList<MovieVo> selectByTitle(String title) {
		ArrayList<MovieVo> list = new ArrayList<>();
		String sql = 
				"select movieId, managing, theaterN, title, runTime,"
						+ "to_char(releaseD, 'yyyy-mm-dd'), to_char(endD, 'yyyy-mm-dd'),"
						+ "to_char(runD, 'yyyy-mm-dd'), startT, finishT from movie where title like '%' || ? || '%'";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new MovieVo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10)));
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
	
	// 전체검색
	public ArrayList<MovieVo> selectAll(){
		ArrayList<MovieVo> list = new ArrayList<>();
		String sql = "select movieId, managing, theaterN, title, runTime,"
						+ "to_char(releaseD, 'yyyy-mm-dd'), to_char(endD, 'yyyy-mm-dd'),"
						+ "to_char(runD, 'yyyy-mm-dd'), startT, finishT from movie";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new MovieVo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10)));
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
	
	//영화제목 수정
	public void edit(MovieVo vo) {
		String sql = "update movie set title = ? where movieId = ?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getMovieId());
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
	
	//영화 삭제
	public void deleteOne(int movieId) {
		String sql = "delete from movie where movieId = ?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieId);
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
