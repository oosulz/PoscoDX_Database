package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertEx02 {
	public static void main(String[] args) {
		insert("개발 1팀");
	}

	public static boolean insert(String departmentName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.13:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결 성공!");

			// 3. Statement 준비하기
			String sql = "insert into department values(4, ?)";
			pstmt = conn.prepareStatement(sql);
			
			
			// 4. Parameter Binding
			pstmt.setString(1,departmentName);
			int count = pstmt.executeUpdate();
			result = count == 1;
			
			System.out.println("완료");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
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
}
