package com.lec.liveandloud.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lec.liveandloud.service.*;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		if(command.equals("/main.do")) {
			viewPage = "main/main.jsp";
			/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
			 * * * * * * * * * * member 관련 요청  * * * * * * * * * *
			 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		}else if(command.equals("/loginView.do")) { // 로그인 화면
			viewPage = "member/login.jsp";
		}else if(command.equals("/login.do")) { // DB를 통해 로그인 확인 및 세션 처리
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/joinView.do")) { // 회원가입 화면
			viewPage = "member/join.jsp";
		}else if(command.equals("/midConfirm.do")) {
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/midConfirm.jsp";
		}else if(command.equals("/memailConfirm.do")) {
			service = new MemailConfirmService();
			service.execute(request, response);
			viewPage = "member/memailConfirm.jsp";
		}else if(command.equals("/join.do")) { // 회원가입 DB처리후 로그인으로 가고 id엔 가입한 id가 출력
			service = new MJoinService();
			service.execute(request, response);
			viewPage = "loginView.do";
		}else if(command.equals("/logout.do")) { // 로그아웃 - 세션날리기
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/modifyView.do")) { // 정보 수정 화면
			viewPage = "member/modify.jsp";
		}else if(command.equals("/modify.do")) {// 정보수정 DB처리후 세션도 수정
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/allView.do")) { // 전체 회원리스트
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/mAllView.jsp";
		}else if(command.equals("/withdrawal.do")) { // 회원탈퇴
			service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * * * admin 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		}else if(command.equals("/adminLoginView.do")) {	
			viewPage = "admin/adminLogin.jsp";
		}else if(command.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "allView.do";
			
					
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * 파일첨부 게시판 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		}else if(command.equals("/boardList.do")) {
			service = new BoardListService();
			service.execute(request, response);
			viewPage = "fileBoard/boardList.jsp";
		}else if(command.equals("/boardWriteView.do")) {
			viewPage = "fileBoard/boardWrite.jsp";
		}else if(command.equals("/boardWrite.do")) {
			service = new BoardWriteService();
			service.execute(request, response);
			viewPage = "boardList.do";
		}else if(command.equals("/boardContent.do")) {
			service = new BoardContentService();
			service.execute(request, response);
			viewPage = "fileBoard/boardContent.jsp";
		}else if(command.equals("/boardModifyView.do")) {
			service = new BoardModifyViewService();
			service.execute(request, response);
			viewPage = "fileBoard/boardModify.jsp";
		}else if(command.equals("/boradModify.do")) {
			service = new BoardModifyService();
			service.execute(request, response);
			viewPage = "boardList.do";
		}else if(command.equals("/boardDelete.do")) {
			service = new BoardDeleteService();
			service.execute(request, response);
			viewPage = "boardList.do";
		}else if(command.equals("/boardReplyView.do")) {
			service = new BoardReplyViewService();
			service.execute(request, response);
			viewPage = "fileBoard/boardReply.jsp";
		}else if(command.equals("/boardReply.do")) {
			service = new BoardReplyService();
			service.execute(request, response);
			viewPage = "boardList.do";
			
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * 공지 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		}else if(command.equals("/noticeList.do")) {
			service = new NoticeListService();
			service.execute(request, response);
			viewPage = "notice/noticeList.jsp";
		}else if(command.equals("/noticeWriteView.do")) {
			viewPage = "notice/noticeWrite.jsp";
		}else if(command.equals("/noticeWrite.do")) {
			service = new NoticeWriteService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		}else if(command.equals("/noticeContent.do")) {
			service = new NoticeContentService();
			service.execute(request, response);
			viewPage = "notice/noticeContent.jsp";
		}else if(command.equals("/noticeModifyView.do")) {
			service = new NoticeModifyViewService();
			service.execute(request, response);
			viewPage = "notice/noticeModify.jsp";
		}else if(command.equals("/noticeModify.do")) {
			service = new NoticeModifyService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		}else if(command.equals("/noticeDelete.do")) {
			service = new NoticeDeleteService();
			service.execute(request, response);
			viewPage = "noticeList.do";		
		}else if(command.equals("/noticeReplyView.do")) {
			service = new NoticeReplyViewService();
			service.execute(request, response);
			viewPage = "notice/noticeReply.jsp";
		}else if(command.equals("/noticeReply.do")) {
			service = new NoticeReplyService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		
			
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * 공연사진 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		}else if(command.equals("/photoList.do")) {
			service = new PhotoListService();
			service.execute(request, response);
			viewPage = "photo/photoList.jsp";
		}else if(command.equals("/photoWriteView.do")) {
			viewPage = "photo/photoWrite.jsp";
		}else if(command.equals("/photoWrite.do")) {
			service = new PhotoWriteService();
			service.execute(request, response);
			viewPage = "photoList.do";
		}else if(command.equals("/photoContent.do")) {
			service = new PhotoContentService();
			service.execute(request, response);
			viewPage = "photo/photoContent.jsp";
		}else if(command.equals("/photoModifyView.do")) {
			service = new PhotoModifyViewService();
			service.execute(request, response);
			viewPage = "photo/photoModify.jsp";
		}else if(command.equals("/photoModify.do")) {
			service = new PhotoModifyService();
			service.execute(request, response);
			viewPage = "photoList.do";
		}else if(command.equals("/photoDelete.do")) {
			service = new PhotoDeleteService();
			service.execute(request, response);
			viewPage = "photoList.do";		
		}else if(command.equals("/photoReplyView.do")) {
			service = new PhotoReplyViewService();
			service.execute(request, response);
			viewPage = "photo/photoReply.jsp";
		}else if(command.equals("/photoReply.do")) {
			service = new PhotoReplyService();
			service.execute(request, response);
			viewPage = "photoList.do"	;
			
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * 장비 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		}else if(command.equals("/equipmentList.do")) {
			service = new EquipmentListService();
			service.execute(request, response);
			viewPage = "equipment/equipmentList.jsp";
		}else if(command.equals("/equipmentWriteView.do")) {
			viewPage = "equipment/equipmentWrite.jsp";
		}else if(command.equals("/equipmentWrite.do")) {
			service = new EquipmentWriteService();
			service.execute(request, response);
			viewPage = "equipmentList.do";
		}else if(command.equals("/equipmentContent.do")) {
			service = new EquipmentContentService();
			service.execute(request, response);
			viewPage = "equipment/equipmentContent.jsp";
		}else if(command.equals("/equipmentModifyView.do")) {
			service = new EuipmentModifyViewService();
			service.execute(request, response);
			viewPage = "equipment/equipmentModify.jsp";
		}else if(command.equals("/equipmentModify.do")) {
			service = new EquipmentModifyService();
			service.execute(request, response);
			viewPage = "equipmentList.do";
		}else if(command.equals("/equipmentDelete.do")) {
			service = new EquipmentDeleteService();
			service.execute(request, response);
			viewPage = "equipmentList.do";		
		}else if(command.equals("/equipmentReplyView.do")) {
			service = new EquipmentReplyViewService();
			service.execute(request, response);
			viewPage = "equipment/equipmentReply.jsp";
		}else if(command.equals("/equipmentReply.do")) {
			service = new EquipmentReplyService();
			service.execute(request, response);
			viewPage = "equipmentList.do"	;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}


















