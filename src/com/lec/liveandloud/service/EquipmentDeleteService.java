package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.BoardDao;
import com.lec.liveandloud.dao.EquipmentDao;
import com.lec.liveandloud.dao.NoticeDao;

public class EquipmentDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		EquipmentDao equipmentDao = EquipmentDao.getInstance();
		int deleteCnt = equipmentDao.deleteEquipment(eid);
		if(deleteCnt >= EquipmentDao.SUCCESS) {
			request.setAttribute("equipmentResult", "글 " + deleteCnt + "개 글 삭제 성공");
		}
	}
}
