package kr.co.bit.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.dao.BoardFileDAO;
import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.member.vo.MemberVO;
import kr.co.bit.util.BitFileNamePolicy;

public class WriteProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// post방식 인코딩부터!
		request.setCharacterEncoding("utf-8");
		// OS별로 올바른 파일 세퍼레이터를 사용하도록 해야함,...
		// 현재 이클립스 프로젝트내 폴더를 명칭했지만, 좋은 방법이 아니다. 
		// 실제 웹 서버에 데이터를 저장할 경로를 지정해두는것이 좋다
		String saveFolder = "C:\\Users\\bit-user\\Desktop\\Lecture\\LKC_WEB_Workspace\\Mission-Web\\WebContent\\upload";

		// request정보, 저장될 디렉토리(웹서버 내), 사이즈(3M), 인코딩, 
		// FileRenamePolicy(유니크한 파일명..) 각 유저가 같은이름의 파일을 올렸을때 구분해야될 필요가 있음
		// 해당 라이브러리에 인터페이스가 존재하며, rename이라는 함수를 오버라이딩해야함(유니크한 이름이 되게끔)
		// BitFileNamePolicy.java파일 확인!
		MultipartRequest multi = new MultipartRequest(request, saveFolder, (1024 * 1024 * 3), "utf-8",
				new BitFileNamePolicy());
		// 이렇게까지만 해도 해당 경로에 파일이 업로드되게 된다.
		
		HttpSession session = request.getSession();

		// 기존 가져왔던 정보를 MultipartRequest로 가져와야 한다
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		BoardDAO boardDAO = new BoardDAO();

		//미리 보드번호(시퀀스)를 알아오게끔 한다 (두 테이블에 동일하게 저장하기 위해)
		int boardNo = boardDAO.selectSeqNo();
			
		BoardVO board = new BoardVO();
		board.setNo(boardNo);
		board.setTitle(title);
		board.setWriter(loginUser.getId());
		board.setContent(content);

		int result = boardDAO.insert(board);
		request.setAttribute("result", result);

		// 파일..
		BoardFileDAO boardFileDAO = new BoardFileDAO();
		Enumeration<?> files = multi.getFileNames();
		while (files.hasMoreElements()) {
			// fileName은 attachFile1, 2가 담김
			String fileName = (String) files.nextElement();
			File file = multi.getFile(fileName);
			if (file != null) {
				
				String fileOriName = multi.getOriginalFileName(fileName);
				String fileSaveName = multi.getFilesystemName(fileName);
				int fileSize = (int)file.length();
				
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setBoardNo(boardNo);
				fileVO.setFileOriName(fileOriName);
				fileVO.setFileSaveName(fileSaveName);
				fileVO.setFileSize(fileSize);
				
				// fileVO를 디비에 저장
				result = boardFileDAO.insert(fileVO);	
			}
		}
		
		return "/jsp/board/write.jsp";
	}

}
