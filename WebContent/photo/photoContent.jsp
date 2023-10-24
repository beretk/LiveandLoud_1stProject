<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>★★★ 클럽 라이브앤라우드 공식 홈페이지 ★★★</title>
	<link href="${conPath}/css/style.css" rel="stylesheet">
	<style>
		#content_form {
			height:800px;
			margin: 50px auto 0px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table>
			<caption>${photo.pid }글 상세보기</caption>
			<tr><td>작성자</td><td>${photo.aname} (${photo.aid}) 님</td>	</tr>
			<tr><td>제목</td><td>${photo.ptitle }</td></tr>
			<tr><td>본문</td><td><pre>${photo.pcontent}</pre></td></tr>
			<tr><th>조회수</th><td>${photo.phit }</td></tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${not empty photo.pfileName }">
						<a href="${conPath }/photoUp/${photo.pfileName}" target="_blank">${photo.pfileName}</a>
					</c:if>
					<c:if test="${empty photo.pfileName }">
						첨부파일없음
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${admin.aid eq photo.aid }">
				 		<button onclick="location='${conPath}/photoModifyView.do?pid=${photo.pid }&pageNum=${param.pageNum }'">수정</button>
				 	</c:if>
				 	<c:if test="${admin.aid eq photo.aid or not empty admin}">
						<button onclick="location='${conPath}/photoDelete.do?pid=${photo.pid }&pageNum=${param.pageNum }'">삭제</button>
			 		</c:if>
				 	<button onclick="location='${conPath}/photoList.do?pageNum=${param.pageNum }'">목록</button>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>

