package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.NoticeDao;

public class NoticeDeleteService implements Service {

	@Override
	/*public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nid = Integer.parseInt(request.getParameter("nid"));
		NoticeDao nDao = new NoticeDao();
		int result = nDao.deleteNotice(nid);
		String deleteMsg = (result==NoticeDao.SUCCESS) ? nid + "번글 삭제 성공" : nid + "번글 삭제 실패";
		request.setAttribute("deleteMsg", deleteMsg);

	}*/
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nid = Integer.parseInt(request.getParameter("nid"));
		NoticeDao noticeDao = NoticeDao.getInstance();
		int deleteCnt = noticeDao.deleteNotice(nid);
		if(deleteCnt >= NoticeDao.SUCCESS) {
			request.setAttribute("noticeResult", "글 "+deleteCnt+"개 글 삭제 성공");
		}
	}
}