package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.PhotoDao;
import com.lec.liveandloud.dto.PhotoDto;

public class PhotoModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		PhotoDao photoDao = PhotoDao.getInstance();
		PhotoDto photoDto = photoDao.getPhotoNotHitUp(pid);
		request.setAttribute("photo", photoDto);
	}

}
