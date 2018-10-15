package kr.co.happy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkPw")
public class CheckPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int btype = Integer.parseInt(request.getParameter("btype"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		String kind = request.getParameter("kind"); 
		int page = Integer.parseInt(request.getParameter("page"));
		
		request.setAttribute("kind", kind);
		request.setAttribute("page", page);
		request.setAttribute("target", "chkpw");
		request.setAttribute("btype", btype);
		request.setAttribute("seq", seq);
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int btype = Integer.parseInt(request.getParameter("btype"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		int page = Integer.parseInt(request.getParameter("page"));
		String kind = request.getParameter("kind");
		String pw = request.getParameter("pw");
		
		BoardDAO dao = BoardDAO.getInstance();
		boolean flag = dao.chkPassword(btype, seq, pw);
		
		if(flag) {
			if(kind.equals("modify")) {
				response.sendRedirect("boardWrite?btype="+btype+"&page="+page+"&seq="+seq+"&kind=modify");
			}else if(kind.equals("delete")){
				dao.boardDelete(btype, seq);
				
				response.sendRedirect("boardList?btype="+btype+"&page=1");
			}
			
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호를 잘 못 입력하셨습니다!');"
					+ "location.href='boardDetail?btype="+btype+"&page="+page+"&seq="+seq+"';</script>");
			out.flush();
		}
	
	}

}
