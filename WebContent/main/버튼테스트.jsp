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
	<link href="${conPath}/css/style.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
			const overImg = ['img/fmenu_012.jpg','img/fmenu_022.jpg','img/fmenu_032.jpg','img/fmenu_042.jpg'];
	        const outImg = ['img/fmenu_011.jpg','img/fmenu_021.jpg','img/fmenu_031.jpg','img/fmenu_041.jpg'];
	        const menu = ['오디션신청','이달의 공연일정','공연방송 보기','공연 예매하기 '];	        
		$(document).ready(function(){
			$('img').each(function(idx, item){
				$(this).mouseover(function(){
					$(this).attr('src',overImg[idx]);
					$(this).attr('alt', menu[idx]+ '선택');
				});
			$(this).mouseout(function(){
				$(this).attr('src',outImg[idx]);
				$(this).attr('alt', menu[idx]+ '선택 해제');
			});
			});
		});
	</script>
	<style>
		#content_form {
			width: 800px; height:500px; line-height:500px;
			margin: 0 auto; text-align: center; font-size: 3em;
		}
		header a {text-decoration: none; font-weight: bold;}
		header li { list-style: none;}
		header .gnb{width: 100%;	background-color: gray;} 
		header .gnb ul {
		overflow: hidden;
		width:700px;
		height: 20px;
		line-height: 30px;
		margin: 0 auto;
		}
		header .gnb ul li {	float: left;	margin-right: 30px;}
		header .gnb a { 
			color : #ffffaa;
			/* color : #000000; */
			font-size: 0.9em;
			display: block;
			padding-left:15px;
			padding-right:15px;
		}
	</style>
</head>
<body>
	
</body>
</html>