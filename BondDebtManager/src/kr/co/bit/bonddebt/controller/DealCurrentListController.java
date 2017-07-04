package kr.co.bit.bonddebt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.bonddebt.dao.DealDAO;
import kr.co.bit.bonddebt.vo.Deal;
import kr.co.bit.bonddebt.vo.Member;

public class DealCurrentListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "/jsp/process/sessionOutProcess.jsp";
		}

		int memberNo = loginUser.getNo();

		int pageNo = 1;
		String page = request.getParameter("page");
		if (page != null) {
			pageNo = Integer.parseInt(page);
		}

		DealDAO dealDAO = new DealDAO();
		List<Deal> dealList = dealDAO.selectAll(memberNo, pageNo);
		request.setAttribute("dealList", dealList);

		System.out.println(dealList);

		// ȸ���� �����ŷ������� ���� ��ü �Խù� ����, �ִ� ������ ����
		int maxCount = dealDAO.selectMaxCount(memberNo);

		// ����������, �������� ����(�׸� ��)
		int startPage = 5 * ((pageNo - 1) / 5) + 1;
		int lastPage = startPage + 4;

		if (lastPage >= (maxCount / 10) + 1) {
			lastPage = (maxCount / 10) + 1;
		}

		// ��������ȣ
		request.setAttribute("currentPage", pageNo);
		request.setAttribute("startPage", startPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("maxPage", (maxCount / 10) + 1);

		return "/jsp/currentDeal.jsp";
	}

}
