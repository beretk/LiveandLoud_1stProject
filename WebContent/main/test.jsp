<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
        ul{overflow: hidden;}
        li{float: left; list-style: none; margin: 3px;}
      </style>
    <script>
        var overImg = ['../img/fmenu_011.jpg','../img/fmenu_021.jpg','../img/fmenu_031.jpg','../img/fmenu_041.jpg'];
        var outImg = ['../img/fmenu_012.jpg','../img/fmenu_022.jpg','../img/fmenu_032.jpg','../img/fmenu_042.jpg'];
        var img;
        window.onload = function(){
            imgs = document.getElementsByTagName('img');
                     
        };
        function mouseOver(n){
            imgs[n].setAttribute('src', overImg[n]);
        }
        function mouseOut(n){
            imgs[n].setAttribute('src', outImg[n]);
        }
    </script>
</head>
<body>
	<ul>
        <li>
        	<a href="#"><img src="../img/fmenu_012.jpg" onmouseover="mouseOver(0)" onmouseout="mouseOut(0)"/></a>
        </li>
        <li>
        	<a href="#"><img src="../img/fmenu_022.jpg" onmouseover="mouseOver(1)" onmouseout="mouseOut(1)"/></a>
        </li>
        <li>
        	<a href="#"><img src="../img/fmenu_032.jpg" onmouseover="mouseOver(2)" onmouseout="mouseOut(2)"/></a>
        </li>
        <li>
        	<a href="#"><img src="../img/fmenu_042.jpg" onmouseover="mouseOver(3)" onmouseout="mouseOut(3)"/></a>
        </li>
      </ul>
</body>
</html>