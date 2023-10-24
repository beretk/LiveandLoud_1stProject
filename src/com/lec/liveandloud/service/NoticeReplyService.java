package com.lec.liveandloud.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.liveandloud.dao.NoticeDao;
import com.lec.liveandloud.dto.AdminDto;
import com.lec.liveandloud.dto.NoticeDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("noticeUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		String nfileName = "";
		int result = NoticeDao.FAIL;
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			nfileName = mRequest.getFilesystemName(param);
			// aId, nTitle, nContent,  nileName, nIp
			HttpSession httpSession = request.getSession();
			AdminDto admin = (AdminDto)httpSession.getAttribute("admin");
			if(admin ==null) {
				request.setAttribute("noticeResult", "로그인 후에만 답글쓰기 가능");
				return;
			}
			String aid = admin.getAid();
			String ntitle = mRequest.getParameter("ntitle");
			String ncontent = mRequest.getParameter("ncontent");
			String nip = request.getRemoteAddr();
			int ngroup = Integer.parseInt(mRequest.getParameter("ngroup"));
			int nstep = Integer.parseInt(mRequest.getParameter("nstep"));
			int nindent = Integer.parseInt(mRequest.getParameter("nindent"));
			NoticeDao noticeDao = NoticeDao.getInstance();
			NoticeDto noticeDto = new NoticeDto(0, aid, null, ntitle, ncontent, nfileName, null, 0, ngroup, nstep, nindent, nip);
			result = noticeDao.reply(noticeDto);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == NoticeDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("noticeResult", "답글쓰기 성공");
			}else {
				request.setAttribute("noticeResult", "답글쓰기 실패");
			}
			//★mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set : modify,reply★
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("noticeResult", "답글쓰기 실패");
		}
		if(nfileName!=null && result == NoticeDao.SUCCESS) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+nfileName);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:/webPro/source/08_1stProject/LiveandLoud/WebContent/noticeUp/"+nfileName);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt==-1) break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} // try
		}// 파일 복사 if
	}//execute()
}//class
