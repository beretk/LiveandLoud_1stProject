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
	
	<style>
		#content_form {
			/* border:1px solid yellow; */
			width: 800px; height:360px; line-height:400px;
			margin: 0 auto; text-align: center; font-size: 3em;
		}
		header a {text-decoration: none; font-weight: bold;}
		header li { list-style: none;}
		header .gnb{width: 100%;	background-color: gray;} 
		header .gnb ul {
		overflow: hidden;
		width:800px;
		height: 20px;
		line-height: 30px;
		margin: 0 auto;
		}
		header .gnb ul li {	float: left;	margin-right: 30px;}
		header .gnb a { 
			color : #ffffaa;
			/* color : #000000; */
			font-size: 0.8em;
			display: block;
			padding-left:15px;
			padding-right:15px;
		}
		.button ul{
			width: 265px; margin: 0 auto; 
			/* border: 1px solid yellow; */
			padding-left:5px;
			overflow: hidden;
		}
		.button ul li{
			float: left; list-style: none; margin: 3px;
		}
		
		.business ul{
			width: 500px; margin: 0 auto; 
			/* border: 1px solid yellow; */
			padding-left:35px;
			padding-top:20px;
			padding-bottom:10px;
			overflow: hidden;
		}
		.business ul li{
			float: left; list-style: none; margin: 3px;
		}
		
	</style>
	<script>
        var overImg = ['${conPath}/img/fmenu_022.jpg','${conPath}/img/fmenu_032.jpg'];
        var outImg = ['${conPath}/img/fmenu_021.jpg','${conPath}/img/fmenu_031.jpg'];
        var img;
        window.onload = function(){
            imgs = document.getElementsByTagName('img');
                     
        };
        function mouseOver(n){
        	imgs[n].setAttribute('src', overImg[n-2]);
        }
        function mouseOut(n){
            imgs[n].setAttribute('src', outImg[n-2]);
        }
    </script>
</head>
<body>
	<c:if test="${not empty param.next && empty loginErrorMsg && empty modifyResult}">
		<script>
			location.href = '${conPath}/${param.next}';
		</script>
	</c:if>
	<c:if test="${not empty loginErrorMsg }">
		<script>
			alert('${loginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty withdrawalResult }">
		<script>
			alert('${withdrawalResult}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	
	<div id="content_form">
		<!-- main --> 
		<img alt="" src="${conPath }/img/daemoon.jpg">
	</div>
	<div class="button">
		<ul>
	        <li>
	        	<a href="http://liveandloud.co.kr/homepage/live_schedule.htm">
	        		<img src="${conPath }/img/fmenu_021.jpg" onmouseover="mouseOver(2)" onmouseout="mouseOut(2)"/>
	        	</a>
	        </li>
	        <li>
	        	<a href="http://liveandloud.co.kr/homepage/lable_casting.htm">
	        		<img src="${conPath }/img/fmenu_031.jpg" onmouseover="mouseOver(3)" onmouseout="mouseOut(3)"/>
	        	</a>
        	</li>
      	</ul>
	</div>
	<div class="business">
		<ul>
			<li>
				<a href="http://www.lovecoal.org/">
					<img src="${conPath }/img/mou_lovecoal.jpg"/>
				</a>
			</li>
			<li>
				<a href="http://nangokmaeul.org/">
					<img src="${conPath }/img/mou_nangok.jpg"/>
				</a>
			</li>
			<li>
				<a href="https://cafe.naver.com/savekoreandogs">
					<img src="${conPath }/img/mou_sakodog.jpg"/>
				</a>
			</li>
			<li>
				<a href="https://map.naver.com/v5/entry/place/18814427?c=14122382.4577742,4459284.8371849,13,0,0,0,dh&placePath=%2Fhome%3Fentry=plt">
					<img src="${conPath }/img/mou_peniel.jpg"/>
				</a>
			</li>
		</ul>
	</div>
	
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>