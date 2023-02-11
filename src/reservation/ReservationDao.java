package reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import dbconn.DBConnect;

public class ReservationDao {
	private DBConnect dbconn;
	
	public ReservationDao() {
		dbconn = DBConnect.getInstance();
	}
	
	public void insert(ReservationVo vo) {
		String sql = "insert into Reservation values(seq_reserve.nextval,?,?,?,?,?,?,?)";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMovieId());
			pstmt.setString(2, vo.getMemberId());
			pstmt.setInt(3, vo.getTheaterN());
			pstmt.setString(4, vo.getRunD());
			pstmt.setString(5, vo.getStartT());
			pstmt.setString(6, vo.getRowN());
			pstmt.setInt(7, vo.getSeatN());
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
	
	public ReservationVo selectByNum(int ticketN) {
		ReservationVo vo = null;
		ResultSet rs = null;
		
		String sql="select * from Reservation where ticketN=?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketN);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new ReservationVo(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			}
			return vo;
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
	
	public ArrayList<ReservationVo> select(String memberId) {
		ReservationVo vo = null;
		ResultSet rs = null;
		ArrayList<ReservationVo> list = new ArrayList<>();
		
		String sql="select * from Reservation where memberId=?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new ReservationVo(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				list.add(vo);
			}
			return list;
			
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


	public void delete(int ticketN) {
		String sql = "delete from Reservation where ticketN=?";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketN);
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
