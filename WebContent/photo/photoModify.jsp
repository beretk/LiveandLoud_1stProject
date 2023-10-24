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
			height:400px;
			margin: 100px auto 0px;
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
	<form action="${conPath }/photoModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="pid" value="${photo.pid }">
		<input type="hidden" name="dbFileName" value="${photo.pfileName }">
		<table>
			<caption>${photo.aid }번 글 수정</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required"
								value="${photo.aname }(${photo.aid })" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
					<td><input type="text" name="ptitle" required="required"
								value="${photo.ptitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" 
							name="pcontent">${photo.pcontent }</textarea></td>
			</tr>
			<tr><th>첨부파일</th>
					<td><input type="file" name="pfileName" class="btn"> 원첨부파일:
							<c:if test="${not empty photo.pfileName }">
						 		<a href="${conPath }/photoUp/${photo.pfileName}" target="_blank">${photo.pfileName}</a>
						 	</c:if>
						 	<c:if test="${empty photo.pfileName }">
						 		첨부파일없음
						 	</c:if>
					</td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="수정" class="btn">
						<input type="reset" value="이전" class="btn"
						  onclick="history.back()">
						<input type="button" value="목록"  class="btn"
							onclick="location='${conPath}/photoList.do?pageNum=${param.pageNum }'">
					</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>