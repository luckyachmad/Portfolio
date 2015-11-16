package inv.com.service;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Embeddable
public class PKSystLkup implements Serializable {
	
	private String tableType;
	private String tableCode;
	
	public PKSystLkup() {}
	
	public PKSystLkup(String tableType, String tableCode) {
		this.tableType = tableType;
		this.tableCode = tableCode;
	}
	
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getTableCode() {
		return tableCode;
	}
	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
	
	@Override
	public boolean equals(Object other) {
		if ( !(other instanceof PKSystLkup) ) {
			return false;
		}
		PKSystLkup castOther = (PKSystLkup) other;
		return new EqualsBuilder()
			.append(this.getTableType(), castOther.getTableType())
			.append(this.getTableCode(), castOther.getTableCode())
			.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("tableType", getTableType())
			.append("tableCode", getTableCode())
			.toString();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTableType())
			.append(getTableCode())
			.toHashCode();
	}

}