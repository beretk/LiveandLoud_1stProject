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

import com.lec.liveandloud.dao.AdminDao;
import com.lec.liveandloud.dao.EquipmentDao;
import com.lec.liveandloud.dto.EquipmentDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class EquipmentModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		 
		String path = request.getRealPath("equipmentUp");
		int maxSize = 1024*1024*10; 
		String efileName = "", dbFileName = null;
		int result = EquipmentDao.FAIL;
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			efileName = mRequest.getFilesystemName(param);
			dbFileName = mRequest.getParameter("dbFileName");
			if(efileName==null) {
				efileName = dbFileName;
			}
			int eid = Integer.parseInt(mRequest.getParameter("eid"));
			String etitle = mRequest.getParameter("etitle");
			String econtent = mRequest.getParameter("econtent");
			String eip = request.getRemoteAddr();
			EquipmentDao equipmentDao = EquipmentDao.getInstance();
			EquipmentDto equipmentDto = new EquipmentDto(eid, null, null, etitle, econtent, 
											efileName, null, 0, 0, 0, 0, eip);
			result = equipmentDao.modifyEquipment(equipmentDto);
			if(result == EquipmentDao.SUCCESS) { 
				request.setAttribute("equipmentResult", "글수정 성공");
			}else {
				request.setAttribute("equipmentResult", "글수정 실패");
			}
			//★mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set : modify,reply★
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("equipmentResult", "글수정 실패");
		}
		if(dbFileName!=null && !efileName.equals(dbFileName) && result==AdminDao.SUCCESS) { 
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
