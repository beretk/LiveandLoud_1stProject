<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>★★★ 클럽 라이브앤라우드 공식 홈페이지 ★★★</title>
<style>
	body { background-color: black; }/*yellow*/
	footer {height:80px;background-color: gray;}/*#000000*/
	footer #footer_conts, footer #footer_conts a {
		color:white;
		font-weight: blod;
		font-size:0.7em;
		text-align: center;
	}
	footer #footer_conts p:first-child {
		font-weight: bold; height: 20px; line-height: 30px;
	}
</style>
</head>
<body>
	<footer>
		<div id="footer_conts">
			<p>클럽 라이브앤라우드 | 사업자등록번호 110-10-12345 | 개인정보 및 청소년보호 책임자 손OO</p> 
			<p>서울특별시 마포구 동교로 191 DBM빌딩 B201 | mail master@liveandloud.co.kr</p>
			<p>Copyright© by Club Live & Loud. All rights reserved.</p>
		</div>
	</footer>
</body>
</html>