<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath}/css/style.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	
</head>
<body>
	<header>
        <div class="gnb">
            <ul>
                <li><a href="#Login">Login</a></li>
                <li><a href="#Join Us">Join Us</a></li>                
            </ul>
        </div>
        <div class="logo">
            <h1><img src="http://liveandloud.co.kr/homepage/image/head_01.jpg"></h1>
        </div>
        <div class="lnb">
        	<ul>
        		<li><a href="#Wish List">소개 </a></li>
                <li><a href="#Cart">장비</a></li>
                <li><a href="#My Page">공연</a></li>
                <li><a href="#Notice">공지</a></li>
                <li><a href="#QnA">보드</a></li>
                <li><a href="#Review">자료</a></li>
                <li><a href="#Favorit">포토</a></li>
                <li><a href="#Review">약도</a></li>
        	</ul>
        </div>
        <div class="main">
            <h1><img src="http://liveandloud.co.kr/homepage/image/daemoon.jpg"></h1>
        </div>
       <div class="hitmenu">
            <ul>
                <li><a href="#hit4">오디션신청</a></li>
                <li><a href="#hit3">이달의 공연일정</a></li>
                <li><a href="#hit2">공연방송보기</a></li>
                <li><a href="#hit1">공연예매하기</a></li>
            </ul>
        </div>
    </header>
	<ul>
		<img src="${conPath }/fmenu_011.jpg" alt="오디션신청 선택해제">
		<img src="fmenu_021.jpg" alt="이달의 공연일정 선택해제">
		<img src="fmenu_031.jpg" alt="공연방송 보기 선택해제">
		<img src="img/3out.jpg" alt="link 선택해제">
    </ul>
    <div id="footer">
        <div>office_logo</div>
        <div>office_address</div>
        <div>shopping_info</div>
        <div>Copyright ⓒ Live and Loud. All Rights reserver.</div>
    </div>
</body>
</html>