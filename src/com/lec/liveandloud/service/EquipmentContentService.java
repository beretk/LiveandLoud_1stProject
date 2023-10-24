package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.EquipmentDao;
import com.lec.liveandloud.dto.EquipmentDto;

public class EquipmentContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		EquipmentDao eDao = EquipmentDao.getInstance();
		if(request.getParameter("after") == null) {
			eDao.hitUp(eid);
		}
		EquipmentDto equipment = eDao.getEquipmentNotHitUp(eid);
		request.setAttribute("equipment", equipment);

	}

}
