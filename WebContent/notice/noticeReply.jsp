<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>★★★ 클럽 라이브앤라우드 공식 홈페이지 ★★★</title>
</head>
<body>
	<c:if test="${empty admin }"> <!-- 로그인 후에만 해당 답글을 달 수 있음 -->
		<script>
			location.href='${conPath}/adminLoginView.do?next=noticeReplyView.do?nid=${param.nid }&pageNum=${param.pageNum }';
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/noticeReply.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="ngroup" value="${originNotice.ngroup }">
			<input type="hidden" name="nstep" value="${originNotice.nstep }">
			<input type="hidden" name="nindent" value="${originNotice.nindent }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<caption>${originNotice.nid }번글의 답변쓰기 폼</caption>
				<tr><td>작성자</td><td>${admin.aname }(${admin.aid })</td></tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="ntitle" required="required"
								value="[답]${originNotice.ntitle }">
					</td>
				</tr>
				<tr><td>본문</td><td><textarea name="nContent" rows="3" cols="3"></textarea></td></tr>
				<tr><td>첨부파일</td><td><input type="file" name="nfilName"></td></tr>
				<tr><td colspan="2">
							<input type="submit" value="답변쓰기" class="btn">
							<input type="reset" value="취소" class="btn">
							<input type="button" value="목록" class="btn"
								onclick="location.href='${conPath}/noticeList.do?pageNum=${param.pageNum }'">
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>