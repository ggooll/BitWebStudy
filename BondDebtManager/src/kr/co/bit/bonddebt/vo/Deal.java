package kr.co.bit.bonddebt.vo;

public class Deal {

	private int no;
	private String comments;
	private int senderNo;
	private int receiverNo;
	private String senderId;
	private String receiverId;
	private int money;
	private String startDate;

	public Deal() {}

	public Deal(int no, String comments, int senderNo, int receiverNo, String senderId,
			String receiverId, int money, String startDate) {
		this.no = no;
		this.comments = comments;
		this.senderNo = senderNo;
		this.receiverNo = receiverNo;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.money = money;
		this.startDate = startDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getSenderNo() {
		return senderNo;
	}

	public void setSenderNo(int senderNo) {
		this.senderNo = senderNo;
	}

	public int getReceiverNo() {
		return receiverNo;
	}

	public void setReceiverNo(int receiverNo) {
		this.receiverNo = receiverNo;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "{ \"no\":\"" + no + "\", \"comments\":\"" + comments + "\", \"senderNo\":\""
				+ senderNo + "\", \"receiverNo\":\"" + receiverNo + "\", \"senderId\":\"" + senderId
				+ "\", \"receiverId\":\"" + receiverId + "\", \"money\":\"" + money
				+ "\", \"startDate\":\"" + startDate + "\"}";
	}

}
