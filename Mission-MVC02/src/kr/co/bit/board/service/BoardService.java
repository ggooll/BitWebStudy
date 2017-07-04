package kr.co.bit.board.service;

import java.util.List;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardVO;

public class BoardService {

	private BoardDAO boardDAO;

	public BoardService(BoardDAO dao) {
		this.boardDAO = dao;
	}

	public List<BoardVO> selectAllBoard() {

		List<BoardVO> list = boardDAO.selectAllBoard();

		return list;

	}
}
