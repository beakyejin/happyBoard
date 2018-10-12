<%@page import="kr.co.happy.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("data");
	int btype = (int)request.getAttribute("btype");
	int _page = (int)request.getAttribute("page");
%>
<div class="tb_alldiv">
	<div class="tb_div">
		<table>
		<%for(BoardDTO a : list){ %>
			<tr>
				<td>제목</td>
				<td><%=a.getBtitle() %></td>
			</tr>
			<tr>
				<td>작성 일시</td>
				<td><%=a.getBregdate() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><%=a.getBcontent() %></td>
			</tr>
		</table>
	</div>
	<div class="btn">
		<input type="button" value="목록" onclick="location.href='boardList?btype=<%=btype%>&page=<%=_page%>'">
		<input type="button" value="수정" onclick="location.href='checkpw?btype=<%=btype%>&seq=<%=a.getSeq()%>'">
		<%-- <input type="button" value="수정" onclick="location.href='boardWrite?btype=<%=btype%>&seq=<%=a.getSeq()%>'"> --%>
		<input type="button" value="삭제">
	</div>
	<%} %>
</div>
