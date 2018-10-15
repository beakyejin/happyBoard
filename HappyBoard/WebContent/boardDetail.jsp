<%@page import="kr.co.happy.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("data");
%>
<div class="tb_alldiv">
	<div class="tb_div">
		<table>
		<%for(BoardDTO vo : list){ %>
			<tr>
				<td>제목</td>
				<td><%=vo.getBtitle() %></td>
			</tr>
			<tr>
				<td>작성 일시</td>
				<td><%=vo.getBregdate() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><%=vo.getBcontent() %></td>
			</tr>
		</table>
	</div>
	<div class="btn">
		<input type="button" value="목록" onclick="location.href='boardList?btype=${btype}&page=${page}'">
		<input type="button" value="수정" onclick="location.href='checkPw?btype=${btype}&seq=<%=vo.getSeq()%>&page=${page}&kind=modify'">
		<input type="button" value="삭제" onclick="location.href='checkPw?btype=${btype}&seq=<%=vo.getSeq()%>&page=${page}&kind=delete'">
	</div>
	<%} %>
</div>
