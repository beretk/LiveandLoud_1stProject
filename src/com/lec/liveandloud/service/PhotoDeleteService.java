package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.NoticeDao;
import com.lec.liveandloud.dao.PhotoDao;

public class PhotoDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		PhotoDao photoDao = PhotoDao.getInstance();
		int deleteCnt = photoDao.deletePhoto(pid);
		if(deleteCnt >= PhotoDao.SUCCESS) {
			request.setAttribute("photoResult", "글 "+deleteCnt+"개 글 삭제 성공");
		}
	}
}
