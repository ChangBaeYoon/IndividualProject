package model;

public class CustomerVO {
	
	private int num; //고객번호
	private String name; //고객명
	private String address; //고객주소
	private String phone; //전화번호
	private String other; //비고
	
	public CustomerVO() {
		super();
	}

	public CustomerVO(int num, String name, String address, String phone, String other) {
		super();
		this.num = num;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.other = other;
	}

	public CustomerVO(String name, String address, String phone, String other) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.other = other;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
}
