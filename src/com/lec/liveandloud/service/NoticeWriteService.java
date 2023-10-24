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

import com.lec.liveandloud.dao.AdminDao;
import com.lec.liveandloud.dao.NoticeDao;
import com.lec.liveandloud.dto.AdminDto;
import com.lec.liveandloud.dto.NoticeDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("noticeUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String nfileName = "";
		int result = NoticeDao.FAIL;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", 
												new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			nfileName = mRequest.getFilesystemName(param);
			// aId, nTitle, nContent,  nileName, nIp
			HttpSession httpSession = request.getSession();
			AdminDto admin = (AdminDto)httpSession.getAttribute("admin");
			if(admin!=null) {
				String aId = admin.getAid(); // 로그인 한 사람의 aId
				String ntitle = mRequest.getParameter("ntitle");
				String ncontent = mRequest.getParameter("ncontent");
				String nip = request.getRemoteAddr();
				NoticeDao noticeDao = NoticeDao.getInstance();
				NoticeDto noticeDto = new NoticeDto(0, aId, null, ntitle, ncontent, nfileName,
											null, maxSize, 0, 0, 0, nip);
				result = noticeDao.writeNotice(noticeDto);
				// joinAdmin결과에 따라 적절히 request.setAttribute
				if(result == NoticeDao.SUCCESS) {  
					request.setAttribute("noticeResult", "글쓰기 성공");
				}else {
					request.setAttribute("noticeResult", "글쓰기 실패");
				}
			}else {
				request.setAttribute("noticeResult", "관리자만 글쓸 수 있어요");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("noticeResult", "글쓰기 실패");
		}
		// 서버에 올라간 noticeUp 파일을 소스폴더에 filecopy
		if(nfileName!=null && result==AdminDao.SUCCESS) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/" + nfileName);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:/webPro/source/08_1stProject/LiveandLoud/WebContent/noticeUp/" + nfileName);
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
			}
		}
	}
}
