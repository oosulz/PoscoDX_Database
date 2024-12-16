package bookmall.vo;

public class CartVo {
	private int userNo;
	private int bookNo;
	private int quantity;
	private int price;
	private String bookTitle;

	public CartVo() {

	}

	@Override
	public String toString() {
		return "CartVo [userNo=" + userNo + ", bookNo=" + bookNo + ", quantity=" + quantity + ", price=" + price
				+ ", bookTitle=" + bookTitle + "]";
	}


	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookTitle() {
		return bookTitle;
	}

}