package kr.co.bit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.dao.BoardFileDAO;
import kr.co.bit.board.vo.BoardFileVO;

public class DeleteController implements Controller {

	private static final String SAVE_FOLDER_PATH =
			"C:\\Users\\bit-user\\Desktop\\Lecture\\LKC_WEB_Workspace\\Mission-Web\\WebContent\\upload";

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 게시글 삭제
		int no = Integer.parseInt(request.getParameter("no"));

		// 서버 경로에있는 파일까지도 삭제해줘야한다.
		BoardFileDAO boardFileDAO = new BoardFileDAO();
		List<BoardFileVO> boardFileList = boardFileDAO.selectByBoardNo(no);

		for (BoardFileVO boardFile : boardFileList) {
			File file = new File(SAVE_FOLDER_PATH + "\\" + boardFile.getFileSaveName());
			file.delete();
		}

		// 게시글 및 첨부파일 삭제
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.delete(no);

		// 데이터베이스에서 삭제
		request.setAttribute("result", result);

		return "/jsp/board/delete.jsp";
	}

}
