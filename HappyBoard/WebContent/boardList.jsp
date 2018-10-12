<%@page import="kr.co.happy.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("data");
	int btype = (int)request.getAttribute("btype");
	int _page = (int)request.getAttribute("page");
%>
<% if(list.size() > 0){ %>
<div class="tb_alldiv">
	<div class="tb_div">
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th class="tb_title">제목</th>
					<th>작성 날짜</th>
				</tr>
			</thead>
			<tbody>
			<% for(BoardDTO a: list){ %>
				<tr>
					<td><%=a.getSeq() %></td>
					<td>
						<a href="boardDetail?btype=<%=btype%>&page=<%=_page%>&seq=<%=a.getSeq() %>">
							<%=a.getBtitle() %></a></td>
					<td><%=a.getBregdate() %></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
<% }else {%>
	게시글이 없습니다.
<% }%>
	<div class="btn">
		<input type="button" value="글쓰기" onclick="location.href='boardWrite?btype=<%=btype%>&page=<%=_page%>&seq=0'">
	</div>
</div>