package im.lich.gdms.core.model.teacher;

import im.lich.gdms.base.model.IdEntity;
import im.lich.gdms.core.model.generic.User;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Teacher extends IdEntity implements User {
	private static final long serialVersionUID = 5919308243195296451L;

	private String loginName;//教工号
	private String name = "";//姓名
	private String password = "0";//密码
	private String dept = "";//部门
	private Integer subnum = 0;//可指导学生数、课题数
	private String tel = "";//电话
	private String mail = "";//邮箱
	private Set<Thesis> thesises = new HashSet<Thesis>();//教师的课题

	public Teacher() {
		super();
	}

	public Teacher(String loginName) {
		super();
		this.loginName = loginName;
	}

	public Teacher(String loginName, String name, String password) {
		super();
		this.loginName = loginName;
		this.name = name;
		this.password = password;
	}

	public Teacher(String loginName, String name, String password, String dept, Integer subnum, String tel, String mail) {
		super();
		this.loginName = loginName;
		this.name = name;
		this.password = password;
		this.dept = dept;
		this.subnum = subnum;
		this.tel = tel;
		this.mail = mail;
	}

	@Column(nullable = false, unique = true)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable = false)
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(nullable = false)
	public Integer getSubnum() {
		return subnum;
	}

	public void setSubnum(Integer subnum) {
		this.subnum = subnum;
	}

	@Column(nullable = false)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(nullable = false)
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
	public Set<Thesis> getThesises() {
		return thesises;
	}

	public void setThesises(Set<Thesis> thesises) {
		this.thesises = thesises;
	}
}
