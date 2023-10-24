package com.lec.liveandloud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.liveandloud.dao.BoardDao;
import com.lec.liveandloud.dao.MemberDao;
import com.lec.liveandloud.dto.MemberDto;

public class MWithdrawalService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 탈퇴할 사람의 mid를 세션에서 가져와서 탈퇴할 회원의 글 모두 사게 -> 회원탈퇴 -> 세션삭제 -> 뷰에 alert할 메세지 setAttribute
		HttpSession session = request.getSession();
		String mid = null;
		MemberDto sessionMember = (MemberDto)session.getAttribute("member");
		if(sessionMember != null) {
			mid = sessionMember.getMid();
		}
		BoardDao bDao = BoardDao.getInstance();
		int cnt = bDao.preWithdrawalMemberStep(mid); // 탈퇴할 회원의 글 모두 삭제
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.withdrawalMember(mid); // 회원탈퇴
		session.invalidate(); // 세션 삭제
		if(result==MemberDao.SUCCESS) {
			request.setAttribute("withdrawalResult", "회원 탈퇴가 완료되었습니다. 작성하신 글 " + cnt+"개 모두 다 삭제되었습니다");
		}else {
			request.setAttribute("withdrawalResult", "로그인이 되어 있지 않습니다");
		}
	}

}
