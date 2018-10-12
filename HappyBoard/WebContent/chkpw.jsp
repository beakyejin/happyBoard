<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int btype = (int)request.getAttribute("btype");
	int seq = (int)request.getAttribute("seq");
	//TODO : 비밀번호 체크 페이지
%>
<div class="chk_box">
	<form action="checkpw?btype=<%=btype%>&seq=<%=seq%>">
		<p>비밀번호르 입력해주세요</p>
		<input type="text" name="pw">
	</form>
</div>