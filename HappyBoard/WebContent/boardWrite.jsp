<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.happy.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("data");
%>
<div class="tb_alldiv">
	<form action="boardWrite?btype=${btype}&seq=${seq}" method="post">
	<input type="hidden" value="${kind}" name="kind">
	<input type="hidden" value="${page}" name="page">
		<div class="tb_div">
		<%if(list != null){ %>
			<table>
			<%for(BoardDTO vo : list){ %>
				<tr>
					<td>제목</td>
					<td> 
						<input type="text" name="btitle" value="<%=vo.getBtitle()%>" required>
					</td> 
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="bcontent" required><%=vo.getBcontent() %></textarea>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="pw" value="<%=vo.getPw()%>" required>
					</td>
				</tr>
			<%} %>
			</table>
		<%}else{ %>
		<table>
				<tr>
					<td>제목</td>
					<td> 
						<input type="text" name="btitle" required>
					</td> 
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="bcontent" required></textarea>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="pw" required>
					</td>
				</tr>
			</table>
		<%} %>
		</div>
		<div class="btn">
			<input type="submit" value="작성">
			<input type="button" value="취소" onclick="javascript: history.back();">
		</div>
	</form>
</div>