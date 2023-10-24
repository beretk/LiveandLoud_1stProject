<%@page import="com.lec.liveandloud.dto.EquipmentDto"%>
<%@page import="com.lec.liveandloud.dao.EquipmentDao"%>
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
	EquipmentDao dao = EquipmentDao.getInstance();
	EquipmentDto dto = new EquipmentDto();
	for(int i=0 ; i<70 ; i++){
		dto.setEtitle("좋은 정보 " + i);
		dto.setEcontent(i+"번째 본문");
		dto.setEip("192.168.10."+i);
		if(i%5!=0){
			dto.setAid("admin");
			dto.setEfileName(null);
			dao.writeEquipment(dto);			
		}else if(i%5==0){
			dto.setAid("admin");
			dto.setEfileName("a.docx");
			dao.writeEquipment(dto);		
		}	
	}
	response.sendRedirect("../equipmentList.do");
%>
</body>
</html>