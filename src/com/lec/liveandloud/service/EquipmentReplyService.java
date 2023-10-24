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

import com.lec.liveandloud.dao.EquipmentDao;
import com.lec.liveandloud.dto.AdminDto;
import com.lec.liveandloud.dto.EquipmentDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class EquipmentReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String path = request.getRealPath("equipmentUp");
		int maxSize = 1024*1024*10;
		String efileName = "";
		int result = EquipmentDao.FAIL;
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			efileName = mRequest.getFilesystemName(param);
			HttpSession httpSession = request.getSession();
			AdminDto admin = (AdminDto)httpSession.getAttribute("admin");
			if(admin ==null) {
				request.setAttribute("equipmentResult", "로그인 후에만 답글쓰기 가능");
				return;
			}
			String aid = admin.getAid();
			String etitle = mRequest.getParameter("etitle");
			String econtent = mRequest.getParameter("econtent");
			String eip = request.getRemoteAddr();
			int egroup = Integer.parseInt(mRequest.getParameter("egroup"));
			int estep = Integer.parseInt(mRequest.getParameter("estep"));
			int eindent = Integer.parseInt(mRequest.getParameter("eindent"));
			EquipmentDao equipmentDao = EquipmentDao.getInstance();
			EquipmentDto equipmentDto = new EquipmentDto(0, aid, null, etitle, econtent, efileName, null, 0, egroup, estep, eindent, eip);
			result = equipmentDao.reply(equipmentDto);
			 
			if(result == EquipmentDao.SUCCESS) {  
				request.setAttribute("equipmentResult", "답글쓰기 성공");
			}else {
				request.setAttribute("equipmentResult", "답글쓰기 실패");
			}
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("equipmentResult", "답글쓰기 실패");
		}
		if(efileName!=null && result == EquipmentDao.SUCCESS) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/" + efileName);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:/webPro/source/08_1stProject/LiveandLoud/WebContent/equipmentUp/" + efileName);
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
