package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.BoardDao;
import com.lec.liveandloud.dao.EquipmentDao;

public class EquipmentDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int egroup = Integer.parseInt(request.getParameter("egroup"));
		int estep = Integer.parseInt(request.getParameter("estep"));
		int eindent = Integer.parseInt(request.getParameter("eindent"));
		EquipmentDao equipmentDao = EquipmentDao.getInstance();
		int deleteCnt = equipmentDao.deleteEquipment(egroup, estep, eindent);
		if(deleteCnt >= EquipmentDao.SUCCESS) {
			request.setAttribute("equipmentResult", "글(답변글 포함) "+deleteCnt+"개 글 삭제 성공");
		}else {
			request.setAttribute("equipmentResult", "글(답변글도 모두) 삭제 안 됨");
		}
	}
}
