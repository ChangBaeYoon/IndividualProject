package model;

public class CustomerVO {
	
	private int num; //����ȣ
	private String name; //����
	private String address; //���ּ�
	private String phone; //��ȭ��ȣ
	private String other; //���
	
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
