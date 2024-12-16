package emaillist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import example.DepartmentVo;

public class EmaillistDao {
	private static Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.15:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("SQL error:" + e);
		}
		
		return conn;
		
	}
	
	
	
	public static boolean deleteByEmail(String email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();
			// 1. JDBC Driver 로딩

			System.out.println("연결 성공!");

			// 3. Statement 준비하기
			String sql = "delete from emaillist where email = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. Parameter Binding
			pstmt.setString(1,email);
			int count = pstmt.executeUpdate();
			result = count == 1;
			
			System.out.println("완료");
			
		}  catch (SQLException e) {
			System.out.println("SQL error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("connection 닫기 실패:" + e);
			}

		}
		return result;
	}
	
	public static boolean insert(EmaillistVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();

			System.out.println("연결 성공!");

			// 3. Statement 준비하기
			String sql = "insert into emaillist values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. Parameter Binding
			pstmt.setString(1,vo.getFirstName());
			pstmt.setString(2,vo.getLastName());
			pstmt.setString(3,vo.getEmail());
			int count = pstmt.executeUpdate();
			result = count == 1;
			
			System.out.println("완료");
			
		}  catch (SQLException e) {
			System.out.println("SQL error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("connection 닫기 실패:" + e);
			}

		}
		return result;
	}

	public List<EmaillistVo> findAll() {
		
		List<EmaillistVo> result = new ArrayList<EmaillistVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			System.out.println("findAll 연결 성공!");

			// 3. Statement 준비하기
			String sql = "select id, first_name, last_name, email from emaillist order by id desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				Long id = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				
				EmaillistVo vo = new EmaillistVo();
				
				vo.setId(id);
				vo.setEmail(email);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);

				result.add(vo);
			}
			
			System.out.println("완료");
			
		}  catch (SQLException e) {
			System.out.println("SQL error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("connection 닫기 실패:" + e);
			}

		}
		return result;
	}
	
	public static Long count() {
		Long count = 0l;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			System.out.println("연결 성공!");

			// 3. Statement 준비하기
			String sql = "select count(*) from emaillist";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getLong(1);
			}
			
			System.out.println("완료");
			
		}  catch (SQLException e) {
			System.out.println("SQL error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("connection 닫기 실패:" + e);
			}

		}
		return count;
	}
}
