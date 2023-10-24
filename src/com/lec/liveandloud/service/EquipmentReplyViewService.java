package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.EquipmentDao;
import com.lec.liveandloud.dto.EquipmentDto;

public class EquipmentReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		EquipmentDao equipmentDao = EquipmentDao.getInstance();
		EquipmentDto originEquipment = equipmentDao.getEquipmentNotHitUp(eid);
		request.setAttribute("originEquipment", originEquipment); 
	}

}