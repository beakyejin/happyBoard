<%@page import="kr.co.happy.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int btype = (int)request.getAttribute("btype");
	int seq = (int) request.getAttribute("seq");
	ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("data");
%>
<div class="tb_alldiv">
	<form action="boardWrite?btype=<%=btype%>&seq=<%=seq%>" method="post">
		<div class="tb_div">
		<%if(list.size() > 0){ %>
			<table>
				<tr>
					<td>제목</td>
					<td> 
						<input type="text" name="btitle">
					</td> 
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="bcontent"></textarea>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="pw">
					</td>
				</tr>
			</table>
		<%}else{ %>
			<table>
			<%for(BoardDTO a : list){ %>
				<tr>
					<td>제목</td>
					<td> 
						<input type="text" name="btitle" value="<%=a.getBtitle()%>">
					</td> 
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="bcontent"><%=a.getBcontent() %></textarea>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="pw" value="<%=a.getPw()%>">
					</td>
				</tr>
			<%} %>
			</table>
		<%} %>
		</div>
		<div class="btn">
			<input type="submit" value="작성">
		</div>
	</form>
</div>