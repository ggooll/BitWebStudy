package kr.co.bit.vo;

/**
 * @author 임규철
 */
public class BoardVO {

	/**
	 * 자바 빈즈 클래스
	 * 1. 패키지로 선언
	 * 2. 멤버변수는 모두 private
	 * 3. get/set
	 * 4. 디폴트 생성자 보유
	 */

	private int no;
	private String title;
	private String writer;
	private String content;
	private String regDate;

	public BoardVO() {

	}

	public BoardVO(int no, String title, String writer, String content, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
