package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao {

	private static Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.15:3306/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("SQL error:" + e);
		}

		return conn;

	}

	public boolean insert(CategoryVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			conn = getConnection();

			// 3. Statement 준비하기
			String sql = "insert into category values(null, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement("select last_insert_id() from dual");

			// 4. Parameter Binding
			pstmt.setString(1, vo.getCategory());
			int count = pstmt.executeUpdate();
			result = count == 1;
			
			rs = pstmt2.executeQuery();
			
			if (rs.next()) {
				vo.setNo(rs.getInt(1));
			}
			

		} catch (SQLException e) {
			System.out.println("SQL error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("connection 닫기 실패:" + e);
			}

		}
		return result;
	}

	public List<CategoryVo> findAll() {

		List<CategoryVo> result = new ArrayList<CategoryVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "select no, category from category order by no desc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				int no = rs.getInt(1);
				String category = rs.getString(2);


				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setCategory(category);

				result.add(vo);
			}

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

	public boolean deleteByNo(int no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();
			// 1. JDBC Driver 로딩

			// 3. Statement 준비하기
			String sql = "delete from category where no = ?";
			pstmt = conn.prepareStatement(sql);

			// 4. Parameter Binding
			pstmt.setInt(1, no);
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
