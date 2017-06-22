package kr.co.bit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.util.DBUtil;

public class BoardFileDAO {

	/**
	 * 첨부파일이 등록된 경우 테이블에 저장
	 * 
	 * @param boardFileVO
	 * @return
	 */
	public int insert(BoardFileVO boardFileVO) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO T_BOARD_FILE(NO, BOARD_NO, FILE_ORI_NAME, FILE_SAVE_NAME, FILE_SIZE) ");
		sql.append(" VALUES(SEQ_T_BOARD_FILE_NO.nextval, ?, ?, ?, ?)");
		int result = 0;

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			// 변수를 선언하고 ++하는 식으로 구현하면 유지보수할때 좋을 수도 있음
			int loc = 1;
			pst.setInt(loc++, boardFileVO.getBoardNo());
			pst.setString(loc++, boardFileVO.getFileOriName());
			pst.setString(loc++, boardFileVO.getFileSaveName());
			pst.setInt(loc++, boardFileVO.getFileSize());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 게시물의 첨부파일들을 조회한다.
	 * 
	 * @param boardNo
	 * @return
	 */
	public List<BoardFileVO> selectByBoardNo(int boardNo) {
		List<BoardFileVO> boardFileList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NO, BOARD_NO, FILE_ORI_NAME, FILE_SAVE_NAME, FILE_SIZE ");
		sql.append(" FROM T_BOARD_FILE ");
		sql.append(" WHERE BOARD_NO = ?");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, boardNo);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setNo(rs.getInt("NO"));
				fileVO.setBoardNo(rs.getInt("BOARD_NO"));
				fileVO.setFileOriName(rs.getString("FILE_ORI_NAME"));
				fileVO.setFileSaveName(rs.getString("FILE_SAVE_NAME"));
				fileVO.setFileSize(rs.getInt("FILE_SIZE"));
				boardFileList.add(fileVO);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return boardFileList;
	}

	/**
	 * 첨부파일 지움(cascade로 대체)
	 * @param boardNo
	 * @return
	 */
	public int deleteByBoardNo(int boardNo) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM T_BOARD_FILE WHERE BOARD_NO = ?");
		int result = 0;

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, boardNo);
			result = pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
