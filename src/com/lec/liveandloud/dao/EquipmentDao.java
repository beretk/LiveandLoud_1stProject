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

import com.lec.liveandloud.dto.EquipmentDto;

public class EquipmentDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	// 싱글톤
	private static EquipmentDao instance = new EquipmentDao();
	public static EquipmentDao getInstance() {
		return instance;
	}
	public EquipmentDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 글목록(startRow~endRow)
	public ArrayList<EquipmentDto> listEquipment(int startRow, int endRow){
		ArrayList<EquipmentDto> dtos = new ArrayList<EquipmentDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM " + 
				"  (SELECT ROWNUM RN, A.* FROM (SELECT E.*, ANAME FROM EQUIPMENT E, ADMIN A " + 
				"        WHERE E.AID=A.AID ORDER BY EGROUP DESC, ESTEP) A) " + 
				"  WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    eid      = rs.getInt("eid");
				String aid      = rs.getString("aid");
				String aname    = rs.getString("aname");
				String etitle   = rs.getString("etitle");
				String econtent = rs.getString("econtent");
				String efileName= rs.getString("efileName");
				Timestamp erdate= rs.getTimestamp("erdate");
				int    ehit     = rs.getInt("ehit");
				int    egroup   = rs.getInt("egroup");
				int    estep    = rs.getInt("estep");
				int    eindent  = rs.getInt("eindent");
				String eip      = rs.getString("eip");
				dtos.add(new EquipmentDto(eid, aid, aname, etitle, econtent, efileName, erdate, ehit, egroup, estep, eindent, eip));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage() + ": 공지사항 리스트 예외");
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
	public int getEquipmentTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM EQUIPMENT";
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
	public int writeEquipment(EquipmentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO EQUIPMENT (EID, AID, ETITLE, ECONTENT, EFILENAME, EGROUP, ESTEP, EINDENT, EIP) " + 
				"  VALUES (EQUIPMENT_SEQ.NEXTVAL, ?, ?, ?, ?,  " + 
				"    EQUIPMENT_SEQ.CURRVAL, 0,0, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAid());
			pstmt.setString(2, dto.getEtitle());
			pstmt.setString(3, dto.getEcontent());
			pstmt.setString(4, dto.getEfileName());
			pstmt.setString(5, dto.getEip());
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
	public void hitUp(int eid) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE EQUIPMENT SET EHIT = EHIT + 1 WHERE EID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eid);
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
	// (5) 글번호(eid)로 글전체 내용(EquipmentDto) 가져오기 - 글상세보기, 글수정뷰, 답변글쓰기뷰용
	public EquipmentDto getEquipmentNotHitUp(int eid) {
		EquipmentDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT E.*, ANAME " + 
				"  FROM EQUIPMENT E, ADMIN A WHERE E.AID=A.AID AND EID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String aid = rs.getString("aid");
				String aname = rs.getString("aname");
				String etitle = rs.getString("etitle");
				String econtent = rs.getString("econtent");
				String efileName = rs.getString("efileName");
				Timestamp erdate = rs.getTimestamp("erdate");
				int    ehit = rs.getInt("ehit");
				int    egroup= rs.getInt("egroup");
				int    estep= rs.getInt("estep");
				int    eindent= rs.getInt("eindent");
				String eip = rs.getString("eip");
				dto = new EquipmentDto(eid, aid, aname, etitle, econtent, efileName, erdate, ehit, egroup, estep, eindent, eip);
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
	// (6) 글 수정하기(eid, econtent, erdate(SYSDATE), eip 수정)
	public int modifyEquipment(EquipmentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE EQUIPMENT SET ETITLE = ?, " + 
				"                    ECONTENT = ?, " + 
				"                    EFILENAME = ?, " + 
				"                    EIP = ?, " + 
				"                    ERDATE = SYSDATE " + 
				"            WHERE EID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEtitle());
			pstmt.setString(2, dto.getEcontent());
			pstmt.setString(3, dto.getEfileName());
			pstmt.setString(4, dto.getEip());
			pstmt.setInt(5, dto.getEid());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정 성공":"글번호(bid) 오류");
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
	// (7) 글 삭제하기(eid로)
	public int deleteEquipment(int eid) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM EQUIPMENT WHERE EID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eid);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글삭제 성공":"글번호(nid) 오류");
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
		public int deleteEquipment(int egroup, int estep, int eindent) {
			int deleteCnt = FAIL;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM EQUIPMENT WHERE EGROUP = ? AND (ESTEP>=? AND  " + 
					"    ESTEP<(select NVL(MIN(ESTEP),9999) FROM EQUIPMENT WHERE EGROUP=? AND ESTEP>? AND EINDENT<=?))";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, egroup);
				pstmt.setInt(2, estep);
				pstmt.setInt(3, egroup);
				pstmt.setInt(4, estep);
				pstmt.setInt(5, eindent);
				deleteCnt = pstmt.executeUpdate();
				System.out.println(deleteCnt>=SUCCESS? deleteCnt+"개 글삭제성공":"글삭제실패");
				postDelete(deleteCnt, egroup, estep); // 글삭제시 estep 재조정
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
		// 위의 DELTE 수행한 후 ESTEP 연속적으로 재배열하기(생략가능)
		private void postDelete(int deleteCnt, int egroup, int estep) {
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE EQUIPMENT SET ESTEP = ESTEP-? WHERE EGROUP=? AND ESTEP>?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, deleteCnt);
				pstmt.setInt(2, egroup);
				pstmt.setInt(3, estep);
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
	// (9) 답변글 쓰기 전 단계(원글의 egroup과 같고, 원글의 estep보다 크면 estep을 하나 증가하기)
	private void preReplyEquipmentStep(int egroup, int estep) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE EQIPMENT SET ESTEP = ESTEP + 1 WHERE EGROUP=? AND ESTEP>?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, egroup);
			pstmt.setInt(2, estep);
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
	public int reply(EquipmentDto dto) {
		int result = FAIL;
		preReplyEquipmentStep(dto.getEgroup(), dto.getEstep());
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO EQUIPMENT (EID, AID, ETITLE, ECONTENT, "
				+ 					"EFILENAME, EGROUP, ESTEP, EINDENT, EIP)" + 
				"  VALUES (EQUIPMENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAid());
			pstmt.setString(2, dto.getEtitle());
			pstmt.setString(3, dto.getEcontent());
			pstmt.setString(4, dto.getEfileName());
			pstmt.setInt(5, dto.getEgroup());
			pstmt.setInt(6, dto.getEstep() + 1);
			pstmt.setInt(7, dto.getEindent() + 1);
			pstmt.setString(8, dto.getEip());
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
