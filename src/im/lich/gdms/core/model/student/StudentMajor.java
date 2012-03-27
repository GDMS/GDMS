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
public class StudentMajor extends IdEntity {
	private static final long serialVersionUID = 2084180035575935501L;

	private String name = "";//专业、班级名称

	public StudentMajor() {
		super();
	}

	public StudentMajor(String name) {
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
