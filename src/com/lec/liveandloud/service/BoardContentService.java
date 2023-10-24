package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.liveandloud.dao.BoardDao;
import com.lec.liveandloud.dto.BoardDto;

public class BoardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fid = Integer.parseInt(request.getParameter("fid"));
		BoardDao bDao = BoardDao.getInstance();
		if(request.getParameter("after") == null) {
			bDao.hitUp(fid);
		}
		BoardDto board = bDao.getBoardNotHitUp(fid);
		request.setAttribute("board", board);

	}

}
