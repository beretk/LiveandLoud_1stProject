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
import com.lec.liveandloud.dao.PhotoDao;
import com.lec.liveandloud.dto.AdminDto;
import com.lec.liveandloud.dto.PhotoDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PhotoWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("photoUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String pfileName = "";
		int result = PhotoDao.FAIL;
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", 
												new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			pfileName = mRequest.getFilesystemName(param);
			// aId, pTitle, pContent,  pileName, pIp
			HttpSession httpSession = request.getSession();
			AdminDto admin = (AdminDto)httpSession.getAttribute("admin");
			if(admin!=null) {
				String aId = admin.getAid(); // 로그인 한 사람의 aId
				String ptitle = mRequest.getParameter("ptitle");
				String pcontent = mRequest.getParameter("pcontent");
				String pip = request.getRemoteAddr();
				PhotoDao photoDao = PhotoDao.getInstance();
				PhotoDto photoDto = new PhotoDto(0, aId, null, ptitle, pcontent, pfileName,
											null, maxSize, 0, 0, 0, pip);
				result = photoDao.writePhoto(photoDto);
				// joinAdmin결과에 따라 적절히 request.setAttribute
				if(result == PhotoDao.SUCCESS) {  
					request.setAttribute("photoResult", "글쓰기 성공");
				}else {
					request.setAttribute("photoResult", "글쓰기 실패");
				}
			}else {
				request.setAttribute("photoResult", "관리자만 글쓸 수 있어요");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("photoResult", "글쓰기 실패");
		}
		// 서버에 올라간 photoUp 파일을 소스폴더에 filecopy
		if(pfileName!=null && result==AdminDao.SUCCESS) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+pfileName);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:/webPro/source/08_1stProject/LiveandLoud/WebContent/photoUp/"+pfileName);
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

