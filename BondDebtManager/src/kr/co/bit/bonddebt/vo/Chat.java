package kr.co.bit.bonddebt.vo;

import java.sql.Date;

public class Chat {

	private int groupNo;
	private int no;
	private int memberNo;
	private String message;
	private Date chatDate;
	private String memberName;

	public Chat() {}

	public Chat(Integer no, Integer groupNo, Integer memberNo, String memberName, String message,
			Date chatDate) {
		super();
		this.no = no;
		this.groupNo = groupNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.message = message;
		this.chatDate = chatDate;
	}

	public Chat(Integer groupNo, Integer memberNo, String memberName, String message) {
		super();
		this.groupNo = groupNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.message = message;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getChatDate() {
		return chatDate;
	}

	public void setChatDate(Date chatDate) {
		this.chatDate = chatDate;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "Chat [groupNo=" + groupNo + ", no=" + no + ", memberNo=" + memberNo + ", message="
				+ message + ", chatDate=" + chatDate + ", memberName=" + memberName + "]";
	}

}
