package im.lich.gdms.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TeacherDept extends IdEntity {
	private static final long serialVersionUID = 5919308243195296451L;

	@Column(length = 50, nullable = false)
	private String name;//部门名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
