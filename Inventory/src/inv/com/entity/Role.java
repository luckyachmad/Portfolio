package inv.com.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="role")
public class Role implements Serializable {
	
	@Id
	private String id;
	
	private String description;
	
	@ManyToMany(mappedBy="roles")
	private Set<User> users;
	
	public Role(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

		
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public boolean equals(Object other) {
		if ( !(other instanceof Role) ) {
			return false;
		}
		Role castOther = (Role) other;
		return new EqualsBuilder()
			.append(this.getId(), castOther.getId())			
			.isEquals();
	}
}
