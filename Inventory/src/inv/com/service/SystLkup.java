package inv.com.service;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="tbl_syst_lkup")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SystLkup implements Serializable {
	
	@EmbeddedId
	private PKSystLkup compId;
	
	private String description;
	private Integer seq;
	private Boolean enabled;
	
	public PKSystLkup getCompId() {
		return compId;
	}
	public void setCompId(PKSystLkup compId) {
		this.compId = compId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public Integer getSeq() {
		return this.seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public boolean equals(Object other) {
		if ( !(other instanceof SystLkup) ) return false;
		SystLkup castOther = (SystLkup) other;
		return new EqualsBuilder()
			.append(this.getCompId(), castOther.getCompId())
			.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("compId =>", getCompId())
			.toString();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCompId())
			.toHashCode();
	}
	
	/* convenient method */
	public String getTableCode() {
		if (this.compId == null) return "";
		return this.compId.getTableCode();
	}
	public String getTableType() {
		if (this.compId == null) return "";
		return this.compId.getTableType();
	}
	
	public void setTableType(String tableType) {
		if (this.compId == null) {
			this.compId = new PKSystLkup();
		}
		this.compId.setTableType(tableType);
	}
	public void setTableCode(String tableCode) {
		if (this.compId == null) {
			this.compId = new PKSystLkup();
		}
		this.compId.setTableCode(tableCode);
	}
}
