package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.NoticeDao;
import com.lec.liveandloud.dto.NoticeDto;

public class NoticeContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nid = Integer.parseInt(request.getParameter("nid"));
		NoticeDao nDao = NoticeDao.getInstance();
		if(request.getParameter("after") == null) {
			nDao.hitUp(nid);
		}
		NoticeDto notice = nDao.getNoticeNotHitUp(nid);
		request.setAttribute("notice", notice);

	}

}