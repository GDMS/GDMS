package im.lich.gdms.core.model.teacher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Thesis extends IdEntity {
	private static final long serialVersionUID = 2084180035575935501L;

	@Column(length = 50, nullable = false)
	private String name;//专业、班级名称

	@Column(length = 20, nullable = false)
	private String type;//类型

	@Column(length = 20, nullable = false)
	private String property;//性质

	@Column(length = 20, nullable = false)
	private String mode;//方式

	@Column(length = 20, nullable = false)
	private String assign;//分配方式

	private Teacher teacher;//开设课题的教师

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
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
