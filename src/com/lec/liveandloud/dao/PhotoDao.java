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
	
}



