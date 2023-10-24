<%@page import="com.lec.liveandloud.dto.NoticeDto"%>
<%@page import="com.lec.liveandloud.dao.NoticeDao"%>
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
	NoticeDao dao = NoticeDao.getInstance();
	NoticeDto dto = new NoticeDto();
	for(int i=0 ; i<70 ; i++){
		dto.setNtitle("좋은 정보 " + i);
		dto.setNcontent(i+"번째 본문");
		dto.setNip("192.168.10."+i);
		if(i%5!=0){
			dto.setAid("admin");
			dto.setNfileName(null);
			dao.writeNotice(dto);			
		}else if(i%5==0){
			dto.setAid("admin");
			dto.setNfileName("a.docx");
			dao.writeNotice(dto);		
		}	
	}
	response.sendRedirect("../noticeList.do");
%>
</body>
</html>