<%@page import="com.lec.liveandloud.dto.PhotoDto"%>
<%@page import="com.lec.liveandloud.dao.PhotoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>★★★ 클럽 라이브앤라우드 공식 홈페이지 ★★★</title>
</head>
<body>
<%
	PhotoDao dao = PhotoDao.getInstance();
	PhotoDto dto = new PhotoDto();
	for(int i=0 ; i<70 ; i++){
		dto.setPtitle("좋은 정보 " + i);
		dto.setPcontent(i+"번째 본문");
		dto.setPip("192.168.10."+i);
		if(i%5!=0){
			dto.setAid("admin");
			dto.setPfileName(null);
			dao.writePhoto(dto);			
		}else if(i%5==0){
			dto.setAid("admin");
			dto.setPfileName("a.docx");
			dao.writePhoto(dto);		
		}	
	}
	response.sendRedirect("../photoList.do");
%>
</body>
</html>