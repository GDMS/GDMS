package im.lich.gdms.core.model.teacher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TeacherDept extends IdEntity {
	private static final long serialVersionUID = 5919308243195296451L;

	private String name = "";//部门名称

	public TeacherDept() {
		super();
	}

	public TeacherDept(String name) {
		super();
		this.name = name;
	}

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
