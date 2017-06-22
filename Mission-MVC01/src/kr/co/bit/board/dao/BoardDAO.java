package kr.co.bit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.util.DBUtil;

public class BoardDAO {
	/**
	 * 
	 * @return
	 */
	public List<BoardVO> selectAll() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NO, TITLE, WRITER, to_char(REG_DATE, 'yyyy-mm-dd') as REG_DATE ");
		sql.append(" FROM T_BOARD ");
		sql.append(" ORDER BY NO DESC");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setRegDate(rs.getString("REG_DATE"));
				boardList.add(board);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return boardList;
	}

	/**
	 * 
	 * @param boardNo
	 * @return
	 */
	public BoardVO selectByNo(int boardNo) {
		BoardVO board = null;
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT NO, TITLE, WRITER, CONTENT, VIEW_CNT, to_char(REG_DATE, 'yyyy-mm-dd') as REG_DATE ");
		sql.append("FROM T_BOARD ");
		sql.append("WHERE NO = ?");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, boardNo);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				board = new BoardVO();
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setViewCnt(rs.getInt("VIEW_CNT"));
				board.setRegDate(rs.getString("REG_DATE"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return board;
	}

	/**
	 * 
	 * @param board
	 * @return
	 */
	public int insert(BoardVO board) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO T_BOARD(NO, TITLE, WRITER, CONTENT) ");
		sql.append(" VALUES(?, ?, ?, ?)");
		int result = 0;

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			// 변수를 선언하고 ++하는 식으로 구현하면 유지보수할때 좋을 수도 있음
			int loc = 1;
			pst.setInt(loc++, board.getNo());
			pst.setString(loc++, board.getTitle());
			pst.setString(loc++, board.getWriter());
			pst.setString(loc++, board.getContent());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param board
	 * @return
	 */
	public int update(BoardVO board) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE T_BOARD");
		sql.append(" SET TITLE=?, CONTENT=?");
		sql.append(" WHERE NO = ?");
		int result = 0;

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setString(1, board.getTitle());
			pst.setString(2, board.getContent());
			pst.setInt(3, board.getNo());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param no
	 * @return
	 */
	public int delete(int no) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM T_BOARD WHERE NO = ?");
		int result = 0;

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, no);
			result = pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param board
	 * @return
	 */
	public int increaseViewCnt(BoardVO board) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE T_BOARD SET VIEW_CNT=? WHERE NO = ?");
		int result = 0;

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, board.getViewCnt());
			pst.setInt(2, board.getNo());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 페이지 번호에 해당하는 게시글들을 가져온다
	 * 
	 * @param pageNum
	 * @return
	 */
	public List<BoardVO> selectByPageNum(int pageNum) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NO, TITLE, WRITER, to_char(REG_DATE, 'yyyy-mm-dd') as REG_DATE ");
		sql.append("FROM (SELECT ROWNUM RN, A.* ");
		sql.append("FROM (SELECT * FROM T_BOARD ORDER BY no desc) A) ");
		sql.append("WHERE RN BETWEEN ? AND ?");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, 10 * (pageNum - 1) + 1);
			pst.setInt(2, 10 * (pageNum));
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setRegDate(rs.getString("REG_DATE"));
				boardList.add(board);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return boardList;
	}

	/**
	 * 현재 게시글의 개수를 조회한다
	 * 
	 * @return
	 */
	public int selectMaxBoardCount() {
		int count = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) AS MAXCOUNT FROM T_BOARD");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				count = rs.getInt("MAXCOUNT");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 게시글의 현재 시퀀스를 반환한다.
	 * 
	 * @return
	 */
	public int selectSeqNo() {
		String sql = "SELECT SEQ_T_BOARD_NO.NEXTVAL FROM DUAL";
		int seq = 0;

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				seq = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return seq;
	}

}
