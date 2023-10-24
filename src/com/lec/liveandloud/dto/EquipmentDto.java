package com.lec.liveandloud.dto;

import java.sql.Timestamp;

public class EquipmentDto {
	private int eid;
	private String aid;
	private String aname;
	private String etitle;
	private String econtent;
	private String efileName;
	private Timestamp erdate;
	private int ehit;
	private int egroup;
	private int estep;
	private int eindent;
	private String eip;
	public EquipmentDto() {}
	public EquipmentDto(int eid, String aid, String aname, String etitle, String econtent, String efileName,
			Timestamp erdate, int ehit, int egroup, int estep, int eindent, String eip) {
		this.eid = eid;
		this.aid = aid;
		this.aname = aname;
		this.etitle = etitle;
		this.econtent = econtent;
		this.efileName = efileName;
		this.erdate = erdate;
		this.ehit = ehit;
		this.egroup = egroup;
		this.estep = estep;
		this.eindent = eindent;
		this.eip = eip;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
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
	public String getEtitle() {
		return etitle;
	}
	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}
	public String getEcontent() {
		return econtent;
	}
	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}
	public String getEfileName() {
		return efileName;
	}
	public void setEfileName(String efileName) {
		this.efileName = efileName;
	}
	public Timestamp getErdate() {
		return erdate;
	}
	public void setErdate(Timestamp erdate) {
		this.erdate = erdate;
	}
	public int getEhit() {
		return ehit;
	}
	public void setEhit(int ehit) {
		this.ehit = ehit;
	}
	public int getEgroup() {
		return egroup;
	}
	public void setEgroup(int egroup) {
		this.egroup = egroup;
	}
	public int getEstep() {
		return estep;
	}
	public void setEstep(int estep) {
		this.estep = estep;
	}
	public int getEindent() {
		return eindent;
	}
	public void setEindent(int eindent) {
		this.eindent = eindent;
	}
	public String getEip() {
		return eip;
	}
	public void setEip(String eip) {
		this.eip = eip;
	}
	@Override
	public String toString() {
		return "EquipmentDto [eid=" + eid + ", aid=" + aid + ", aname=" + aname + ", etitle=" + etitle + ", econtent="
				+ econtent + ", efileName=" + efileName + ", erdate=" + erdate + ", ehit=" + ehit + ", egroup=" + egroup
				+ ", estep=" + estep + ", eindent=" + eindent + ", eip=" + eip + "]";
	}
}
