package kr.co.bit.board.vo;

public class BoardVO {

	private int no;
	private String title;
	private String writer;
	private String content;
	private int viewCnt;
	private String regDate;
	private static int maxCount = -1;

	public BoardVO() {}
	
	public BoardVO(String title, String writer){
		this.title = title;
		this.writer = writer;
	}

	public BoardVO(int no, String title, String writer, String content, int view_cnt,
			String regDate) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.viewCnt = view_cnt;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public static int getMaxCount() {
		return maxCount;
	}

	public static void setMaxCount(int maxCount) {
		BoardVO.maxCount = maxCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardVO [no=").append(no).append(", title=").append(title)
				.append(", writer=").append(writer).append(", content=").append(content)
				.append(", viewCnt=").append(viewCnt).append(", regDate=").append(regDate)
				.append("]");
		return builder.toString();
	}

}
