package kr.co.bit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String saveFolder =
				"C:\\Users\\bit-user\\Desktop\\Lecture\\LKC_WEB_Workspace\\Mission-Web\\WebContent\\upload";
		String boardNo = request.getParameter("no");
		String fileSaveName = request.getParameter("file");
		String fileOriName = request.getParameter("ori");
		request.setAttribute("boardNo", boardNo);

		File file = new File(saveFolder + "\\" + fileSaveName);
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileOriName + "\";");
		FileInputStream fis = new FileInputStream(file);
		ServletOutputStream os = response.getOutputStream();
		/* out.close(); */

		byte[] bytes = new byte[1024 * 1024 * 3];
		int data = 0;

		while ((data = (fis.read(bytes, 0, bytes.length))) != -1) {
			os.write(bytes, 0, data);
		}

		os.flush();
		os.close();
		fis.close();

		// detail.do 
		
		return "/jsp/board/download.jsp";
	}

}
