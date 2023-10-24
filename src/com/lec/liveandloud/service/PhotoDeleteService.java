package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.PhotoDao;

public class PhotoDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pgroup = Integer.parseInt(request.getParameter("pgroup"));
		int pstep = Integer.parseInt(request.getParameter("pstep"));
		int pindent = Integer.parseInt(request.getParameter("pindent"));
		PhotoDao photoDao = PhotoDao.getInstance();
		int deleteCnt = photoDao.deletePhoto(pgroup, pstep, pindent);
		if(deleteCnt >= PhotoDao.SUCCESS) {
			request.setAttribute("photoResult", "글(답변글 포함) "+deleteCnt+"개 글 삭제 성공");
		}else {
			request.setAttribute("photoResult", "글(답변글도 모두) 삭제 안 됨");
		}
	}
}
