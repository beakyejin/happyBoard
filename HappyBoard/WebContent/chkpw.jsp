<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="chk_box">
	<form action="checkPw?btype=${btype}&seq=${seq}" method="post">
	<input type="hidden" value="${kind}" name="kind">
	<input type="hidden" value="${page}" name="page">
		<p>비밀번호를 입력해주세요</p>
		<input type="password" name="pw" required>
		<input type="submit" value="확인">
	</form>
</div>