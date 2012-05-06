package im.lich.gdms.core.model.teacher;

import im.lich.gdms.base.model.IdEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
	private String majorRestrict = "";//限制专业
	private List<String> majorRes = new ArrayList<String>();//限制专业辅助变量，不计入数据库
	private Teacher teacher;//开设课题的教师

	public Thesis() {
		super();
	}

	public Thesis(String name) {
		super();
		this.name = name;
	}

	public Thesis(String name, String type, String property, String mode, String assign, String majorRestrict) {
		super();
		this.name = name;
		this.type = type;
		this.property = property;
		this.mode = mode;
		this.assign = assign;
		this.majorRestrict = majorRestrict;
	}

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

	@Transient
	public List<String> getMajorRes() {
		return majorRes;
	}

	public void setMajorRes(List<String> majorRes) {
		this.majorRes = majorRes;
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
