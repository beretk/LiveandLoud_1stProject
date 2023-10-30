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
		.map_wrap {
			width: 900px;
			padding-left: 130px;
			margin: 30px auto;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div class="map_wrap">
		<img alt="" src="${conPath }/img/map.jpg">
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>