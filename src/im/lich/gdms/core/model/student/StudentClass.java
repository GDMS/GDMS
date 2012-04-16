package im.lich.gdms.core.model.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentClass extends IdEntity {
	private static final long serialVersionUID = 2084180035575934401L;

	private String name = "";//班级名称

	public StudentClass() {
		super();
	}

	public StudentClass(String name) {
		super();
		this.name = name;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
