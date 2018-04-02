package model;

public class MenuVO {
	
	private int num;
	private String name;
	private String price;
	private String btn;
	
	public MenuVO() {
		super();
	}

	public MenuVO(int num, String name, String price, String btn) {
		super();
		this.num = num;
		this.name = name;
		this.price = price;
		this.btn = btn;
	}

	public MenuVO(String name, String price, String btn) {
		super();
		this.name = name;
		this.price = price;
		this.btn = btn;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}
	
}
