package kr.co.bit.bonddebt.vo;

public class Member {

	private int no;
	private String id;
	private String password;
	private String name;
	private String tel;
	private int balance;
	private String email;

	public Member() {}

	public Member(int no, String id, String password, String name, String tel, int balance,
			String email) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.balance = balance;
		this.email = email;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addBalance(int money) {
		this.balance += money;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", password=" + password + ", name=" + name
				+ ", tel=" + tel + ", balance=" + balance + ", email=" + email + "]";
	}

}
