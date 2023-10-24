package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.NoticeDao;
import com.lec.liveandloud.dto.NoticeDto;

public class NoticeReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nid = Integer.parseInt(request.getParameter("nid"));
		NoticeDao noticeDao = NoticeDao.getInstance();
		NoticeDto originNotice = noticeDao.getNoticeNotHitUp(nid);
		request.setAttribute("originNotice", originNotice); // 답변글을 달려는 글의 원글정보
	}

}
