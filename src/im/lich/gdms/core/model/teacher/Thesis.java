package im.lich.gdms.core.model.teacher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Thesis extends IdEntity {
	private static final long serialVersionUID = 2084180035575935501L;

	private String name = "";//课题名称
	private String type = "";//类型
	private String property = "";//性质
	private String mode = "";//方式
	private String assign = "";//分配方式
	private String majorRestrict = "";
	private Teacher teacher;//开设课题的教师

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(nullable = false)
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Column(nullable = false)
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Column(nullable = false)
	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	@Column(nullable = false)
	public String getMajorRestrict() {
		return majorRestrict;
	}

	public void setMajorRestrict(String majorRestrict) {
		this.majorRestrict = majorRestrict;
	}

	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
