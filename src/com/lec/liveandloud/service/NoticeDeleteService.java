package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.NoticeDao;

public class NoticeDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int ngroup = Integer.parseInt(request.getParameter("ngroup"));
		int nstep = Integer.parseInt(request.getParameter("nstep"));
		int nindent = Integer.parseInt(request.getParameter("nindent"));
		NoticeDao noticeDao = NoticeDao.getInstance();
		int deleteCnt = noticeDao.deleteNotice(ngroup, nstep, nindent);
		if(deleteCnt >= NoticeDao.SUCCESS) {
			request.setAttribute("noticeResult", "글(답변글 포함) "+ deleteCnt+"개 글 삭제 성공");
		}else {
			request.setAttribute("noticeResult", "글(답변글도 모두) 삭제 안 됨");
		}
	}
}
