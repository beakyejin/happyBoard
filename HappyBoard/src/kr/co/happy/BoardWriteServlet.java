package kr.co.happy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardWrite")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int btype = Integer.parseInt(request.getParameter("btype"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		if(seq > 0) {
			BoardDAO dao = BoardDAO.getInstance();
			ArrayList<BoardDTO> data = dao.getBoardDetail(btype, seq);
			
			request.setAttribute("data", data);
		}else {
			request.setAttribute("data", null);
		}
	
		request.setAttribute("btype", btype);
		request.setAttribute("seq", seq);
		request.setAttribute("target", "boardWrite");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int btype = Integer.parseInt(request.getParameter("btype"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");

		BoardDAO dao = BoardDAO.getInstance();
		dao.boardInsert(btype, btitle, bcontent, pw);
		
		response.sendRedirect("boardList?btype="+btype+"&page=1");
	}

}
