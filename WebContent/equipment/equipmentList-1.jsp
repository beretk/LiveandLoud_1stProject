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
	<STYLE>
		BODY{
			overflow: hidden; 
			float: center;
			SCROLLBAR-FACE-COLOR: 	#4a4a4a;
			SCROLLBAR-HIGHLIGHT-COLOR: 	#7d7d7d;
			SCROLLBAR-SHADOW-COLOR: 	#323232;
			SCROLLBAR-3DLIGHT-COLOR: 	#111111;
			SCROLLBAR-ARROW-COLOR: 	#bbbbcc;
			SCROLLBAR-TRACK-COLOR: 	#333333;
			SCROLLBAR-DARKSHADOW-COLOR: 	#000000;
			SCROLLBAR-BASE-COLOR: 	#789789;
			
		}
		body table{width: 1000px; height:550px; 
			margin:0 auto ;}
	</STYLE>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<table>
			<tr>
                <td width="100%" align="center">                                    
	                 <p>
	                 	<iframe frameborder="0"
	                       height="480" leftmargin="0" marginheight="0"
	                       marginwidth="0" scrolling="auto"
	                       src="http://liveandloud.co.kr/bbs/zboard.php?id=equip_club" topmargin="0"
	                       width="790" vspace="0" hspace="0" align="middle" name="frame">
	                     </iframe>
	                 </p>
	            </td>
			</tr>
		</table>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>