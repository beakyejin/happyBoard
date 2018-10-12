package kr.co.happy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardDetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int btype = Integer.parseInt(request.getParameter("btype"));
		int page = Integer.parseInt(request.getParameter("page"));
		int _page = Integer.parseInt(request.getParameter("page"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardDTO> data = dao.getBoardDetail(btype, seq);
		
		request.setAttribute("data", data);
		request.setAttribute("btype", btype);
		request.setAttribute("page", _page);
		request.setAttribute("target", "boardDetail");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
