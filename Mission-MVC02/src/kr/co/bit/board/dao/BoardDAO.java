package kr.co.bit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.util.DBUtil;
import kr.co.bit.util.JDBCClose;

public class BoardDAO {

	/**
	 * ��ü �Խù� ��� ��ȸ�ϴ� ���
	 */
	public List<BoardVO> selectAllBoard() {

		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT NO, TITLE, WRITER, ");
			sql.append(" TO_CHAR(REG_DATE, 'YYYY-MM-DD') AS REG_DATE ");
			sql.append(" FROM T_BOARD ");
			sql.append(" ORDER BY NO DESC ");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String writer = rs.getString("WRITER");
				String regDate = rs.getString("REG_DATE");

				BoardVO board = new BoardVO();
				board.setNo(no);
				board.setTitle(title);
				board.setWriter(writer);
				board.setRegDate(regDate);

				list.add(board);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnect(conn, pstmt, rs);
		}

		return list;
	}

	public void insertBoard(BoardVO board) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO T_BOARD(NO, TITLE, WRITER, CONTENT) ");
		sql.append(" VALUES(?, ?, ?, ?) ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int loc = 1;
			pstmt.setInt(loc++, board.getNo());
			pstmt.setString(loc++, board.getTitle());
			pstmt.setString(loc++, board.getWriter());
			pstmt.setString(loc++, board.getContent());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �Խù� ��ȣ�� ���� ��ȸ����
	 */
	public BoardVO selectByNo(int boardNo) {

		BoardVO board = new BoardVO();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NO, TITLE, WRITER, CONTENT, VIEW_CNT, ");
		sql.append(" TO_CHAR(REG_DATE, 'YYYY-MM-DD') AS REG_DATE ");
		sql.append(" FROM T_BOARD ");
		sql.append(" WHERE NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, boardNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				// int no = rs.getInt("no");
				// board.setNo(no);
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setViewCnt(rs.getInt("VIEW_CNT"));
				board.setRegDate(rs.getString("REG_DATE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;
	}

	/**
	 * �Խù��� �����ϴ� ����
	 */
	public void updateBoard(BoardVO board) {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE T_BOARD ");
		sql.append(" SET TITLE = ?, WRITER = ?, CONTENT = ? ");
		sql.append(" WHERE NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getNo());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������������ �Խù� ��ȸ�� ���� ����
	 */
	public void updateViewCnt(int no) {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE T_BOARD ");
		sql.append(" SET VIEW_CNT = VIEW_CNT + 1 ");
		sql.append(" WHERE NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * �Խù� ���� ����
	 */
	public void delete(int no) {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE T_BOARD ");
		sql.append(" WHERE NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * �Խñ� ��ȣ �����ϴ� ����
	 */
	public int selectNo() {

		String sql = "SELECT SEQ_T_BOARD_NO.NEXTVAL FROM DUAL";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	//////////////////// ÷������ ���� CRUD //////////////////////

	/**
	 * ÷������ ��� ����
	 */
	public void insertFile(BoardFileVO fileVO) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO T_BOARD_FILE( ");
		sql.append("   NO, BOARD_NO, FILE_ORI_NAME, FILE_SAVE_NAME, FILE_SIZE ");
		sql.append(" ) ");
		sql.append(" VALUES(SEQ_T_BOARD_FILE_NO.NEXTVAL, ?, ?, ?, ?) ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, fileVO.getBoardNo());
			pstmt.setString(2, fileVO.getFileOriName());
			pstmt.setString(3, fileVO.getFileSaveName());
			pstmt.setInt(4, fileVO.getFileSize());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * �Խù� ��ȣ�� ÷�ε� ���ϸ���Ʈ ��ȸ����
	 */
	public List<BoardFileVO> selectFileByNo(int boardNo) {
		List<BoardFileVO> fileList = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NO, FILE_ORI_NAME, FILE_SAVE_NAME, FILE_SIZE ");
		sql.append("  FROM T_BOARD_FILE ");
		sql.append(" WHERE BOARD_NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, boardNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setNo(rs.getInt("NO"));
				fileVO.setFileOriName(rs.getString("FILE_ORI_NAME"));
				fileVO.setFileSaveName(rs.getString("FILE_SAVE_NAME"));
				fileVO.setFileSize(rs.getInt("FILE_SIZE"));

				fileList.add(fileVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileList;
	}

	/**
	 * �ش� �Խù��� ÷������ ���� ����
	 */
	public void deleteFile(int boardNo) {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE T_BOARD_FILE ");
		sql.append(" WHERE BOARD_NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
