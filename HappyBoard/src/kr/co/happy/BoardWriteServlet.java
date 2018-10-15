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
		String kind = request.getParameter("kind");
		int seq = Integer.parseInt(request.getParameter("seq"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		if(seq > 0) {
			BoardDAO dao = BoardDAO.getInstance();
			ArrayList<BoardDTO> data = dao.getBoardDetail(btype, seq);
			
			request.setAttribute("data", data);
			request.setAttribute("seq", seq);
		}else {
			request.setAttribute("data", null);
			request.setAttribute("seq", 0);
		}
	
		request.setAttribute("page", page);
		request.setAttribute("kind", kind);
		request.setAttribute("btype", btype);
		request.setAttribute("target", "boardWrite");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int btype = Integer.parseInt(request.getParameter("btype"));
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");
		String kind = request.getParameter("kind");
		int page = Integer.parseInt(request.getParameter("page"));
		int seq = Integer.parseInt(request.getParameter("seq"));
	
		BoardDAO dao = BoardDAO.getInstance();
		if(kind.equals("new")) {
			dao.boardInsert(btype, btitle, bcontent, pw);
			
			response.sendRedirect("boardList?btype="+btype+"&page=1");
		}else {
			//modify
			dao.boardUpdate(btype, seq, btitle, bcontent, pw);
			
			response.sendRedirect("boardDetail?btype="+btype+"&page="+page+"&seq="+seq);
		}

		
	}

}
