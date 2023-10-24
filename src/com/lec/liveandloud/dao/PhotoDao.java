package com.lec.liveandloud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.liveandloud.dto.NoticeDto;
import com.lec.liveandloud.dto.PhotoDto;

public class PhotoDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	// 싱글톤
	private static PhotoDao instance = new PhotoDao();
	public static PhotoDao getInstance() {
		return instance;
	}
	public PhotoDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 글목록(startRow~endRow)
	public ArrayList<PhotoDto> listPhoto(int startRow, int endRow){
		ArrayList<PhotoDto> dtos = new ArrayList<PhotoDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM " + 
				"  (SELECT ROWNUM RN, A.* FROM (SELECT P.*, ANAME FROM PHOTO P, ADMIN A " + 
				"        WHERE P.AID=A.AID ORDER BY PGROUP DESC, PSTEP) A) " + 
				"  WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    pid      = rs.getInt("pid");
				String aid      = rs.getString("aid");
				String aname    = rs.getString("aname");
				String ptitle   = rs.getString("ptitle");
				String pcontent = rs.getString("pcontent");
				String pfileName= rs.getString("pfileName");
				Timestamp prdate= rs.getTimestamp("prdate");
				int    phit     = rs.getInt("phit");
				int    pgroup   = rs.getInt("pgroup");
				int    pstep    = rs.getInt("pstep");
				int    pindent  = rs.getInt("pindent");
				String pip      = rs.getString("pip");
				dtos.add(new PhotoDto(pid, aid, aname, ptitle, pcontent, pfileName, prdate, phit, pgroup, pstep, pindent, pip));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage() + ": 공연사진 리스트 예외");
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dtos;
	}
	// (2) 글갯수
	public int getNoticeTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM PHOTO";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return totCnt;
	}
	// (3) 글쓰기(원글쓰기)
	public int writePhoto(PhotoDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PHOTO (PID, AID, PTITLE, PCONTENT, PFILENAME, PGROUP, PSTEP, PINDENT, PIP) " + 
				"  VALUES (PHOTO_SEQ.NEXTVAL, ?, ?, ?, ?,  " + 
				"    PHOTO_SEQ.CURRVAL, 0,0, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAid());
			pstmt.setString(2, dto.getPtitle());
			pstmt.setString(3, dto.getPcontent());
			pstmt.setString(4, dto.getPfileName());
			pstmt.setString(5, dto.getPip());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("원글쓰기 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 원글쓰기 실패 :");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	// (4) hit 1회 올리기
	public void hitUp(int pid) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PHOTO SET PHIT = PHIT + 1 WHERE PID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 조회수 up 실패");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	// (5) 글번호(pid)로 글전체 내용(PhotoDto) 가져오기 - 글상세보기, 글수정뷰, 답변글쓰기뷰용
	public PhotoDto getPhotoNotHitUp(int pid) {
		PhotoDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT P.*, ANAME " + 
				"  FROM PHOTO P, ADMIN A WHERE P.AID = A.AID AND PID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String aid = rs.getString("aid");
				String aname = rs.getString("aname");
				String ptitle = rs.getString("ptitle");
				String pcontent = rs.getString("pcontent");
				String pfileName = rs.getString("pfileName");
				Timestamp prdate = rs.getTimestamp("prdate");
				int    phit = rs.getInt("phit");
				int    pgroup= rs.getInt("pgroup");
				int    pstep= rs.getInt("pstep");
				int    pindent= rs.getInt("pindent");
				String pip = rs.getString("pip");
				dto = new PhotoDto(pid, aid, aname, ptitle, pcontent, pfileName, prdate, phit, pgroup, pstep, pindent, pip);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dto;
	}
	// (6) 글 수정하기(pid, ptitle, pcontent, pfilename, prdate(SYSDATE), pip 수정)
	public int modifyPhoto(PhotoDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PHOTO SET PTITLE = ?, " + 
				"                    PCONTENT = ?, " + 
				"                    PFILENAME = ?, " + 
				"                    PIP = ?, " + 
				"                    PRDATE = SYSDATE " + 
				"            WHERE PID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPtitle());
			pstmt.setString(2, dto.getPcontent());
			pstmt.setString(3, dto.getPfileName());
			pstmt.setString(4, dto.getPip());
			pstmt.setInt(5, dto.getPid());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정 성공":"글번호(pid) 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "글 수정 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	// (7) 글 삭제하기(pid로)
	public int deletePhoto(int pid) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PHOTO WHERE PID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글삭제 성공":"글번호(pid) 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "글 삭제 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	// (8) 글 삭제하기(삭제하고자 하는 글의 답변글들도 모두 삭제하고 삭제된 글 갯수 return)
		public int deletePhoto(int pgroup, int pstep, int pindent) {
			int deleteCnt = FAIL;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM PHOTO WHERE PGROUP = ? AND (PSTEP>=? AND  " + 
					"    PSTEP<(select NVL(MIN(PSTEP),9999) FROM PHOTO WHERE PGROUP=? AND PSTEP>? AND PINDENT<=?))";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pgroup);
				pstmt.setInt(2, pstep);
				pstmt.setInt(3, pgroup);
				pstmt.setInt(4, pstep);
				pstmt.setInt(5, pindent);
				deleteCnt = pstmt.executeUpdate();
				System.out.println(deleteCnt>=SUCCESS? deleteCnt+"개 글삭제성공":"글삭제실패");
				postDelete(deleteCnt, pgroup, pstep); // 글삭제시 pstep 재조정
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {System.out.println(e.getMessage());}
			}
			return deleteCnt;
		}
		// 위의 DELTE 수행한 후 PSTEP 연속적으로 재배열하기(생략가능)
		private void postDelete(int deleteCnt, int pgroup, int pstep) {
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE PHOTO SET PSTEP = PSTEP-? WHERE PGROUP=? AND PSTEP>?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, deleteCnt);
				pstmt.setInt(2, pgroup);
				pstmt.setInt(3, pstep);
				int cnt = pstmt.executeUpdate();
				System.out.println(cnt+"행 pstep 재조정");
			} catch (SQLException e) {
				System.out.println(e.getMessage() + "글 수정 실패 ");
			} finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn  != null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} 
			}
		}
	// (9) 답변글 쓰기 전 단계(원글의 pgroup과 같고, 원글의 pstep보다 크면 pstep을 하나 증가하기)
	private void preReplyPhotoStep(int pgroup, int pstep) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PHOTO SET PSTEP = PSTEP + 1 WHERE PGROUP=? AND PSTEP>?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pgroup);
			pstmt.setInt(2, pstep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " preReplyStep에서 오류");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	// (10) 답변글 쓰기
	public int reply(PhotoDto dto) {
		int result = FAIL;
		preReplyPhotoStep(dto.getPgroup(), dto.getPstep());
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PHOTO (PID, AID, PTITLE, PCONTENT, "
				+ 					"PFILENAME, PGROUP, PSTEP, PINDENT, PIP)" + 
				"  VALUES (PHOTO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAid());
			pstmt.setString(2, dto.getPtitle());
			pstmt.setString(3, dto.getPcontent());
			pstmt.setString(4, dto.getPfileName());
			pstmt.setInt(5, dto.getPgroup());
			pstmt.setInt(6, dto.getPstep() + 1);
			pstmt.setInt(7, dto.getPindent() + 1);
			pstmt.setString(8, dto.getPip());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("답변글쓰기 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 답변글쓰기 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
}



