package kr.co.happy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Input;

@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btype = request.getParameter("btype");
		String page = request.getParameter("page");
		
		//default 값
		int intBtype = 0; 
		if(btype != null) {
			intBtype = Integer.parseInt(btype);
		}
		
		int intPage = 1;
		if(page != null) {
			intPage = Integer.parseInt(page);
		}
		
		//DB에서 자료 가져오기
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardDTO> list = dao.getBaordList(intBtype, intPage);
		int indexPage = dao.getMaxlist(intBtype);
		
		request.setAttribute("idxPage", indexPage);
		request.setAttribute("data", list);
		request.setAttribute("btype", intBtype);
		request.setAttribute("page", intPage);
		request.setAttribute("target", "boardList");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
