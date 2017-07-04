package kr.co.bit.board.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.service.BoardService;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.framework.ModelAndView;
import kr.co.bit.framework.annotation.Controller;
import kr.co.bit.framework.annotation.RequestMapping;

@Controller
public class BoardController {

	@RequestMapping(value = "/board/write.do")
	public void insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("/board/write.do");
	}

	@RequestMapping("/board/list.do")
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ServletContext sc = request.getServletContext();
		BoardService service = (BoardService) sc.getAttribute("boardService");
		List<BoardVO> list = service.selectAllBoard();

		ModelAndView mav = new ModelAndView();
		mav.setView("/jsp/board/list.jsp");
		mav.addAttribute("list", list);

		return mav;
	}
}
