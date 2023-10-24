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
	*{padding:0; margin: 0;}
	/* header { 
		background-color: #ffffaa; font-size: 10pt;
	} */
	header { 
		background-color: #000000; font-size: 10pt;
	}
	header a {text-decoration: none; font-weight: bold;}
	header li { list-style: none;}
	header .gnb{width: 100%;	background-color: gray;}
	header .gnb ul {
		overflow: hidden;
		width:700px;
		height: 30px;
		line-height: 30px;
		margin: 0 auto;
	}
	/* header .gnb ul li {	float: left;	margin-right: 30px;} */
	header .gnb ul li {	float: left;	margin: 0 auto;}
	header .gnb a { 
		color : #ffffaa;
		/* color : #000000; */
		font-size: 0.7em;
		display: block;
		padding-left:15px;
		padding-right:15px;
	}
	header .logo{
		/* width:100px; */ 
		text-align:center;
		margin: 0 auto; font-size:2em;
		cursor: pointer;
	}
	header .lnb {
	  /* width: 100%; height: 40px; */
	  width: 100%; height: 40px;
	  border-top: 2px solid yellow;
		/* border-bottom: 2px dashed #FFFFFF; */
		border-bottom: 2px solid yellow;
		position:relative; /*서브메뉴가 main영역 위로 */
	}
	header .lnb ul{
		overflow: hidden;
		width:800px;
		margin: 0 auto;
	}
	header .lnb ul>li {
		margin: 5px;
		float:left;
		padding:5px 40px;
		line-height: 25px;
	}
	/* header .lnb li a {color: #003300;}
	header .lnb li {color: #003300;} */
	header .lnb li a {color: #FFFFFF;}
	header .lnb li {color: black;}/* yellow */
	/* header .lnb ul li .subMenu {display:none;} */
	/* header .lnb ul li:hover .subMenu {display: block; margin: 10px 0 0 0;} */
</style>
</head>
<body>
<header>
	<c:if test="${empty member and empty admin}"> <%-- 로그인 전 화면 --%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/joinView.do">회원가입</a></li>
				<li><a href="${conPath }/loginView.do">로그인</a></li>
                <b><a href="${conPath }/adminLoginView.do">관리자 모드</a></b>
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath}/main.do'">
			<img alt="" src="${conPath }/img/head_01.jpg">
		</div>
		
		<div class="lnb">
        	<ul>
        		<%-- <li><a href="${conPath }/intro.jsp">소개 </a></li> --%> 
        		<li><a href="http://liveandloud.co.kr/homepage/image/intro2.jpg">소개</a></li>
                <li><a href="${conPath }/equipmentList.do">장비</a></li>
                <li><a href="${conPath }/noticeList.do">공지사항</a></li>
                <li><a href="${conPath }/photoList.do">공연사진</a>
                <li><a href="${conPath }/boardList.do">게시판</a></li>
                <li><a href="http://liveandloud.co.kr/homepage/image/map.jpg">약도</a></li>
                <!-- <li><a href="../admin/map.jsp">약도</a></li> -->
        	</ul>
        </div>
	</c:if>
	<c:if test="${not empty member and empty admin}"> <%-- 사용자 모드 로그인 화면--%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/boardList.do">게시판</a></li>
				<li><a href="${conPath }/modifyView.do">정보수정</a></li>
				<li><a>${member.mname }님 &nbsp; ▶</a></li>	
				<li><a href="${conPath }/logout.do">로그아웃</a></li>
			</ul>
		</div>
		<%-- <div class="logo" onclick="location.href='${conPath}/boardList.do'">
			LOGO
		</div --%>>
		
	</c:if>
	<c:if test="${empty member and not empty admin}"> <%-- 관리자 모드 로그인 화면--%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/logout.do">관리자모드나가기</a></li>
				<li><a href="${conPath }/boardList.do">게시판</a></li>
				<li><a href="${conPath }/noticeList.do">공지사항</a></li>
				<li><a href="${conPath }/equipmentList.do">장비</a></li>
				<li><a href="${conPath }/photoList.do">공연사진</a></li>
				<li><a>${admin.aname }님 &nbsp; ▶</a></li>	
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath}/allView.do'">
			LOGO
		</div>
		
	</c:if>
</header>
</body>
</html>