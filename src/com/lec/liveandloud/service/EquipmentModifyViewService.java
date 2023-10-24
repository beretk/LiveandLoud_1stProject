package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.EquipmentDao;
import com.lec.liveandloud.dao.PhotoDao;
import com.lec.liveandloud.dto.EquipmentDto;
import com.lec.liveandloud.dto.PhotoDto;

public class EquipmentModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		EquipmentDao equipmentDao = EquipmentDao.getInstance();
		EquipmentDto equipmentDto = equipmentDao.getEquipmentNotHitUp(eid);
		request.setAttribute("equipment", equipmentDto);
	}

}
