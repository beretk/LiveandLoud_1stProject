package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.PhotoDao;
import com.lec.liveandloud.dto.PhotoDto;

public class PhotoReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		PhotoDao photoDao = PhotoDao.getInstance();
		PhotoDto originPhoto = photoDao.getPhotoNotHitUp(pid);
		request.setAttribute("originPhoto", originPhoto); // 답변글을 달려는 글의 원글정보
	}

}