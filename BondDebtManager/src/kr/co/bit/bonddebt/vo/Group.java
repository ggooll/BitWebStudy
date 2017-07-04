package kr.co.bit.bonddebt.vo;

import java.util.ArrayList;

public class Group {
	private int no;
	private String name;
	private ArrayList<String> membersList;
	private int memberCount;

	public Group(int no, String name, ArrayList<String> membersList, int memberCount) {
		this.no = no;
		this.name = name;
		this.membersList = membersList;
		this.memberCount = memberCount;
	}

	public Group() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getMembersList() {
		return membersList;
	}

	public void setMembersList(ArrayList<String> membersList) {
		this.membersList = membersList;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupVO [no=");
		builder.append(no);
		builder.append(", name=");
		builder.append(name);
		builder.append(", membersList=");
		builder.append(membersList);
		builder.append(", memberCount=");
		builder.append(memberCount);
		builder.append("]");
		return builder.toString();
	}

}
