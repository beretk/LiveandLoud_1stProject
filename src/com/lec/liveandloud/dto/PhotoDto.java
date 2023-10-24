package com.lec.liveandloud.dto;

import java.sql.Timestamp;

public class PhotoDto {
	private int pid;
	private String aid;
	private String aname;
	private String ptitle;
	private String pcontent;
	private String pfileName;
	private Timestamp prdate;
	private int phit;
	private int pgroup;
	private int pstep;
	private int pindent;
	private String pip;
	public PhotoDto() {}
	public PhotoDto(int pid, String aid, String aname, String ptitle, String pcontent, String pfileName,
			Timestamp prdate, int phit, int pgroup, int pstep, int pindent, String pip) {
		this.pid = pid;
		this.aid = aid;
		this.aname = aname;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pfileName = pfileName;
		this.prdate = prdate;
		this.phit = phit;
		this.pgroup = pgroup;
		this.pstep = pstep;
		this.pindent = pindent;
		this.pip = pip;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
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
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPfileName() {
		return pfileName;
	}
	public void setPfileName(String pfileName) {
		this.pfileName = pfileName;
	}
	public Timestamp getPrdate() {
		return prdate;
	}
	public void setPrdate(Timestamp prdate) {
		this.prdate = prdate;
	}
	public int getPhit() {
		return phit;
	}
	public void setPhit(int phit) {
		this.phit = phit;
	}
	public int getPgroup() {
		return pgroup;
	}
	public void setPgroup(int pgroup) {
		this.pgroup = pgroup;
	}
	public int getPstep() {
		return pstep;
	}
	public void setPstep(int pstep) {
		this.pstep = pstep;
	}
	public int getPindent() {
		return pindent;
	}
	public void setPindent(int pindent) {
		this.pindent = pindent;
	}
	public String getPip() {
		return pip;
	}
	public void setPip(String pip) {
		this.pip = pip;
	}
	@Override
	public String toString() {
		return "PhotoDto [pid=" + pid + ", aid=" + aid + ", aname=" + aname + ", ptitle=" + ptitle + ", pcontent="
				+ pcontent + ", pfileName=" + pfileName + ", prdate=" + prdate + ", phit=" + phit + ", pgroup=" + pgroup
				+ ", pstep=" + pstep + ", pindent=" + pindent + ", pip=" + pip + "]";
	}
	
}
