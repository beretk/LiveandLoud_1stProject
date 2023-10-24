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
			height:470px;
			margin: 30px auto 0px;
		}
		#content_form table tr { height: 10px;}
	</style>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		$(document).ready(function(){
			$('tr').click(function(){
				var pid = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
				if(!isNaN(pid)){
					location.href = '${conPath}/photoContent.do?pid='+ pid + '&pageNum=${pageNum}';
				}
			});
		});
	</script>
</head>
<body>
	<c:if test="${not empty photoResult }">
		<script>alert('${photoResult}');</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
		<div id="content_form">
		<table>
			<tr>
				<td>
						<c:if test="${not empty admin }"><a href="${conPath }/photoWriteView.do">글쓰기</a></c:if>
						<c:if test="${empty admin }"><a href="${conPath }/adminLoginView.do?next=photoWriteView.do">글쓰기</a></c:if>
				</td>
			</tr>
		</table>
		<br>
		<table>
			<tr><th>글번호</th><th>작성자</th><th>글제목</th><th>조회수</th><th>날짜</th><th>ip</th></tr>
			<c:if test="${totCnt==0 }">
				<tr><td colspan="6">등록된 글이 없습니다</td></tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${photoList }" var="photo">
					<tr><td>${photo.pid }</td>
							<td>${photo.aname }</td>
							<td class="left">
								<c:forEach var="i" begin="1" end="${photo.pindent }">
									<c:if test="${i==photo.pindent }">└─</c:if>
									<c:if test="${i!=photo.pindent }"> &nbsp; &nbsp; </c:if>
								</c:forEach>
								${photo.ptitle } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
								<c:if test="${not empty photo.pfileName }">
									<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
								</c:if>
							</td>
							<td>${photo.phit }</td>
							<td><fmt:formatDate value="${photo.prdate }" type="date" dateStyle="short"/></td>
							<td>${photo.pip }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/photoList.do?pageNum=${startPage-1}"> 이전 </a> ]
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
					[ <a href="${conPath }/photoList.do?pageNum=${i}"> ${i } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/photoList.do?pageNum=${endPage+1}"> 다음 </a> ]
			</c:if>
		</div>
	</div>
	
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>