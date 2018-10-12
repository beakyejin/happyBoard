package kr.co.happy;

import java.io.IOException;

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
		
		request.setAttribute("btype", btype);
		request.setAttribute("seq", seq);
		request.setAttribute("target", "chkpw");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO: 비밀번호 체크 후 이동
	}

}
