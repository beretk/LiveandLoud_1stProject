package com.lec.liveandloud.dto;

import java.sql.Timestamp;

public class NoticeDto {
	private int nid;
	private String aid;
	private String aname;
	private String ntitle;
	private String ncontent;
	private String nfileName;
	private Timestamp nrdate;
	private int nhit;
	private int ngroup;
	private int nstep;
	private int nindent;
	private String nip;
	public NoticeDto() {}
	public NoticeDto(int nid, String aid, String aname, String ntitle, String ncontent, String nfileName,
			Timestamp nrdate, int nhit, int ngroup, int nstep, int nindent, String nip) {
		this.nid = nid;
		this.aid = aid;
		this.aname = aname;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nfileName = nfileName;
		this.nrdate = nrdate;
		this.nhit = nhit;
		this.ngroup = ngroup;
		this.nstep = nstep;
		this.nindent = nindent;
		this.nip = nip;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNfileName() {
		return nfileName;
	}
	public void setNfileName(String nfileName) {
		this.nfileName = nfileName;
	}
	public Timestamp getNrdate() {
		return nrdate;
	}
	public void setNrdate(Timestamp nrdate) {
		this.nrdate = nrdate;
	}
	public int getNhit() {
		return nhit;
	}
	public void setNhit(int nhit) {
		this.nhit = nhit;
	}
	public int getNgroup() {
		return ngroup;
	}
	public void setNgroup(int ngroup) {
		this.ngroup = ngroup;
	}
	public int getNstep() {
		return nstep;
	}
	public void setNstep(int nstep) {
		this.nstep = nstep;
	}
	public int getNindent() {
		return nindent;
	}
	public void setNindent(int nindent) {
		this.nindent = nindent;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	@Override
	public String toString() {
		return "NoticeDto [nid=" + nid + ", aid=" + aid + ", aname=" + aname + ", ntitle=" + ntitle + ", ncontent="
				+ ncontent + ", nfileName=" + nfileName + ", nrdate=" + nrdate + ", nhit=" + nhit + ", ngroup=" + ngroup
				+ ", nstep=" + nstep + ", nindent=" + nindent + ", nip=" + nip + "]";
	}
}