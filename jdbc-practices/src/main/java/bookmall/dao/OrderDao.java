package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDao {

	public boolean insert(OrderVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			conn = UserDao.getConnection();

			String sql = "insert into orders values(null, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement("select last_insert_id() from dual");

			pstmt.setString(1, vo.getNumber());
			pstmt.setInt(2, vo.getPayment());
			pstmt.setString(3, vo.getShipping());
			pstmt.setString(4, vo.getStatus());
			pstmt.setInt(5, vo.getUserNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

			rs = pstmt2.executeQuery();

			if (rs.next()) {
				vo.setNo(rs.getLong(1));
			}

		} catch (SQLException e) {
			System.out.println("insert SQL error:" + e);
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

	public boolean insertBook(OrderBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = UserDao.getConnection();

			String sql = "insert into orders_book values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getOrderNo());
			pstmt.setInt(2, vo.getBookNo());
			pstmt.setInt(3, vo.getQuantity());
			pstmt.setInt(4, vo.getPrice());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("insert SQL error:" + e);
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

	public OrderVo findByNoAndUserNo(Long no, int userNo) {

		OrderVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = UserDao.getConnection();

			String sql = "select no, number, payment, shipping, status, user_no from orders where no = ? and user_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.setInt(2, userNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new OrderVo();

				Long orderNo = rs.getLong(1);
				String number = rs.getString(2);
				int payment = rs.getInt(3);
				String shipping = rs.getString(4);
				String status = rs.getString(5);
				int userno = rs.getInt(6);

				vo.setNo(orderNo);
				vo.setNumber(number);
				vo.setPayment(payment);
				vo.setShipping(shipping);
				vo.setStatus(status);
				vo.setUserNo(userno);
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
		return vo;
	}

	public List<OrderBookVo> findBooksByNoAndUserNo(Long orderNo, int userNo) {

		List<OrderBookVo> result = new ArrayList<OrderBookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = UserDao.getConnection();

			String sql = "SELECT b.orders_no, b.book_no, b.quantity, b.price, bk.title " + "FROM orders_book b "
					+ "JOIN orders o ON o.no = b.orders_no " + "JOIN user u ON u.no = o.user_no "
					+ "JOIN book bk ON bk.no = b.book_no " + "WHERE o.no = ? AND u.no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderNo);
			pstmt.setInt(2, userNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Long orderno = rs.getLong(1);
				int bookno = rs.getInt(2);
				int quantity = rs.getInt(3);
				int price = rs.getInt(4);
				String bookTitle = rs.getString(5);

				OrderBookVo vo = new OrderBookVo();

				vo.setOrderNo(orderno);
				vo.setBookNo(bookno);
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

	public boolean deleteByNo(Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = UserDao.getConnection();

			String sql = "delete from orders where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
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

	public boolean deleteBooksByNo(Long bookNo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = UserDao.getConnection();

			String sql = "delete from orders_book where book_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, bookNo);
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
