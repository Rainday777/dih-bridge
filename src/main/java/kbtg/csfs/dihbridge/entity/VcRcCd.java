package kbtg.csfs.dihbridge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
// KBNK_BR_NO, ttt.KBNK_HUB_NO, ttt.EN_CNTR_NM, ttt.TH_CNTR_NM,ttt.CRT_DT, ttt.LAST_UDT_DT
@Entity
@Table(schema = "IP_SHR",name="VC_RC_CD")
@NamedNativeQuery(name="VcRcCd.findByMaxLastUpdate",query="SELECT ttt.RC_CD, ttt.KBNK_BR_NO, ttt.KBNK_HUB_NO, ttt.EN_CNTR_NM, ttt.TH_CNTR_NM,ttt.CRT_DT, ttt.LAST_UDT_DT "
		+ "FROM VC_RC_CD ttt inner join (SELECT RC_CD, MAX(LAST_UDT_DT) as updateDate FROM VC_RC_CD GROUP BY RC_CD ) max_dt_rc "
		+ "ON max_dt_rc.RC_CD = ttt.RC_CD AND max_dt_rc.updateDate = ttt.LAST_UDT_DT"
		,resultClass=VcRcCd.class)
@NamedNativeQuery(name="VcRcCd.findByCurrentdate",query="SELECT ttt.RC_CD, ttt.KBNK_BR_NO, ttt.KBNK_HUB_NO, ttt.EN_CNTR_NM, ttt.TH_CNTR_NM,ttt.CRT_DT, ttt.LAST_UDT_DT "
		+ "FROM VC_RC_CD ttt inner join (SELECT RC_CD, MAX(LAST_UDT_DT) as updateDate FROM VC_RC_CD GROUP BY RC_CD ) max_dt_rc "
		+ "ON max_dt_rc.RC_CD = ttt.RC_CD AND max_dt_rc.updateDate = ttt.LAST_UDT_DT "
		+ "WHERE ttt.LAST_UDT_DT > CURRENT_DATE - 1 OR ttt.CRT_DT > CURRENT_DATE  - 1"
		,resultClass=VcRcCd.class)
public class VcRcCd implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="RC_CD")
	private Integer rcCd;
	
	@Column(name="KBNK_BR_NO")
	private String kbnkBrNo;
	
	@Column(name="KBNK_HUB_NO")
	private String KbnkHubNo;
	
	@Column(name="TH_CNTR_NM")
	private String thCntrNm;
	
	@Column(name="EN_CNTR_NM")
	private String enCntrNm;
	
	@Column(name="CRT_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date crtDt;
	
	@Column(name="LAST_UDT_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUdtDt;
	
	public VcRcCd() {}

	public Integer getRcCd() {
		return rcCd;
	}

	public void setRcCd(Integer rcCd) {
		this.rcCd = rcCd;
	}

	public String getKbnkBrNo() {
		return kbnkBrNo;
	}

	public void setKbnkBrNo(String kbnkBrNo) {
		this.kbnkBrNo = kbnkBrNo;
	}

	public String getKbnkHubNo() {
		return KbnkHubNo;
	}

	public void setKbnkHubNo(String kbnkHubNo) {
		KbnkHubNo = kbnkHubNo;
	}

	public String getThCntrNm() {
		return thCntrNm;
	}

	public void setThCntrNm(String thCntrNm) {
		this.thCntrNm = thCntrNm;
	}

	public String getEnCntrNm() {
		return enCntrNm;
	}

	public void setEnCntrNm(String enCntrNm) {
		this.enCntrNm = enCntrNm;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public Date getLastUdtDt() {
		return lastUdtDt;
	}

	public void setLastUdtDt(Date lastUdtDt) {
		this.lastUdtDt = lastUdtDt;
	}


	@Override
	public int hashCode() {
		return Objects.hash(rcCd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof VcRcCd)) {
			return false;
		}
		VcRcCd other = (VcRcCd) obj;
		return Objects.equals(rcCd, other.rcCd);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VcRcCd [rcCd=");
		builder.append(rcCd);
		builder.append(", kbnkBrNo=");
		builder.append(kbnkBrNo);
		builder.append(", KbnkHubNo=");
		builder.append(KbnkHubNo);
		builder.append(", thCntrNm=");
		builder.append(thCntrNm);
		builder.append(", enCntrNm=");
		builder.append(enCntrNm);
		builder.append(", crtDt=");
		builder.append(crtDt);
		builder.append(", lastUdtDt=");
		builder.append(lastUdtDt);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
