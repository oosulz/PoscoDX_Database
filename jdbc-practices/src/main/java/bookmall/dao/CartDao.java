package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;

public class CartDao {

	public boolean insert(CartVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			
			conn = UserDao.getConnection();
		
			String sql= "insert into cart values(?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getUserNo());
			pstmt.setInt(2, vo.getBookNo());
			pstmt.setInt(3, vo.getQuantity());
			pstmt.setInt(4, vo.getPrice());
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
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
		return result;
	}

	public List<CartVo> findByUserNo(int userNo) {

		List<CartVo> result = new ArrayList<CartVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = UserDao.getConnection();

			String sql = "SELECT c.user_no, c.book_no, c.quantity, c.price, b.title " +
                    "FROM cart c " +
                    "JOIN book b ON c.book_no = b.no " +
                    "WHERE c.user_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				int user_no = rs.getInt(1);
				int book_no = rs.getInt(2);
				int quantity = rs.getInt(3);
				int price = rs.getInt(4);
				String bookTitle = rs.getString(5);
			
				CartVo vo = new CartVo();
				vo.setUserNo(user_no);
				vo.setBookNo(book_no);
				vo.setQuantity(quantity);
				vo.setPrice(price);
				vo.setBookTitle(bookTitle);
				
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
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("connection 닫기 실패:" + e);
			}

		}
		return result;
	}

	public boolean deleteByUserNoAndBookNo(int userNo, int bookNo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = UserDao.getConnection();

			String sql = "delete from cart where user_no = ? and book_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);
			pstmt.setInt(2, bookNo);
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
