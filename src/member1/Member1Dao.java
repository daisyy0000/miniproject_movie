package member1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import dbconn.DBConnect;

public class Member1Dao {

	private DBConnect dbconn; 

	public Member1Dao() {
		dbconn = DBConnect.getInstance();
	}

//insert(추가, 회원가입)
	public void insert(Member1 vo) {
		String sql = "insert into member1 values(?,?,?,?,?)";

		Connection conn = dbconn.conn();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getTel());

			pstmt.executeUpdate();
			System.out.println("==[회원가입이 완료되었습니다]==");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("==[중복된 ID입니다. 다른 ID를 입력하세요]==");
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

//select
	public Member1 select(String id) {
		String sql = "select * from member1 where id =? ";
		Connection conn = dbconn.conn();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
				String pwd = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String tel = rs.getString(5);
				Member1 vo = new Member1(id, pwd, name, email, tel);
				return vo;
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

//Update(개인정보 수정)
	public void update(Member1 vo) {
		String sql = "update member1 set pwd = ?, name = ?, email = ?, tel = ? where id =? ";
		Connection conn = dbconn.conn();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getId());
			pstmt.executeUpdate();
			System.out.println("==[정보 수정이 완료되었습니다]==");
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

//delete
	public void delete(String id) {
		String sql = "delete from member1 where id = ? ";
		Connection conn = dbconn.conn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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

//selectAll(전체회원목록)
	public ArrayList<Member1> selectAll() {
		String sql = "select * from member1";
		Connection conn = dbconn.conn();
		ArrayList<Member1> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String pwd = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String tel = rs.getString(5);
				Member1 vo = new Member1(id, pwd, name, email, tel);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
