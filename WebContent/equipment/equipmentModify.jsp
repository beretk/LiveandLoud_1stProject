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
	<form action="${conPath }/equipmentModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="eid" value="${equipment.eid }">
		<input type="hidden" name="dbFileName" value="${equipment.efileName }">
		<table>
			<caption>${equipment.aid }번 글 수정</caption>
			<tr><th>제목</th>
					<td><input type="text" name="etitle" required="required"
								value="${equipment.etitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" 
							name="econtent">${equipment.econtent }</textarea></td>
			</tr>
			<tr><th>첨부파일</th>
					<td><input type="file" name="efileName" class="btn"> 원첨부파일:
							<c:if test="${not empty equipment.efileName }">
						 		<a href="${conPath }/equipmentUp/${equipment.efileName}" target="_blank">${equipment.efileName}</a>
						 	</c:if>
						 	<c:if test="${empty equipment.efileName }">
						 		첨부파일없음
						 	</c:if>
					</td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="수정" class="btn">
						<input type="reset" value="이전" class="btn"
						  onclick="history.back()">
						<input type="button" value="목록"  class="btn"
							onclick="location='${conPath}/equipmentList.do?pageNum=${param.pageNum }'">
					</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>