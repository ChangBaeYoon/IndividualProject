package model;

import java.util.Date;

public class OrderVO {
	
	private int oNumber;
	private int mNum;
	private int cNum;
	private int oNum;
	private Date date;
	private String phone;
	private String address;
	private String contents;
	private String price;
	private String cash;
	private int quantity;
	
	public OrderVO() {
		super();
	}

	public OrderVO(int oNumber, int mNum, int cNum, int oNum, Date date, String phone, String address,
			String contents, String price, String cash, int quantity) {
		super();
		this.oNumber = oNumber;
		this.mNum = mNum;
		this.cNum = cNum;
		this.oNum = oNum;
		this.date = date;
		this.phone = phone;
		this.address = address;
		this.contents = contents;
		this.price = price;
		this.cash = cash;
		this.quantity = quantity;
	}

	public OrderVO(int mNum, int cNum, int oNum, Date date, String phone, String address, String contents,
			String price, String cash, int quantity) {
		super();
		this.mNum = mNum;
		this.cNum = cNum;
		this.oNum = oNum;
		this.date = date;
		this.phone = phone;
		this.address = address;
		this.contents = contents;
		this.price = price;
		this.cash = cash;
		this.quantity = quantity;
	}

	public int getoNumber() {
		return oNumber;
	}

	public void setoNumber(int oNumber) {
		this.oNumber = oNumber;
	}

	public int getmNum() {
		return mNum;
	}

	public void setmNum(int mNum) {
		this.mNum = mNum;
	}

	public int getcNum() {
		return cNum;
	}

	public void setcNum(int cNum) {
		this.cNum = cNum;
	}

	public int getoNum() {
		return oNum;
	}

	public void setoNum(int oNum) {
		this.oNum = oNum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
